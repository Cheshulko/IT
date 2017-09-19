package Lab.IT;

import db.table.Table;
import db.table.field.base.BaseField;
import db.table.field.base.BaseFieldType;
import junit.framework.TestCase;

public class TestRelation extends TestCase {

	protected void setUp() throws Exception {

	}

	protected void tearDown() throws Exception {

	}

	public void testRelationForTable() throws Exception {
		Table one = Table.tableBuilder().setTableName("One")
				.addTableBaseField(new BaseField("field1", BaseFieldType.INTEGER))
				.addTableBaseField(new BaseField("field2", BaseFieldType.CHAR)).build();
		Table two = Table.tableBuilder().setTableName("Two")
				.addTableBaseField(new BaseField("field1", BaseFieldType.INTEGER))
				.addTableBaseField(new BaseField("field2", BaseFieldType.STRING)).build();
		Table two_first = Table.tableBuilder().setTableName("Two_firt")
				.addTableBaseField(new BaseField("field1", BaseFieldType.INTEGER))
				.addTableBaseField(new BaseField("field2", BaseFieldType.STRING)).build();
		Table two_second = Table.tableBuilder().setTableName("Two_second")
				.addTableBaseField(new BaseField("field1", BaseFieldType.INTEGER))
				.addTableBaseField(new BaseField("field2", BaseFieldType.STRING)).build();

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
