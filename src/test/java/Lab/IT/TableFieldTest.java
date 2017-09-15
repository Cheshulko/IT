package Lab.IT;

import junit.framework.TestCase;
import table.field.TableFieldInstance;
import table.field.TableFieldType;

public class TableFieldTest extends TestCase {

	public void testEqualsTableField() {
		TableFieldInstance tf1 = new TableFieldInstance("tf1", new Integer(12), TableFieldType.INTEGER);
		TableFieldInstance tf1_1 = new TableFieldInstance("tf1", new Integer(12), TableFieldType.INTEGER);
		TableFieldInstance tf2 = new TableFieldInstance("tf2", new Integer(15), TableFieldType.INTEGER);

		TableFieldInstance tf3 = new TableFieldInstance("tf3", new Character('a'), TableFieldType.CHAR);
		TableFieldInstance tf3_3 = new TableFieldInstance("tf3", new Character('a'), TableFieldType.CHAR);
		TableFieldInstance tf4 = new TableFieldInstance("tf4", new Character('b'), TableFieldType.CHAR);

		TableFieldInstance tf5 = new TableFieldInstance("tf5", new Double(1.5), TableFieldType.REAL);
		TableFieldInstance tf5_5 = new TableFieldInstance("tf5", new Double(1.5), TableFieldType.REAL);
		TableFieldInstance tf6 = new TableFieldInstance("tf6", new Double(2.5), TableFieldType.REAL);

		TableFieldInstance tf7 = new TableFieldInstance("tf7", new Long(15), TableFieldType.LONGINT);
		TableFieldInstance tf7_7 = new TableFieldInstance("tf7", new Long(15), TableFieldType.LONGINT);
		TableFieldInstance tf8 = new TableFieldInstance("tf8", new Long(17), TableFieldType.LONGINT);

		// FIXME Add HTML

		assertTrue(tf1.equals(tf1_1));
		assertTrue(!tf1.equals(tf2));

		assertTrue(tf3.equals(tf3_3));
		assertTrue(!tf3.equals(tf4));

		assertTrue(tf5.equals(tf5_5));
		assertTrue(!tf5.equals(tf6));

		assertTrue(tf7.equals(tf7_7));
		assertTrue(!tf7.equals(tf8));

		assertTrue(!tf1.equals(tf3));
		assertTrue(!tf3.equals(tf5));
		assertTrue(!tf5.equals(tf7));
		assertTrue(!tf1.equals(tf7));

		assertTrue(tf1.equals(tf1));
	}
}
