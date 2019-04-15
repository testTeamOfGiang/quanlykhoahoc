package mycustom;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class LIH_TableModel extends DefaultTableModel {

	public LIH_TableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	public int getStatus(int row, int col) {
		return ((LIH_Cell) dataVector.get(row).get(col)).getStatus();
	}

	@Override
	public int getRowCount() {
		return dataVector.size();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return ((LIH_Cell) dataVector.get(row).get(col)).getValue();

	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		((LIH_Cell) dataVector.get(row).get(column)).setValue((String) aValue);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

}
