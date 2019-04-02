package mycustom;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LIH_TableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 3045500088306616880L;

	Color backgroundColor = getBackground();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		LIH_TableModel model = (LIH_TableModel) table.getModel();
		if (model.getStatus(row, column) == 1) {
			c.setBackground(Color.orange);
		} else if (!isSelected) {
			c.setBackground(backgroundColor);
		}
//		else if (model.getStatus(row, column) == 0) {
//			//c.setBackground(Color.green);
//			c.setBackground(Color.LIGHT_GRAY);
//		}
		return c;
	}
	
	@Override
	public void setHorizontalAlignment(int alignment) {
		super.setHorizontalAlignment(JLabel.CENTER);
	}
}
