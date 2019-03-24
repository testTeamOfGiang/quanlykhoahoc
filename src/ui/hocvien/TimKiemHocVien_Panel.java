package ui.hocvien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Hocvien;
import main.MainApp;
import ui.abstracts.AbstractTimKiemPanel;

public class TimKiemHocVien_Panel extends AbstractTimKiemPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComboBox<String> comboBox;
	private Map<Integer, Hocvien> data;

	public TimKiemHocVien_Panel() {
		data = new HashMap<Integer, Hocvien>();
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã Học Viên", "Tên Học Viên", "Số Điện Thoại", "Địa Chỉ" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int current = table.getSelectedRow();
					Hocvien hv = data.get(current);
					containerPanel.setObject(hv);
					containerPanel.showChiTiet();
				}
			}
		});
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 300, 1400, 500);
		add(scrollPane);

		/* ============================= */
		JLabel lblTmKimGing = new JLabel("Tìm Kiếm Học Viên", JLabel.CENTER);
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
		data.clear();
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		String key = textField.getText().trim();
		if (!key.equals("")) {
			if (comboBox.getSelectedIndex() == 0) {
				try {
					List<Hocvien> hocViens = MainApp.hocVienDao.find(key);
					int stt = 1;
					for (Hocvien hv : hocViens) {
						tableModel.addRow(new Object[] { stt, hv.getId_HV(), hv.getTen_HV(), hv.getSodt_HV(),
								hv.getDiachi_HV() });
						data.put(stt - 1, hv);
						stt += 1;
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(TimKiemHocVien_Panel.this, "Không Thể Lấy Dữ Liệu");
					e.printStackTrace();
				}
			} else {
				try {
					int id = Integer.parseInt(key);
					Hocvien hv = MainApp.hocVienDao.findById(id);
					if (hv != null) {
						tableModel.addRow(
								new Object[] { 1, hv.getId_HV(), hv.getTen_HV(), hv.getSodt_HV(), hv.getDiachi_HV() });
						data.put(0, hv);
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(TimKiemHocVien_Panel.this, "Không Thể Lấy Dữ Liệu");
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(TimKiemHocVien_Panel.this, "Mã học viên phải là số");
				}
			}
		}
	}

}
