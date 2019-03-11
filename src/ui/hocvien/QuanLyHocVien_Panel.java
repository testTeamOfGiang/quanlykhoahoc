package ui.hocvien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.Hocvien;
import exception.ChuaChonException;
import main.MainApp;
import ui.abstracts.AbsTractQuanLyPanel;

public class QuanLyHocVien_Panel extends AbsTractQuanLyPanel {

	private static final long serialVersionUID = 1L;
	private int page;
	private JTable table;
	private DefaultTableModel tableModel;
	private Map<Integer, Hocvien> data;

	public QuanLyHocVien_Panel() {
		page = 0;
		data = new HashMap<Integer, Hocvien>();

		/* ======================================= */
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã Học Viên", "Tên Học Viên", "Số Điện Thoại", "Địa Chỉ" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(40);

		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setBounds(0, 0, 1400, 550);
		add(jScrollPane);

		/* ======================================= */
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1400, 550);
		add(panel);

		JButton btnTrc = new JButton("Trước");
		btnTrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTrc.setBounds(492, 582, 106, 40);
		add(btnTrc);

		JButton btnSau = new JButton("Sau");
		btnSau.setBounds(801, 582, 106, 40);
		add(btnSau);

		JButton btnThm = new JButton("Thêm");
		btnThm.setBounds(389, 676, 106, 40);
		add(btnThm);

		JButton btnSa = new JButton("Sửa");
		btnSa.setBounds(644, 676, 106, 40);
		add(btnSa);

		JButton btnXa = new JButton("Xóa");
		btnXa.setBounds(910, 676, 106, 40);
		btnXa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int current = table.getSelectedRow();
				if (current == -1) {
					try {
						throw new ChuaChonException();
					} catch (ChuaChonException e1) {
						JOptionPane.showMessageDialog(null, "Hãy Chọn Học Viên Muốn Xóa");
						e1.printStackTrace();
					}
				} else {
					Hocvien hv = data.get(current);
					try {
						MainApp.hocVienDao.delete(hv);
						loadData();
						JOptionPane.showConfirmDialog(null, "Xóa Học Viên Thành Công");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Xóa Không Thành Công");
						e1.printStackTrace();
					}
				}
			}
		});
		add(btnXa);

		JButton btnTmKim = new JButton("Tìm Kiếm");
		btnTmKim.setBounds(1106, 676, 97, 40);
		btnTmKim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showTimKiem();
			}
		});
		add(btnTmKim);

		JButton btnReload = new JButton("Reload");
		btnReload.setBounds(177, 677, 97, 39);
		btnReload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		add(btnReload);

		loadData();
	}

	@Override
	public void loadData() {
		data.clear();

		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		try {
			List<Hocvien> hocViens = MainApp.hocVienDao.getPage(page);
			int stt = 1;
			for (Hocvien hv : hocViens) {
				tableModel.addRow(
						new Object[] { stt, hv.getId_HV(), hv.getTen_HV(), hv.getSodt_HV(), hv.getDiachi_HV() });
				data.put(stt - 1, hv);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không Thể load dữ liệu");
			e.printStackTrace();
		}

	}

}
