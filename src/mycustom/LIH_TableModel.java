package mycustom;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class LIH_TableModel extends DefaultTableModel {

	public LIH_TableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	@SuppressWarnings("unchecked")
	public int getStatus(int row, int col) {
		return ((Vector<Vector<LIH_Cell>>) dataVector).get(row).get(col).getStatus();
	}

	@Override
	public int getRowCount() {
		return dataVector.size();
	}

	@Override
	public int getColumnCount() {
		return 8;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getValueAt(int row, int col) {
		return ((Vector<Vector<LIH_Cell>>) dataVector).get(row).get(col).getValue();

	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValueAt(Object aValue, int row, int column) {
		((Vector<Vector<LIH_Cell>>) dataVector).get(row).get(column).setValue((String) aValue);
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
