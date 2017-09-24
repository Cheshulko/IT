package fake;
import db.DB;
import db.table.Table;
import db.table.TableInstance;
import db.table.field.base.BaseField;
import db.table.field.base.BaseFieldInstance;
import db.table.field.base.BaseFieldType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FakeDB {

	private ObservableList<DB> dbs;
	
	public FakeDB(){
		dbs = FXCollections.observableArrayList();
		DB db1 = new DB("first");
		DB db2 = new DB("second");
		DB db3 = new DB("third");
		Table tb1_1 = null;
		Table tb1_2 = null;		
		try {
			 tb1_1 = Table.tableBuilder().setTableName("table1")
						.addTableBaseField(new BaseField("field1_1", BaseFieldType.INTEGER))
						.addTableBaseField(new BaseField("field2_1", BaseFieldType.REAL)).build();
			 tb1_2 = Table.tableBuilder().setTableName("table2")
						.addTableBaseField(new BaseField("field1_2", BaseFieldType.INTEGER))
						.addTableBaseField(new BaseField("field2_2", BaseFieldType.REAL))
						.addTableBaseField(new BaseField("field3_2", BaseFieldType.STRING))
						.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dbs.add(db1);
		dbs.add(db2);
		dbs.add(db3);
		
		db1.createTable(tb1_1);
		db1.createTable(tb1_2);		
		
		try {
			tb1_1.addTableInstance(TableInstance.tableInstanceBuilder()
					.addBaseFieldInstance(new BaseFieldInstance("field1_1", 1, BaseFieldType.INTEGER))
					.addBaseFieldInstance(new BaseFieldInstance("field2_1", 1.23, BaseFieldType.REAL))
					.build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		try {
			tb1_2.addTableInstance(TableInstance.tableInstanceBuilder()
					.addBaseFieldInstance(new BaseFieldInstance("field1_2", 1123, BaseFieldType.INTEGER))
					.addBaseFieldInstance(new BaseFieldInstance("field2_2", 11.23, BaseFieldType.REAL))
					.addBaseFieldInstance(new BaseFieldInstance("field3_2", "asdasd", BaseFieldType.STRING))
					.build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
	}
	
	public ObservableList<DB> getDb() {
        return dbs;
    }
	
}
