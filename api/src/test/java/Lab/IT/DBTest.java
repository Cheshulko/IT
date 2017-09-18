package Lab.IT;

import db.DB;
import db.table.Table;
import db.table.TableInstance;
import db.table.field.TableField;
import db.table.field.TableFieldInstance;
import db.table.field.TableFieldType;
import junit.framework.TestCase;

public class DBTest extends TestCase {

	private DB db = null;

	protected void setUp() throws Exception {
		db = new DB("FirstDb");
	}

	private Table createTestTable(String tableName) throws Exception {
		Table table = Table.tableBuilder().setTableName(tableName)
				.addTableField(new TableField("field1", TableFieldType.INTEGER))
				.addTableField(new TableField("field2", TableFieldType.REAL)).build();
		assertTrue(table != null);
		return table;
	}

	public void testContainsDbTable() throws Exception {
		Table table1 = createTestTable("table1");
		Table table2 = Table.tableBuilder().setTableName("table1")
				.addTableField(new TableField("field1", TableFieldType.LONGINT)).build();
		db.createTable(table1);
		assertTrue(db.contains(table2.getTableName()) == true);
		Table table3 = createTestTable("table2");
		assertTrue(db.contains(table3.getTableName()) == false);
	}

	public void testCreateRemoveTableDb() throws Exception {

		Boolean result = null;

		Table table1 = createTestTable("Table1");
		Table table2 = createTestTable("Table2");
		Table table3 = createTestTable("Table3");

		// Check creating
		result = db.createTable(table1);
		assertTrue(result == true);

		result = db.createTable(table1);
		assertTrue(result == false);

		result = db.createTable(table2);
		assertTrue(result == true);
		assertTrue(db.getTables().size() == 2);

		result = db.createTable(table3);

		// Check removing
		result = db.removeTable(table2);
		assertTrue(result == true);
		assertTrue(db.getTables().size() == 2);

		result = db.removeTable(table2);
		assertTrue(result == false);
		assertTrue(db.getTables().size() == 2);

	}

	public void testTableInstanceDb() throws Exception {

		Table MainTable = Table.tableBuilder().setTableName("MainTable")
				.addTableField(new TableField("field1", TableFieldType.INTEGER))
				.addTableField(new TableField("field2", TableFieldType.REAL)).build();

		Table OneToMainTable = Table.tableBuilder().setTableName("OneToMainTable")
				.addTableField(new TableField("field1", TableFieldType.REAL))
				.addTableField(new TableField("field2", TableFieldType.CHAR)).build();

		MainTable.addForeignKeyTable(OneToMainTable);
		OneToMainTable.addForeignKeyTable(MainTable);

		// Add TableInstance
		TableInstance tiForMainTable_1 = TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1", new Integer(15), TableFieldType.INTEGER))
				.addTableFieldInstance(new TableFieldInstance("tf2", new Double(15.5), TableFieldType.REAL)).build();
		TableInstance tiForMainTable_2 = TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1", new Integer(17), TableFieldType.INTEGER))
				.addTableFieldInstance(new TableFieldInstance("tf2", new Double(17.5), TableFieldType.REAL)).build();

		TableInstance tiForOneToMainTable_1 = TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1_1", new Double(5.23), TableFieldType.REAL))
				.addTableFieldInstance(new TableFieldInstance("tf2_2", new Character('a'), TableFieldType.CHAR))
				.build();
		TableInstance tiForOneToMainTable_2 = TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1_1", new Double(4.23), TableFieldType.REAL))
				.addTableFieldInstance(new TableFieldInstance("tf2_2", new Character('b'), TableFieldType.CHAR))
				.build();

		MainTable.addTableInstance(tiForMainTable_1);
		MainTable.addTableInstance(tiForMainTable_2);

		OneToMainTable.addTableInstance(tiForOneToMainTable_1);
		OneToMainTable.addTableInstance(tiForOneToMainTable_2);

		tiForMainTable_1.addForeignKeyTableInstances(tiForOneToMainTable_1);
		tiForOneToMainTable_1.addForeignKeyTableInstances(tiForMainTable_1);
		tiForMainTable_2.addForeignKeyTableInstances(tiForOneToMainTable_2);
		tiForOneToMainTable_2.addForeignKeyTableInstances(tiForMainTable_2);

		// Check ForeignKeyTableInstances
		assertTrue(tiForMainTable_1.getForeignKeyTableInstances().size() == 1);
		assertTrue(tiForMainTable_1.getForeignKeyTableInstances().contains(tiForOneToMainTable_1));
		assertTrue(tiForOneToMainTable_1.getForeignKeyTableInstances().size() == 1);
		assertTrue(tiForOneToMainTable_1.getForeignKeyTableInstances().contains(tiForMainTable_1));
	}

}
