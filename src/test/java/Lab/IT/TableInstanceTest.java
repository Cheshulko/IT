package Lab.IT;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import table.TableInstance;
import table.field.TableFieldInstance;
import table.field.TableFieldType;

public class TableInstanceTest extends TestCase {

	public void testEqualsTableInstance() throws Exception {
		TableInstance ti1 = TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1", new Integer(1), TableFieldType.INTEGER))
				.addTableFieldInstance(new TableFieldInstance("tf2", new Double(1.0), TableFieldType.REAL)).build();

		TableInstance ti2 = TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1", new Integer(1), TableFieldType.INTEGER))
				.addTableFieldInstance(new TableFieldInstance("tf2", new Double(1.0), TableFieldType.REAL)).build();

		Set<TableInstance> st = new HashSet<TableInstance>();
		st.add(ti1);
		st.add(ti2);
		assertTrue(st.size() == 1);

		TableInstance ti3 = TableInstance.tableInstanceBuilder()
				.addTableFieldInstance(new TableFieldInstance("tf1", new Integer(1), TableFieldType.INTEGER))
				.addTableFieldInstance(new TableFieldInstance("tf2", new Long(12), TableFieldType.LONGINT)).build();

		st.add(ti3);
		assertTrue(st.size() == 2);
	}
}
