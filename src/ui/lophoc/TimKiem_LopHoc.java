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
import utils.DateSQL;

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

		try {
			// 0 == Tìm theo tên
			if (type == 0) {
				List<LopHoc> list = new LopHocDAO().findByName(key);
				int stt = 1;
				for (LopHoc lh : list) {
					String[] tenKH_PH_GV = new LopHocDAO().getTenKH_PH_GV(lh.getId_KH(), lh.getId_PH(), lh.getId_GV());
					String ten_KH = tenKH_PH_GV[0];
					String ten_PH = tenKH_PH_GV[1];
					String ten_GV = tenKH_PH_GV[2];
					tableModel.addRow(new Object[] { stt, lh.getId_LH(), ten_KH, lh.getTen_LH(), ten_GV,
							DateSQL.toVNDate(lh.getNgaybatdau()), DateSQL.toVNDate(lh.getNgayketthuc()), ten_PH, lh.getSiso_LH(), lh.getGhichu_LH() });
					stt += 1;
				}

			} else {

				int id = Integer.parseInt(key);
				LopHoc lh = (new LopHocDAO().findById_LH(id));
				if (lh != null) {
					String[] tenKH_PH = new LopHocDAO().getTenKH_PH_GV(lh.getId_KH(), lh.getId_PH(), lh.getId_GV());
					String ten_KH = tenKH_PH[0];
					String ten_PH = tenKH_PH[1];
					String ten_GV = tenKH_PH[2];
					tableModel.addRow(new Object[] { 1, lh.getId_LH(), ten_KH, lh.getTen_LH(), ten_GV,
							DateSQL.toVNDate(lh.getNgaybatdau()), DateSQL.toVNDate(lh.getNgayketthuc()), ten_PH, lh.getSiso_LH(), lh.getGhichu_LH() });
				}

			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(TimKiem_LopHoc.this, "Lỗi khi kết nối CSDL!");
			e.printStackTrace();
		} catch (NumberFormatException e) {
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

		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã lớp", "Khoá học", "Tên lớp học",
				"Giảng viên", "Ngày bắt đầu", "Ngày kết thúc", "Phòng học", "Sĩ số", "Ghi chú" }) {
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
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			// 0: STT
			// 1: Mã lớp
			// 2: Tên khoá học
			// 3: Tên lớp học
			// 4: Tên giảng viên
			// 5: Ngày bắt đầu
			// 6: Ngày kết thúc
			// 7: Phòng học
			// 8: Sĩ số
			// 9: Ghi chú
			if (i == 0) {
				column.setMaxWidth(50);
			} else if (i == 1 || i == 8) {
				column.setMaxWidth(80);
			} else if (i == 7 || i == 5 || i == 6) {
				column.setWidth(100);
			} else {
				column.setPreferredWidth(150);
			}
		}

		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setBounds(0, 300, 1400, 500);
		add(jScrollPane);

	}
}
