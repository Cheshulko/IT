package Lab.IT;

import db.table.Table;
import db.table.field.TableField;
import db.table.field.TableFieldType;
import junit.framework.TestCase;

public class TestRelation extends TestCase {

	protected void setUp() throws Exception {

	}

	protected void tearDown() throws Exception {

	}

	public void testRelationForTable() throws Exception {
		Table one = Table.tableBuilder().setTableName("One")
				.addTableField(new TableField("field1", TableFieldType.INTEGER))
				.addTableField(new TableField("field2", TableFieldType.CHAR)).build();
		Table two = Table.tableBuilder().setTableName("Two")
				.addTableField(new TableField("field1", TableFieldType.INTEGER))
				.addTableField(new TableField("field2", TableFieldType.STRING)).build();
		Table two_first = Table.tableBuilder().setTableName("Two_firt")
				.addTableField(new TableField("field1", TableFieldType.INTEGER))
				.addTableField(new TableField("field2", TableFieldType.STRING)).build();
		Table two_second = Table.tableBuilder().setTableName("Two_second")
				.addTableField(new TableField("field1", TableFieldType.INTEGER))
				.addTableField(new TableField("field2", TableFieldType.STRING)).build();

		// Test One to One relation
		one.addForeignKeyTable(two);
		two.addForeignKeyTable(one);
		assertTrue(one.getForeignKeyTables().size() == 1);
		assertTrue(two.getForeignKeyTables().size() == 1);

		one.addForeignKeyTable(two);
		assertTrue(one.getForeignKeyTables().size() == 1);

		// Test One to Many relation
		two.addForeignKeyTable(two_first);
		two.addForeignKeyTable(two_second);
		two_first.addForeignKeyTable(two);
		two_second.addForeignKeyTable(two);
		assertTrue(two.getForeignKeyTables().size() == 3);
		assertTrue(two_first.getForeignKeyTables().size() == 1);
		assertTrue(two_second.getForeignKeyTables().size() == 1);
	}
}
