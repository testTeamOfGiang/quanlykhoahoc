package ui.lophoc;

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
import javax.swing.table.TableColumn;

import dao.LopHocDAO;
import entity.LopHoc;
import exception.ChuaChonException;
import ui.abstracts.AbsTractQuanLyPanel;

public class QuanLyLopHoc_Panel extends AbsTractQuanLyPanel {

	private static final long serialVersionUID = 1577833518232687077L;

	private JTable table;
	private DefaultTableModel tableModel;
	private int page;
	private Map<Integer, LopHoc> data;

	public QuanLyLopHoc_Panel() {
		page = 0;
		data = new HashMap<Integer, LopHoc>();
		initComponent();
		loadData();
	}

	private void initComponent() {
		initTable();
		initButton();
		loadData();
	}

	private void initButton() {
		JButton btnTruoc = new JButton("Trước");
		btnTruoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTruoc.setBounds(492, 582, 106, 40);
		add(btnTruoc);

		JButton btnSau = new JButton("Sau");
		btnSau.setBounds(801, 582, 106, 40);
		add(btnSau);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(389, 676, 106, 40);
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LopHoc_Diaglog(QuanLyLopHoc_Panel.this, null).setVisible(true);
			}
		});
		add(btnThem);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(644, 676, 106, 40);
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int current = table.getSelectedRow();
				if (current == -1) {
					try {
						throw new ChuaChonException();
					} catch (ChuaChonException e1) {
						JOptionPane.showMessageDialog(null, "Bạn chưa chọn lớp học!");
					}
				} else {
					new LopHoc_Diaglog(QuanLyLopHoc_Panel.this, data.get(current)).setVisible(true);
				}
			}
		});
		add(btnSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBounds(910, 676, 106, 40);
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int current = table.getSelectedRow();
				if (current == -1) {
					JOptionPane.showMessageDialog(null, "Hãy chọn một lớp để xoá!");
				} else {
					int result = JOptionPane.showOptionDialog(QuanLyLopHoc_Panel.this, "Bạn có chắc muốn xoá?",
							"Xác nhận xoá", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new String[] { "Đồng ý", "Không" }, JOptionPane.NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						LopHoc lh = data.get(current);
						try {
							new LopHocDAO().deleteLopHoc(lh);
							loadData();
							JOptionPane.showMessageDialog(QuanLyLopHoc_Panel.this, "Xóa lớp học thành công!");
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(QuanLyLopHoc_Panel.this, "Lỗi! Không thể kết nối CSDL!");
							e1.printStackTrace();
						}
					}

				}
			}

		});
		add(btnXoa);

		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBounds(1106, 676, 97, 40);
		btnTimKiem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showTimKiem();
			}
		});
		add(btnTimKiem);

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
		/* ================== */

		try {
			List<LopHoc> lstLopHocs = new LopHocDAO().getPage(page);
			int stt = 1;
			/*
			 * "STT", "Mã lớp", "Khoá học", "Tên Lớp Học", "Ngày học", "Phòng học", "Sĩ số",
			 * "Ghi chú"
			 */
			for (LopHoc lh : lstLopHocs) {
				String[] tenKH_PH = new LopHocDAO().getTenKH_TenPH(lh.getId_KH(), lh.getId_PH());
				String ten_KH = tenKH_PH[0];
				String ten_PH = tenKH_PH[1];
				tableModel.addRow(new Object[] { stt, lh.getId_LH(), ten_KH, lh.getTen_LH(), lh.getNgaybatdau(),
						lh.getNgayketthuc(), ten_PH, lh.getSiso_LH(), lh.getGhichu_LH() });
				data.put(stt - 1, lh);
				stt += 1;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(QuanLyLopHoc_Panel.this, "Lỗi kết nối tới CSDL!");
			e.printStackTrace();
		}
	}

	private void initTable() {
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã lớp", "Khoá học", "Tên Lớp Học",
				"Ngày bắt đầu", "Ngày kết thúc", "Phòng học", "Sĩ số", "Ghi chú" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int current = table.getSelectedRow();
					LopHoc lh = data.get(current);
					containerPanel.setObject(lh);
					containerPanel.showChiTiet();
				}
			}
		});

		TableColumn column;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			// 0: STT
			// 1: Mã lớp
			// 2: Tên khoá học
			// 3: Tên lớp học
			// 4: Ngày bắt đầu
			// 5: Ngày kết thúc
			// 6: Phòng học
			// 7: Sĩ số
			// 8: Ghi chú
			if (i == 0 || i == 1 || i == 7) {
				column.setPreferredWidth(20);
			} else if (i == 6) {
				column.setPreferredWidth(45);
			} else {
				column.setPreferredWidth(150);
			}
		}

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1400, 550);
		add(scrollPane);
	}

}