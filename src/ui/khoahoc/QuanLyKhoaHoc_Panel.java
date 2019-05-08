package ui.khoahoc;

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

import entity.Khoahoc;
import exception.ThieuThongTinException;
import main.MainApp;
import ui.abstracts.AbsTractQuanLyPanel;
import ui.khoahoc.KhoaHoc_Dialog.Type;

public class QuanLyKhoaHoc_Panel extends AbsTractQuanLyPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int page;
	private Map<Integer, Khoahoc> data;

	public QuanLyKhoaHoc_Panel() {
		page = 0;
		data = new HashMap<Integer, Khoahoc>();
		initComponent();
		loadData();
	}

	public void initComponent() {
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã Khóa Học", "Tên Khóa Học", "Giá Khóa Học" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JButton btnTrc = new JButton("Trước");
		btnTrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(page>0) {
					page--;
					loadData();
				}
			}
		});
		btnTrc.setBounds(492, 582, 106, 40);
		add(btnTrc);
		table = new JTable(tableModel);
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int current = table.getSelectedRow();
					Khoahoc kh = data.get(current);
					containerPanel.setObject(kh);
					containerPanel.showChiTiet();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1400, 550);
		add(scrollPane);

		

		JButton btnSau = new JButton("Sau");
		btnSau.setBounds(801, 582, 106, 40);
		btnSau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				page++;
				loadData();
			}
		});
		add(btnSau);

		JButton btnThm = new JButton("Thêm");
		btnThm.setBounds(389, 676, 106, 40);
		btnThm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new KhoaHoc_Dialog(QuanLyKhoaHoc_Panel.this, Type.ADD, null).setVisible(true);
			}
		});
		add(btnThm);

		JButton btnSa = new JButton("Sửa");
		btnSa.setBounds(644, 676, 106, 40);
		btnSa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int current = table.getSelectedRow();
					if (current == -1) {
						throw new ThieuThongTinException();
					}
					Khoahoc kh = data.get(current);
					new KhoaHoc_Dialog(QuanLyKhoaHoc_Panel.this, Type.UPDATE, kh).setVisible(true);
				} catch (ThieuThongTinException ex) {
					JOptionPane.showMessageDialog(QuanLyKhoaHoc_Panel.this, "Hãy Chọn Một Khóa Học Để Sửa");
				}
			}
		});
		add(btnSa);

		JButton btnXa = new JButton("Xóa");
		btnXa.setBounds(910, 676, 106, 40);
		btnXa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int current = table.getSelectedRow();
					if (current == -1) {
						throw new ThieuThongTinException();
					}
					int confirm = JOptionPane.showConfirmDialog(QuanLyKhoaHoc_Panel.this, "ban co muon xoa khoa hoc");
					if (confirm == JOptionPane.YES_OPTION) {
						Khoahoc khoaHoc = data.get(current);
						MainApp.khoaHocDao.delete(khoaHoc);
						loadData();
						JOptionPane.showMessageDialog(QuanLyKhoaHoc_Panel.this, "Xóa Khóa Học  Thành Công");
					}
				} catch (ThieuThongTinException ex) {
					JOptionPane.showMessageDialog(QuanLyKhoaHoc_Panel.this, "Hãy Chọn Một Khóa Học Để Xóa");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(QuanLyKhoaHoc_Panel.this, "Xóa Khóa Học Không Thành Công");
					e1.printStackTrace();
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
	}

	@Override
	public void loadData() {
		data.clear();
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		try {
			List<Khoahoc> list = MainApp.khoaHocDao.getPage(page);
			if(list.size()==0) {
				page--;
				list=MainApp.khoaHocDao.getPage(page);
			}
			int stt = 1;
			for (Khoahoc kh : list) {
				tableModel.addRow(new Object[] { stt, kh.getId_KH(), kh.getTen_KH(), kh.getGia_KH() });
				data.put(stt - 1, kh);
				stt += 1;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(QuanLyKhoaHoc_Panel.this, "Không thể Load dữ liệu");
			e.printStackTrace();
		}
	}

}
