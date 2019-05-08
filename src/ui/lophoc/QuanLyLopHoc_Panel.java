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
import utils.DateSQL;
import utils.PageRegulation;

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

	private void btnXoa_Click() {
		int current = table.getSelectedRow();
		if (current == -1) {
			JOptionPane.showMessageDialog(null, "Hãy chọn một lớp để xoá!");
		} else {
			int result = JOptionPane.showOptionDialog(QuanLyLopHoc_Panel.this, "Bạn có chắc muốn xoá?", "Xác nhận xoá",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Đồng ý", "Không" },
					JOptionPane.NO_OPTION);
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

	private void btnSua_Click() {
		int current = table.getSelectedRow();
		if (current == -1) {
			try {
				throw new ChuaChonException();
			} catch (ChuaChonException e1) {
				JOptionPane.showMessageDialog(null, "Bạn chưa chọn lớp học!");
			}
		} else {
			new Them_Sua_LopHoc_Dialog(QuanLyLopHoc_Panel.this, data.get(current)).setVisible(true);
		}
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
		btnTruoc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (page > 0) {
					page--;
					loadData();
				}
			}
		});
		add(btnTruoc);

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

		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(389, 676, 106, 40);
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Them_Sua_LopHoc_Dialog(QuanLyLopHoc_Panel.this, null).setVisible(true);
			}
		});
		add(btnThem);

		JButton btnSua = new JButton("Sửa");
		btnSua.setBounds(644, 676, 106, 40);
		btnSua.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSua_Click();
			}
		});
		add(btnSua);

		JButton btnXoa = new JButton("Xóa");
		btnXoa.setBounds(910, 676, 106, 40);
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnXoa_Click();
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

		try {
			List<LopHoc> lstLopHocs = new LopHocDAO().getPage(page);
			if (lstLopHocs.size() == 0) {
				page--;
				lstLopHocs = new LopHocDAO().getPage(page);
			}
			int stt = page * PageRegulation.LINES_PER_PAGE + 1;
			/*
			 * "STT", "Mã lớp", "Khoá học", "Tên Lớp Học", "Tên giảng viên", "Ngày học",
			 * "Phòng học", "Sĩ số", "Ghi chú"
			 */
			for (LopHoc lh : lstLopHocs) {
				String[] tenKH_PH_GV = new LopHocDAO().getTenKH_PH_GV(lh.getId_KH(), lh.getId_PH(), lh.getId_GV());
				String ten_KH = tenKH_PH_GV[0];
				String ten_PH = tenKH_PH_GV[1];
				String ten_GV = tenKH_PH_GV[2];
				tableModel.addRow(new Object[] { stt, lh.getId_LH(), ten_KH, lh.getTen_LH(), ten_GV,
						DateSQL.toVNDate(lh.getNgaybatdau()), DateSQL.toVNDate(lh.getNgayketthuc()), ten_PH,
						lh.getSiso_LH(), lh.getGhichu_LH() });
				data.put(stt - 1, lh);
				stt += 1;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(QuanLyLopHoc_Panel.this, "Lỗi kết nối tới CSDL!");
			e.printStackTrace();
		}
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

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1400, 550);
		add(scrollPane);
	}

}
