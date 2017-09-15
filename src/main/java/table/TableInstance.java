package table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import table.field.TableFieldInstance;

public class TableInstance {

	private String index = null;
	private List<TableFieldInstance> fields = null;
	private Set<TableInstance> foreignKeyTableInstances = null;

	private TableInstance() {
		index = UUID.randomUUID().toString();
	}

	public String getIndex() {
		return index;
	}

	public List<TableFieldInstance> getFields() {
		return fields;
	}

	public Set<TableInstance> getForeignKeyTableInstances() {
		return foreignKeyTableInstances;
	}

	public void addForeignKeyTableInstances(TableInstance oneMoreForeignKeyTableInstance) {
		foreignKeyTableInstances.add(oneMoreForeignKeyTableInstance);
	}

	public boolean equals(Object object) {
		if (object == null)
			return false;
		if (this == object)
			return true;
		if (object instanceof TableInstance) {
			TableInstance tmpTableInstance = (TableInstance) object;
			for (int i = 0; i < this.fields.size(); ++i) {
				if (!this.fields.get(i).equals(tmpTableInstance.getFields().get(i)))
					return false;
			}
			return true;
		}
		return false;
	}

	public int hashCode() {
		int res = 0;
		for (TableFieldInstance tableField : fields) {
			res ^= tableField.hashCode();
		}
		return res;
	}

	public static TableInstanceBuilder tableInstanceBuilder() {
		return new TableInstance().new TableInstanceBuilder();
	}

	/*
	 * Create TableInstanceBuilder to build a table
	 */
	public class TableInstanceBuilder {

		private TableInstanceBuilder() {
			fields = new ArrayList<TableFieldInstance>();
			foreignKeyTableInstances = new HashSet<TableInstance>();
		}

		public TableInstanceBuilder addTableFieldInstance(TableFieldInstance tableFieldInstance) {
			TableInstance.this.fields.add(tableFieldInstance);
			return this;
		}

		public TableInstance build() throws Exception {
			return TableInstance.this;
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Index: " + index + "\n");
		for (TableFieldInstance tableField : fields) {
			stringBuilder.append(tableField);
		}
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}
}
