package Lab.IT_client.stage.main;

import Lab.IT_client.App;
import ch.qos.logback.core.net.SyslogOutputStream;
import db.DB;
import db.table.Table;
import db.table.TableInstance;
import db.table.TableInstance.TableInstanceBuilder;
import db.table.field.base.BaseField;
import db.table.field.base.BaseFieldInstance;
import db.table.field.base.BaseFieldType;
import fake.FakeDB;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

	private App app;

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
				.addListener((observable, oldValue, newValue) -> getDbTables(newValue));

		tableTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> createViewTableInstance(newValue));
	}

	public void setDataFromFakeDB(FakeDB fakeDB) {
		dbTable.setItems(fakeDB.getDb());
	}

	private ObservableList<Table> cre(DB db) {
		ObservableList<Table> table = FXCollections.observableArrayList(db.getTables());
		return table;
	}

	private void createViewTableInstance(Table table) {
		if (table == null) return;
		System.out.println(table.getTableName());
		tableInstanceTable.getColumns().clear();
		vBoxInputFields.getChildren().clear();
		
		for (int i = 0; i < table.getTableBaseFields().size(); ++i) {
			final int finalIdx = i;
			BaseField currentBaseField = table.getTableBaseFields().get(i);
			System.out.println(currentBaseField.getTableFieldName());
			String columnId = currentBaseField.getTableFieldName() + ":" + currentBaseField.getType();
			TableColumn<TableInstance, String> col = new TableColumn<>(columnId);
			TextField textField = new TextField();
			textField.setId(columnId);
			textField.setPromptText(currentBaseField.getTableFieldName());
			vBoxInputFields.getChildren().add(textField);
			System.out.println();
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
			tableInstanceBuilder.addBaseFieldInstance(new BaseFieldInstance(textField.getId().split(":")[0],
					textField.getText(), BaseFieldType.getTableFieldType(textField.getId().split(":")[1])));
		}
		try {
			selecteTable.addTableInstance(tableInstanceBuilder.build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createViewTableInstance(selecteTable);
	}

	private void getDbTables(DB db) {
		tableInstanceTable.getItems().clear();
		tableInstanceTable.getColumns().clear();

		if (db != null) {
			System.out.println(db.getDbName());
			tableTable.setItems(cre(db));
			tableNameColumn.setCellValueFactory(cellData -> {
				StringProperty stringProperty = new SimpleStringProperty(cellData.getValue().getTableName());
				return stringProperty;
			});
		}
	}

	// public void setApp(App app) {
	// this.app = app;
	// dbTable.setItems();
	// // Добавление в таблицу данных из наблюдаемого списка
	// //personTable.setItems(mainApp.getPersonData());
	// }
}
