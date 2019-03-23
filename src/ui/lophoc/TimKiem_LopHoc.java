package ui.lophoc;

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
import javax.swing.table.TableColumn;

import dao.LopHocDAO;
import entity.LopHoc;
import ui.abstracts.AbstractTimKiemPanel;

public class TimKiem_LopHoc extends AbstractTimKiemPanel {

	private static final long serialVersionUID = 1287215789671796967L;
	private JTable table;
	private JComboBox<String> comboBox;
	private JTextField tfKey;
	private Font font;
	private DefaultTableModel tableModel;

	public TimKiem_LopHoc() {
		font = new Font("Tahoma", Font.PLAIN, 16);
		initButton_Combox();
		initTable();
		initTextFields_Labels();
	}

	@Override
	public void loadData() {
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		int type = comboBox.getSelectedIndex();
		String key = tfKey.getText().trim();
		if (key.equals("")) {
			return;
		}
		// Tìm theo tên
		try {
			if (type == 0) {
				List<LopHoc> list = new LopHocDAO().findByName(key);
				int stt = 1;
				for (LopHoc lh : list) {
					String[] tenKH_PH = new LopHocDAO().getTenKH_TenPH(lh.getId_KH(), lh.getId_PH());
					String ten_KH = tenKH_PH[0];
					String ten_PH = tenKH_PH[1];
					tableModel.addRow(new Object[] { stt, lh.getId_LH(), ten_KH, lh.getTen_LH(), lh.getNgaybatdau(),
							ten_PH, lh.getSiso_LH(), lh.getGhichu_LH() });
					stt += 1;
				}

			} else {

				int id = Integer.parseInt(key);
				LopHoc lh = (new LopHocDAO().findById_LH(id));
				if (lh != null) {
					String[] tenKH_PH = new LopHocDAO().getTenKH_TenPH(lh.getId_KH(), lh.getId_PH());
					String ten_KH = tenKH_PH[0];
					String ten_PH = tenKH_PH[1];
					tableModel.addRow(new Object[] { 1, lh.getId_LH(), ten_KH, lh.getTen_LH(), lh.getNgaybatdau(),
							ten_PH, lh.getSiso_LH(), lh.getGhichu_LH() });
				}

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(TimKiem_LopHoc.this, "Không thể kết nối tới CSDL!");
			e.printStackTrace();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(TimKiem_LopHoc.this, "Có lỗi xảy ra. Hãy liên hệ với nhóm Dev!");
			e.printStackTrace();
		}
	}

	private void initButton_Combox() {

		comboBox = new JComboBox<String>(new String[] { "Theo tên", "Theo mã" });
		comboBox.setFont(font);
		comboBox.setBounds(354, 188, 155, 40);
		add(comboBox);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(font);
		btnTimKiem.setBounds(893, 188, 155, 40);
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				loadData();
			}
		});
		add(btnTimKiem);

		JButton btnQuayLai = new JButton("Quay Lại");
		btnQuayLai.setBounds(34, 38, 114, 40);
		btnQuayLai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showQuanLy();
			}
		});
		add(btnQuayLai);

	}

	private void initTextFields_Labels() {

		JLabel lbTitle = new JLabel("Tìm Kiếm Lớp Học", JLabel.CENTER);
		lbTitle.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbTitle.setBounds(500, 53, 400, 60);
		add(lbTitle);

		tfKey = new JTextField();
		tfKey.setBounds(517, 188, 364, 40);
		add(tfKey);
		tfKey.setColumns(10);
	}

	private void initTable() {

		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã lớp", "Khoá học", "Tên Lớp Học",
				"Ngày học", "Phòng học", "Sĩ số", "Ghi chú" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setSize(1400, 500);
		table.setRowHeight(40);

		TableColumn column;
		for (int i = 0; i < 8; i++) {
			column = table.getColumnModel().getColumn(i);
			// 0: STT
			// 1: Mã lớp
			// 2: Tên khoá học
			// 3: Tên lớp học
			// 4: Ngày học
			// 5: Phòng học
			// 6: Sĩ số
			// 7: Ghi chú
			if (i == 0 || i == 1 || i == 6) {
				column.setPreferredWidth(40);
			} else {
				column.setPreferredWidth(150);
			}
		}

		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setBounds(0, 300, 1400, 500);
		add(jScrollPane);

	}
}
