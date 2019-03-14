package ui.khoahoc;

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

import entity.Khoahoc;
import main.MainApp;
import ui.abstracts.AbstractTimKiemPanel;

public class TimKiemKhoaHoc_Panel extends AbstractTimKiemPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JTable table;
	private DefaultTableModel tableModel;

	public TimKiemKhoaHoc_Panel() {

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã Khóa Học", "Tên Khóa Học", "Giá Khóa Học" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 300, 1400, 500);
		add(scrollPane);

		JLabel lblTmKimGing = new JLabel("Tìm Kiếm Khóa Học", JLabel.CENTER);
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
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		add(btnTmKim);

		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.setBounds(116, 103, 113, 40);
		btnQuayLi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showQuanLy();
			}
		});
		add(btnQuayLi);

		textField = new JTextField();
		textField.setBounds(517, 188, 364, 40);
		add(textField);
		textField.setColumns(10);
	}

	@Override
	public void loadData() {
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		String key = textField.getText().trim();
		if (!key.equals("")) {
			if (comboBox.getSelectedIndex() == 0) {
				try {
					List<Khoahoc> list = MainApp.khoaHocDao.find(key);
					int stt = 1;
					for (Khoahoc kh : list) {
						tableModel.addRow(new Object[] { stt, kh.getId_KH(), kh.getTen_KH(), kh.getGia_KH() });
						stt += 1;
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(TimKiemKhoaHoc_Panel.this, "Không Thể Load Dữ Liệu");
					e.printStackTrace();
				}
			} else {
				try {
					int id = Integer.parseInt(key);
					Khoahoc kh = MainApp.khoaHocDao.findById(id);
					int stt = 1;
					tableModel.addRow(new Object[] { stt, kh.getId_KH(), kh.getTen_KH(), kh.getGia_KH() });
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(TimKiemKhoaHoc_Panel.this, "Mã Khóa Học Phải Là Số");
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(TimKiemKhoaHoc_Panel.this, "Không Thể Load Dữ Liệu");
					e.printStackTrace();
				}
			}
		}
	}

}
