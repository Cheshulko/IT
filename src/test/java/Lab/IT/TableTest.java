package Lab.IT;

import junit.framework.TestCase;
import table.Table;
import table.TableInstance;
import table.field.TableField;
import table.field.TableFieldInstance;
import table.field.TableFieldType;

public class TableTest extends TestCase {

	private Table testTable = null;

	protected void setUp() throws Exception {
		testTable = Table.tableBuilder().setTableName("TestTable")
				.addTableField(new TableField("field1", TableFieldType.INTEGER))
				.addTableField(new TableField("field2", TableFieldType.REAL)).build();

		assertTrue(testTable != null);
	}

	protected void tearDown() throws Exception {

	}

	public void testAddTableInstance() throws Exception {
		Boolean result = null;

		// Test wrong indexTableField
		result = testTable.addTableInstance(TableInstance.tableInstanceBuilder().addTableFieldInstance(null).build());
		assertTrue(result == false);

		// Test wrong table fields
		result = testTable.addTableInstance(TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1", null, TableFieldType.STRING))
				.addTableFieldInstance(new TableFieldInstance("tf2", null, TableFieldType.REAL)).build());
		assertTrue(result == false);

		// Test OK situation
		result = testTable.addTableInstance(TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1", null, TableFieldType.INTEGER))
				.addTableFieldInstance(new TableFieldInstance("tf2", null, TableFieldType.REAL)).build());
		assertTrue(result == true);
	}
}
