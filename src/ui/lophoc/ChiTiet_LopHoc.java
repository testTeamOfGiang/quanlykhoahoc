package ui.lophoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import dao.GiangVienDao;
import dao.HocVien_LopHocDAO;
import dao.KhoaHocDao;
import dao.LopHocDAO;
import dao.PhongHocDao;
import entity.HocVien_LopHoc;
import entity.LopHoc;
import ui.abstracts.AbsTractChiTietPanel;

public class ChiTiet_LopHoc extends AbsTractChiTietPanel {

	private static final long serialVersionUID = -6867270404171857479L;
	private JTable table;
	private DefaultTableModel tbHocVien;
	private DefaultTableModel tbLichHoc;
	private Font font;
	/*
	 * 0 = đang hiển thị DSHV, 1 = đang hiển thị Lich hoc
	 */
	private int status;
	private JLabel lbSiso_LH;
	private JLabel lbTen_LH;
	private JLabel lbID_LH;
	private JLabel lbTen_PH;
	private JLabel lbTen_GV;
	private JLabel lbTen_KH;
	private JTextArea taGhiChu_LH;
	private HashMap<Integer, LopHoc> data;
	private int page;

	public ChiTiet_LopHoc() {
		font = new Font("Tahoma", Font.PLAIN, 16);
		page = 0;
		this.status = 1;

		initLabels();
		initTables();
		initButons();
		onTableHocVien();
	}

	private void initButons() {

		JButton btnQuayLai = new JButton("Quay Lại");
		btnQuayLai.setBounds(34, 38, 114, 40);
		btnQuayLai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showQuanLy();
			}
		});
		add(btnQuayLai);

		JButton btnXemDSHV = new JButton("Xem DS Học viên");
		btnXemDSHV.setBounds(500, 330, 125, 40);
		btnXemDSHV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onTableHocVien();
			}
		});
		add(btnXemDSHV);

		JButton btnXemLIH = new JButton("Xem lịch học");
		btnXemLIH.setBounds(794, 330, 125, 40);
		btnXemLIH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onTableLichHoc();
			}
		});
		add(btnXemLIH);

		JButton btnSuaLIH = new JButton("Sửa lịch học");
		btnSuaLIH.setBounds(1034, 330, 125, 40);
		add(btnSuaLIH);

	}

	private void onTableLichHoc() {
		// Đang hiển thị rồi thì thôi. 1 là lịch học
		if (this.status == 1)
			return;
		this.status = 1;

		tbLichHoc = new DefaultTableModel(new Object[][] {},
				new String[] { "", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật" }) {

			private static final long serialVersionUID = 179939617947351123L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.setModel(tbLichHoc);

	}

	private void onTableHocVien() {
		if (this.status == 0)
			return;
		else
			this.status = 0;
		// = 0 là đang hiển thị Học viên

		table.setModel(tbHocVien);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		TableColumn column;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			// 0: STT // 4: Điểm 2
			// 1: Mã // 5: Điểm 3
			// 2: Tên // 6: Tổng kết
			// 3: Điểm 1 // 7: Ghi chú
			column.setCellRenderer(centerRenderer);
			if (i == 0) {
				column.setMaxWidth(50);
			} else if (i == 1) {
				column.setMaxWidth(80);
			} else if (i == 3 || i == 4 || i == 5 || i == 6) {
				column.setMaxWidth(250);
				column.setPreferredWidth(150);
			} else {
				column.setMaxWidth(400);
				column.setPreferredWidth(300);
			}
		}
	}

	private void initTables() {

		// Table Hoc Vien
		tbHocVien = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã", "Họ tên", "Điểm số 1",
				"Điểm số 2", "Điểm số 3", "Điểm tổng kết", "Ghi chú" }) {

			private static final long serialVersionUID = 7000351210690643616L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable();
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 400, 1400, 400);
		add(scrollPane);

	}

	private void initLabels() {

		JLabel chiTietLopHoc = new JLabel("Chi Tiết Lớp Học", JLabel.CENTER);
		chiTietLopHoc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		chiTietLopHoc.setBounds(500, 38, 400, 70);
		add(chiTietLopHoc);

		lbSiso_LH = new JLabel("...");
		lbSiso_LH.setFont(font);
		lbSiso_LH.setBounds(293, 242, 155, 40);
		add(lbSiso_LH);

		lbTen_LH = new JLabel("...");
		lbTen_LH.setFont(font);
		lbTen_LH.setBounds(293, 191, 155, 40);
		add(lbTen_LH);

		lbID_LH = new JLabel("...");
		lbID_LH.setFont(font);
		lbID_LH.setBounds(293, 140, 155, 40);
		add(lbID_LH);

		lbTen_PH = new JLabel("...");
		lbTen_PH.setFont(font);
		lbTen_PH.setBounds(619, 242, 190, 40);
		add(lbTen_PH);

		lbTen_GV = new JLabel("...");
		lbTen_GV.setFont(font);
		lbTen_GV.setBounds(619, 191, 190, 40);
		add(lbTen_GV);

		lbTen_KH = new JLabel("...");
		lbTen_KH.setFont(font);
		lbTen_KH.setBounds(619, 140, 190, 40);
		add(lbTen_KH);

		JLabel maLop = new JLabel("Mã lớp");
		maLop.setFont(font);
		maLop.setBounds(174, 140, 94, 40);
		add(maLop);

		JLabel tenLop = new JLabel("Tên lớp");
		tenLop.setFont(font);
		tenLop.setBounds(174, 191, 94, 40);
		add(tenLop);

		JLabel siSo = new JLabel("Sĩ số");
		siSo.setFont(font);
		siSo.setBounds(174, 242, 94, 40);
		add(siSo);

		JLabel khoaHoc = new JLabel("Khoá học");
		khoaHoc.setFont(font);
		khoaHoc.setBounds(500, 140, 94, 40);
		add(khoaHoc);

		JLabel giangVien = new JLabel("Giảng viên");
		giangVien.setFont(font);
		giangVien.setBounds(500, 191, 94, 40);
		add(giangVien);

		JLabel phongHoc = new JLabel("Phòng học");
		phongHoc.setFont(font);
		phongHoc.setBounds(500, 242, 94, 40);
		add(phongHoc);

		JLabel ghichu = new JLabel("Ghi chú");
		ghichu.setFont(font);
		ghichu.setBounds(919, 140, 94, 40);
		add(ghichu);

		taGhiChu_LH = new JTextArea();
		taGhiChu_LH.setTabSize(4);
		taGhiChu_LH.setLineWrap(true);
		taGhiChu_LH.setWrapStyleWord(true);
		taGhiChu_LH.setFont(font);
		taGhiChu_LH.setEditable(false);

		JScrollPane scrollPane1 = new JScrollPane(taGhiChu_LH, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setSize(300, 91);
		scrollPane1.setLocation(919, 191);
		add(scrollPane1);

		loadData();
	}

	@Override
	public void loadData() {

		if (obj != null) {
			LopHoc lopHoc = (LopHoc) obj;

			lbID_LH.setText(lopHoc.getId_LH() + "");
			lbSiso_LH.setText(lopHoc.getSiso_LH() + "");
			try {
				lbTen_GV.setText(new GiangVienDao().findByID(lopHoc.getId_GV()).getTen_GV());
			} catch (SQLException e) {
				lbTen_GV.setText("Error while loading!");
				lbTen_GV.setForeground(Color.RED);
				e.printStackTrace();
			}
			try {
				lbTen_KH.setText(new KhoaHocDao().findById(lopHoc.getId_KH()).getTen_KH());
			} catch (SQLException e) {
				lbTen_KH.setText("Error while loading!");
				lbTen_KH.setForeground(Color.RED);
				e.printStackTrace();
			}
			lbTen_LH.setText(lopHoc.getTen_LH());
			try {
				lbTen_PH.setText(new PhongHocDao().findById(lopHoc.getId_PH()).getTen_PH());
			} catch (SQLException e) {
				lbTen_PH.setText("Error while loading!");
				lbTen_KH.setForeground(Color.RED);
				e.printStackTrace();
			}
			taGhiChu_LH.setText(lopHoc.getGhichu_LH());
		}
	}

	public void loadDataHocVien() {
		data.clear();
		while (table.getRowCount() > 0) {
			tbHocVien.removeRow(0);
		}

		if (obj == null) {
			JOptionPane.showMessageDialog(ChiTiet_LopHoc.this, "Không thể load data học viên");
			return;
		}

		LopHoc lh = (LopHoc) obj;
		try {
			List<HocVien_LopHoc> lstHocViensLop = new HocVien_LopHocDAO().getPageByID_LH(lh.getId_LH(), page);
			int stt = 1;
			// 0: STT // 4: Điểm 2
			// 1: Mã // 5: Điểm 3
			// 2: Tên // 6: Tổng kết
			// 3: Điểm 1 // 7: Ghi chú

			// UNDONE

			for (@SuppressWarnings("unused")
			HocVien_LopHoc hvLop : lstHocViensLop) {
				String[] tenKH_PH = new LopHocDAO().getTenKH_TenPH(lh.getId_KH(), lh.getId_PH());
				String ten_KH = tenKH_PH[0];
				String ten_PH = tenKH_PH[1];
				tbHocVien.addRow(new Object[] { stt, lh.getId_LH(), ten_KH, lh.getTen_LH(), lh.getNgaybatdau(),
						lh.getNgayketthuc(), ten_PH, lh.getSiso_LH(), lh.getGhichu_LH() });
				data.put(stt - 1, lh);
				stt += 1;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ChiTiet_LopHoc.this, "Lỗi kết nối tới CSDL!");
			e.printStackTrace();
		}
	}
}
