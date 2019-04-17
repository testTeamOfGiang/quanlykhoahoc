package ui.giangvien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Giangvien;
import main.MainApp;
import ui.abstracts.AbstractTimKiemPanel;
import utils.DateSQL;

public class TimKiemGiangVien_Panel extends AbstractTimKiemPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> comboBox;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField textField;

	public TimKiemGiangVien_Panel() {

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã giảng viên", "Ten giảng viên", "Ngày sinh", "Số đt", "Địa chỉ" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setSize(1400, 500);
		table.setRowHeight(40);

		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setBounds(0, 300, 1400, 500);
		add(jScrollPane);

		/* =================== */
		JLabel lblTmKimGing = new JLabel("Tìm Kiếm Giảng Viên", JLabel.CENTER);
		lblTmKimGing.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTmKimGing.setBounds(500, 53, 400, 60);
		add(lblTmKimGing);

		comboBox = new JComboBox<String>(new String[] { "Theo Tên", "Theo Mã" });
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(354, 188, 155, 40);
		add(comboBox);

		JButton btnTmKim = new JButton("Tìm Kiếm");
		btnTmKim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTmKim.setBounds(893, 188, 155, 40);
		btnTmKim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		add(btnTmKim);

		textField = new JTextField();
		textField.setBounds(517, 188, 364, 40);
		add(textField);
		textField.setColumns(10);

		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.setBounds(116, 103, 113, 40);
		btnQuayLi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showQuanLy();
			}
		});
		add(btnQuayLi);
	}

	@Override
	public void loadData() {
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int type = comboBox.getSelectedIndex();
		String key = textField.getText().trim();
		if (key.equals("")) {
			return;
		}
		if (type == 0) {
			try {
				List<Giangvien> list = MainApp.giangVienDao.find(key);
				int stt = 1;
				for (Giangvien gv : list) {
					tableModel.addRow(new Object[] { stt, gv.getId_GV(), gv.getTen_GV(), gv.getNgaysinh_GV(),
							gv.getSodt_GV(), gv.getDiachi_GV() });
					stt += 1;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Không thể load dữ liệu");
				e.printStackTrace();
			}
		} else {

			try {
				int id = Integer.parseInt(key);
				Giangvien gv = MainApp.giangVienDao.findByID(id);
				if (gv != null) {
					tableModel.addRow(new Object[] { 1, gv.getId_GV(), gv.getTen_GV(),
							DateSQL.toVNDate(gv.getNgaysinh_GV()), gv.getSodt_GV(), gv.getDiachi_GV() });
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Không thể thực hiện");
			}
		}
	}

}
