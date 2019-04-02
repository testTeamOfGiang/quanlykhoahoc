package ui.lophoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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
import dao.HocVienDao;
import dao.HocVien_LopHocDAO;
import dao.KhoaHocDao;
import dao.LichHocDAO;
import dao.PhongHocDao;
import entity.HocVien_LopHoc;
import entity.Hocvien;
import entity.LichHoc;
import entity.LopHoc;
import mycustom.LIH_Cell;
import mycustom.LIH_TableCellRenderer;
import mycustom.LIH_TableModel;
import ui.abstracts.AbsTractChiTietPanel;

public class ChiTiet_LopHoc extends AbsTractChiTietPanel {

	private static final long serialVersionUID = -6867270404171857479L;
	
	private JTable table;
	private DefaultTableModel tbHocVien;
	private LIH_TableModel tbLichHoc;
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
	private JButton btnNhapDiem;
	private JButton btnCapNhatDS;
	private JButton btnXemDSHV;
	private JButton btnXemLIH;
	private JButton btnSuaLIH;
	private HashMap<Integer, HocVien_LopHoc> dataHV;
	private HashMap<Integer, LichHoc> dataLIH;
	private int LIH_MaxTiet;
	private int page;

	public ChiTiet_LopHoc() {
		dataHV = new HashMap<Integer, HocVien_LopHoc>();
		dataLIH = new HashMap<Integer, LichHoc>();
		font = new Font("Tahoma", Font.PLAIN, 16);
		page = 0;
		this.status = 1;
		LIH_MaxTiet = 20;

		initTables();
		initButons();
		initLabels();
		loadData();
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

		btnNhapDiem = new JButton("Nhập điểm");
		btnNhapDiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnNhapDiem_Click();
			}
		});
		btnNhapDiem.setBounds(247, 293, 100, 40);
		add(btnNhapDiem);

		btnCapNhatDS = new JButton("Cập nhật DS");
		btnCapNhatDS.setBounds(247, 349, 100, 40);
		btnCapNhatDS.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCapNhatDS_Click();
			}
		});
		add(btnCapNhatDS);

		btnXemDSHV = new JButton("Xem DS Học viên");
		btnXemDSHV.setBounds(500, 330, 125, 40);
		btnXemDSHV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onTableHocVien();
			}
		});
		add(btnXemDSHV);

		btnXemLIH = new JButton("Xem lịch học");
		btnXemLIH.setBounds(794, 330, 125, 40);
		btnXemLIH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onTableLichHoc();
			}
		});
		add(btnXemLIH);

		btnSuaLIH = new JButton("Sửa lịch học");
		btnSuaLIH.setBounds(1034, 330, 125, 40);
		add(btnSuaLIH);

	}

	private void btnNhapDiem_Click() {
		int current = table.getSelectedRow();
		if (current != -1) {
			new ChiTiet_NhapDiem_Dialog(ChiTiet_LopHoc.this, dataHV.get(current)).setVisible(true);
			;
			loadDataHocVien();
		} else {
			JOptionPane.showMessageDialog(ChiTiet_LopHoc.this, "Bạn chưa chọn học viên!");
		}

	}

	private void btnCapNhatDS_Click() {
		if (obj != null) {
			LopHoc lh = (LopHoc) obj;
			new ChiTiet_CapNhatDSHV(ChiTiet_LopHoc.this, lh).setVisible(true);
			loadDataHocVien();
		}
	}

	private void onTableLichHoc() {
		// Đang hiển thị rồi thì thôi. 1 là lịch học
		if (this.status == 1)
			return;
		this.status = 1;

		table.setModel(tbLichHoc);
		table.setRowHeight(30);
		table.setDefaultRenderer(String.class, new LIH_TableCellRenderer());
		btnSuaLIH.setVisible(true);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		btnCapNhatDS.setVisible(false);
		btnNhapDiem.setVisible(false);
		loadDataLichHoc();
	}

	private void onTableHocVien() {
		if (this.status == 0)
			return;
		else
			this.status = 0;

		// = 0 là đang hiển thị Học viên rồi

		table.setModel(tbHocVien);
		table.setRowHeight(40);
		table.setDefaultRenderer(String.class, new DefaultTableCellRenderer());

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		TableColumn column;
		for (int i = 0; i < table.getColumnCount(); i++) {
			column = table.getColumnModel().getColumn(i);
			// 0: STT // 4: Điểm 2
			// 1: Mã // 5: Điểm 3
			// 2: Tên // 6: Điểm 4
			// 3: Điểm 1 // 6: Điểm TB
			// 7: Ghi chú
			if (i == 0) {
				column.setCellRenderer(centerRenderer);
				column.setMaxWidth(50);
			} else if (i == 1) {
				column.setCellRenderer(centerRenderer);
				column.setMaxWidth(80);
			} else if (i == 3 || i == 4 || i == 5 || i == 6 || i == 7) {
				// column.setCellRenderer(centerRenderer);
				column.setMaxWidth(250);
				column.setPreferredWidth(150);
			} else {
				column.setMaxWidth(400);
				column.setPreferredWidth(300);
			}
		}
		btnNhapDiem.setVisible(true);
		btnCapNhatDS.setVisible(true);

		btnSuaLIH.setVisible(false);
		loadDataHocVien();
	}

	private void initTables() {

		// Table Hoc Vien
		tbHocVien = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã", "Họ tên", "Điểm số 1",
				"Điểm số 2", "Điểm số 3", "Điểm cuối khoá", "Điểm TB", "Ghi chú" }) {

			private static final long serialVersionUID = 7000351210690643616L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbLichHoc = new LIH_TableModel(new Object[][] {},
				new String[] { "", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật" }) {
			private static final long serialVersionUID = -5270395411369071077L;
		};

		table = new JTable();
		((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
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
		lbSiso_LH.setBounds(264, 242, 155, 40);
		add(lbSiso_LH);

		lbTen_LH = new JLabel("...");
		lbTen_LH.setFont(font);
		lbTen_LH.setBounds(264, 191, 155, 40);
		add(lbTen_LH);

		lbID_LH = new JLabel("...");
		lbID_LH.setFont(font);
		lbID_LH.setBounds(264, 140, 155, 40);
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
		maLop.setBounds(174, 140, 80, 40);
		add(maLop);

		JLabel tenLop = new JLabel("Tên lớp");
		tenLop.setFont(font);
		tenLop.setBounds(174, 191, 80, 40);
		add(tenLop);

		JLabel siSo = new JLabel("Sĩ số");
		siSo.setFont(font);
		siSo.setBounds(174, 242, 80, 40);
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

	}

	/**
	 * Load dữ liệu cho các labels chi tiết
	 */
	@Override
	public void loadData() {

		if (obj != null) {
			LopHoc lh = (LopHoc) obj;

			lbID_LH.setText(lh.getId_LH() + "");
			lbSiso_LH.setText(lh.getSiso_LH() + "");
			try {
				lbTen_GV.setText(lh.getId_GV() + " - " + new GiangVienDao().findByID(lh.getId_GV()).getTen_GV());
			} catch (SQLException e) {
				lbTen_GV.setText("Error while loading!");
				lbTen_GV.setForeground(Color.RED);
				e.printStackTrace();
			}
			try {
				lbTen_KH.setText(lh.getId_KH() + " - " + new KhoaHocDao().findById(lh.getId_KH()).getTen_KH());
			} catch (SQLException e) {
				lbTen_KH.setText("Error while loading!");
				lbTen_KH.setForeground(Color.RED);
				e.printStackTrace();
			}
			lbTen_LH.setText(lh.getTen_LH());
			try {
				lbTen_PH.setText(lh.getId_PH() + " - " + new PhongHocDao().findById(lh.getId_PH()).getTen_PH());
			} catch (SQLException e) {
				lbTen_PH.setText("Error while loading!");
				lbTen_KH.setForeground(Color.RED);
				e.printStackTrace();
			}
			taGhiChu_LH.setText(lh.getGhichu_LH());
		}
		if (this.status == 0)
			loadDataHocVien();
		else
			loadDataLichHoc();
	}

	private void loadDataLichHoc() {
		dataLIH.clear();
		while (tbLichHoc.getRowCount() > 0) {
			tbLichHoc.removeRow(0);
		}

		if (obj == null) {
			return;
		}

		LopHoc lh = (LopHoc) obj;

		try {
			List<LichHoc> lstLIH = new LichHocDAO().findById_LH(lh.getId_LH());
			for (Vector<LIH_Cell> vt : toVectorCell(lstLIH)) {
				tbLichHoc.addRow(vt);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ChiTiet_LopHoc.this, "Lỗi kết nối tới CSDL", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	private Vector<Vector<LIH_Cell>> toVectorCell(List<LichHoc> lstLIH) {
		Vector<Vector<LIH_Cell>> result = new Vector<Vector<LIH_Cell>>();
		HashMap<Integer, LIH_Cell> map = new HashMap<Integer, LIH_Cell>();
		// duyệt theo thứ (ngày)
		for (LichHoc lih : lstLIH) {

			// duyệt theo buổi trong ngày
			String[] buoi = lih.getTiet().trim().split(";");
			for (String partBuoi : buoi) {
				String[] tiets = partBuoi.trim().split(",");
				int tiet_dau = Integer.parseInt(tiets[0]);
				int tiet_cuoi = Integer.parseInt(tiets[1]);

				// key tính toán theo toạ độ của cell
				// Thứ 2 thì index là 1.
				for (int i = tiet_dau; i <= tiet_cuoi; i++)
					map.put(i * 8 + lih.getThu() - 1, new LIH_Cell());
			}
		}

		Vector<LIH_Cell> vt;
		for (int i = 1; i <= LIH_MaxTiet; i++) {
			vt = new Vector<LIH_Cell>();
			vt.add(new LIH_Cell("Tiết " + i, 0));
			for (int j = 0; j <= 7; j++) {
				if (map.get(i * 8 + j - 1) != null)
					vt.add(new LIH_Cell(i + "", 1));
				else
					vt.add(new LIH_Cell("", 0));
			}

			result.add(vt);
		}
		
		return result;
	}

	private void loadDataHocVien() {
		dataHV.clear();
		while (tbHocVien.getRowCount() > 0) {
			tbHocVien.removeRow(0);
		}

		if (obj == null) {
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

			DecimalFormat f = new DecimalFormat("#.#");

			for (HocVien_LopHoc hvlh : lstHocViensLop) {

				// thằng này lấy tên học viên thôi
				Hocvien hv = new HocVienDao().findById(hvlh.getId_HV());

				float diem_1 = hvlh.getDiem_1();
				float diem_2 = hvlh.getDiem_2();
				float diem_3 = hvlh.getDiem_3();
				float diem_4 = hvlh.getDiem_4();
				int heso = 0;
				float tong = 0;

				// = -1 tức chưa có điểm. Đại diện cho null trong SQL

				if (diem_1 != -1) {
					heso += 1;
					tong += diem_1;
				}
				if (diem_2 != -1) {
					heso += 1;
					tong += diem_2;
				}
				if (diem_3 != -1) {
					heso += 1;
					tong += diem_3;
				}
				if (diem_4 != -1) {
					heso += 2;
					tong += diem_4 * 2;
				}
				float diemTB = tong / heso;
				tbHocVien.addRow(new Object[] { stt, hvlh.getId_HV(), hv.getTen_HV(), (diem_1 == -1 ? "*" : diem_1),
						(diem_2 == -1 ? "*" : diem_2), (diem_3 == -1 ? "*" : diem_3), (diem_4 == -1 ? "*" : diem_4),
						(heso == 0 ? "*" : f.format(diemTB)), hvlh.getGhichu_HVLH() });
				dataHV.put(stt - 1, hvlh);
				stt += 1;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ChiTiet_LopHoc.this, "Lỗi kết nối tới CSDL!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
