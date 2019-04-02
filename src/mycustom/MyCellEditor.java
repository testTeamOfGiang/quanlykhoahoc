package mycustom;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MyCellEditor extends DefaultCellEditor {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1001699915610691749L;
	@SuppressWarnings("unused")
	private JTable table;
    @SuppressWarnings("unused")
	private LIH_TableModel model;

    public MyCellEditor(JTable table) {
        super(new JTextField());
        this.table = table;
        this.model = (LIH_TableModel) table.getModel();
    }

    /**
     * Nếu sửa đổi 1 ô cell thì có thể thay đổi status, value ở đây
     *
     * @return
     */
    @Override
    public boolean stopCellEditing() {
        // model.setStatus(table.getEditingRow(),table.getEditingColumn(), 1);
        return super.stopCellEditing();
    }
}
