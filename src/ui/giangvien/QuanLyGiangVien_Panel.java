package ui.giangvien;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.Giangvien;
import exception.ChuaChonException;
import main.MainApp;
import ui.abstracts.AbsTractQuanLyPanel;
import ui.giangvien.SubDialog.Type;

public class QuanLyGiangVien_Panel extends AbsTractQuanLyPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private Map<Integer, Giangvien> data;
	private int page;

	public QuanLyGiangVien_Panel() {
		data = new HashMap<Integer, Giangvien>();
		page = 0;

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã giảng viên", "Ten giảng viên", "Ngày sinh", "Số đt", "Địa chỉ" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table = new JTable(tableModel);
		table.setSize(1400, 550);
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int current = table.getSelectedRow();
					Giangvien gv = data.get(current);
					containerPanel.setObject(gv);
					containerPanel.showChiTiet();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1400, 550);
		add(scrollPane);

		/* ========================================== */

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
		btnThm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SubDialog(Type.ADD, QuanLyGiangVien_Panel.this, null).setVisible(true);
			}
		});
		add(btnThm);

		JButton btnSa = new JButton("Sửa");
		btnSa.setBounds(644, 676, 106, 40);
		btnSa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int current = table.getSelectedRow();
				if (current == -1) {
					try {
						throw new ChuaChonException();
					} catch (ChuaChonException e1) {
						JOptionPane.showMessageDialog(null, "Hãy Chọn một giảng viên để sửa");
					}
				} else {
					new SubDialog(Type.UPDATE, QuanLyGiangVien_Panel.this, data.get(current)).setVisible(true);
					;
				}
			}
		});
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
						JOptionPane.showMessageDialog(null, "Hãy chọn một giảng viên muốn xóa");
						e1.printStackTrace();
					}
				} else {
					Giangvien gv = data.get(current);
					try {
						MainApp.giangVienDao.delete(gv);
						loadData();
						JOptionPane.showMessageDialog(null, "Xóa giảng viên thành công");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Gặp lỗi lúc xóa giảng viên");
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

		/* ===================== */

		loadData();
	}

	@Override
	public void loadData() {
		data.clear();
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		/* ================== */

		try {
			List<Giangvien> giangViens = MainApp.giangVienDao.getPage(page);
			int stt = 1;
			for (Giangvien gv : giangViens) {
				tableModel.addRow(new Object[] { stt, gv.getId_GV(), gv.getTen_GV(), gv.getNgaysinh_GV(),
						gv.getSodt_GV(), gv.getDiachi_GV() });
				data.put(stt - 1, gv);
				stt += 1;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi lúc load dữ liệu");
			e.printStackTrace();
		}
	}

}
