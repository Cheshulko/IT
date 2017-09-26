package Lab.IT_client.stage.main;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import db.DB;
import db.table.Table;
import db.table.TableInstance;
import db.table.TableInstance.TableInstanceBuilder;
import db.table.field.base.BaseField;
import db.table.field.base.BaseFieldInstance;
import db.table.field.base.BaseFieldType;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import service.IDbService;
import service.ITableService;

public class MainSceneController {

	@FXML
	private TableView<DB> dbTable;
	@FXML
	private TableView<Table> tableTable;
	@FXML
	private TableView<TableInstance> tableInstanceTable;
	@FXML
	private TableColumn<DB, String> dbNameColumn;
	@FXML
	private TableColumn<Table, String> tableNameColumn;
	@FXML
	private Label nameLabel1;
	@FXML
	private VBox vBoxInputFields;

	private DB stateDB;
	private Table stateTable;

	@Autowired
	private IDbService dbService;

	@Autowired
	private ITableService tableService;

	public MainSceneController() {

	}

	@FXML
	private void initialize() {
		dbNameColumn.setCellValueFactory(cellData -> {
			StringProperty stringProperty = new SimpleStringProperty(cellData.getValue().getDbName());
			return stringProperty;
		});

		tableNameColumn.setCellValueFactory(cellData -> {
			StringProperty stringProperty = new SimpleStringProperty(cellData.getValue().getTableName());
			return stringProperty;
		});

		dbTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> createViewDbTables(newValue));

		tableTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> createViewTableInstance(newValue));
	}

	private void createViewTableInstance(Table table) {
		if (table == null) {
			System.out.println("table == null");
			return;
		}
		// update();
		System.out.println(table.getTableName());
		tableInstanceTable.getColumns().clear();
		vBoxInputFields.getChildren().clear();

		for (int i = 0; i < table.getTableBaseFields().size(); ++i) {
			final int finalIdx = i;
			BaseField currentBaseField = table.getTableBaseFields().get(i);
			String columnId = currentBaseField.getTableFieldName() + ":" + currentBaseField.getType();

			TableColumn<TableInstance, String> col = new TableColumn<>(columnId);
			TextField textField = new TextField();
			textField.setId(columnId);
			textField.setPromptText(currentBaseField.getTableFieldName());
			vBoxInputFields.getChildren().add(textField);

			col.setCellValueFactory(cellValue -> new ReadOnlyObjectWrapper<>(
					cellValue.getValue().getBaseFields().get(finalIdx).getData().toString()));
			tableInstanceTable.getColumns().add(col);
		}

		tableInstanceTable.getItems().clear();

		for (int i = 0; i < table.getTableInstances().size(); ++i) {
			tableInstanceTable.getItems().add(table.getTableInstances().get(i));
		}
	}

	public void addTableInstance() {
		System.out.println("Cliked Add btn");
		Table selecteTable = tableTable.getSelectionModel().getSelectedItem();
		TableInstanceBuilder tableInstanceBuilder = TableInstance.tableInstanceBuilder();

		for (int i = 0; i < vBoxInputFields.getChildren().size(); ++i) {
			TextField textField = (TextField) vBoxInputFields.getChildren().get(i);
			BaseFieldType baseFieldType = BaseFieldType.getTableFieldType(textField.getId().split(":")[1]);

			tableInstanceBuilder.addBaseFieldInstance(new BaseFieldInstance(textField.getId().split(":")[0],
					BaseFieldInstance.createValidFromString(textField.getText(), baseFieldType), baseFieldType));
		}
		try {
			tableService.addTableInstance(selecteTable, tableInstanceBuilder.build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		update();
		createViewTableInstance(selecteTable);
	}

	public void deleteTableInstance() {
		System.out.println("Cliked delete btn");
		TableInstance tableInstance = tableInstanceTable.getSelectionModel().getSelectedItem();
		Table selecteTable = tableTable.getSelectionModel().getSelectedItem();

		if (tableInstance == null || selecteTable == null)
			return;

		tableService.delteTableInstance(selecteTable, tableInstance);
		update();
		createViewTableInstance(selecteTable);
	}

	private void createViewDbTables(DB db) {
		tableInstanceTable.getItems().clear();
		tableInstanceTable.getColumns().clear();

		stateDB = db;

		if (db == null)
			return;

		tableTable.setItems(FXCollections.observableArrayList(db.getTables()));
		tableNameColumn.setCellValueFactory(cellData -> {
			StringProperty stringProperty = new SimpleStringProperty(cellData.getValue().getTableName());
			return stringProperty;
		});

	}

	private void update() {
		dbTable.getItems().clear();
		tableInstanceTable.getItems().clear();
		tableInstanceTable.getColumns().clear();

		dbTable.setItems(FXCollections.observableArrayList(dbService.getDbs()));
		dbTable.getSelectionModel().select(stateDB);

		// tableTable.setItems(FXCollections.observableArrayList());
	}

	@PostConstruct
	public void init() {
		System.out.println("Start init @PostConstruct");
		System.out.println(dbService != null);
		update();
		System.out.println(dbTable.getItems().size());
		initialize();
	}
}
