package ui.thietke;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTextArea;

public class ChiTietLopHoc extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7432362180551962765L;
	private JTable table;
	private DefaultTableModel tableModel;
	private JComponent lbSiso_LH;
	private JComponent lbTen_LH;
	private Component lbID_LH;
	private Component lbTen_PH;
	private Component lbTen_GV;
	private Component lbTen_KH;
	private JTextArea taGhiChu_LH;

	public ChiTietLopHoc() {
		setLayout(null);
		setSize(1400, 800);

		Font font = new Font("Tahoma", Font.PLAIN, 16);

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã Lớp", "Tên Lớp", "Ngày Bắt Đầu", "Ngày Kết Thúc" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 400, 1400, 400);
		add(scrollPane);

		/* ================= */

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

		JButton btnSuaLIH = new JButton("Sửa lịch học");
		btnSuaLIH.setBounds(1034, 330, 125, 40);
		add(btnSuaLIH);

		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.setBounds(34, 38, 114, 40);
		btnQuayLi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		add(btnQuayLi);

		JButton btnXemDSHV = new JButton("Xem DS Học viên");
		btnXemDSHV.setBounds(500, 330, 125, 40);
		btnXemDSHV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		add(btnXemDSHV);

		JButton btnXemLichHoc = new JButton("Xem lịch học");
		btnXemLichHoc.setBounds(794, 330, 125, 40);
		btnXemLichHoc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		add(btnXemLichHoc);
		
		JButton btnNhapDiem = new JButton("Nhập điểm");
		btnNhapDiem.setBounds(247, 293, 100, 40);
		add(btnNhapDiem);
		
		JButton btnCapNhatDS = new JButton("Cập nhật DS");
		btnCapNhatDS.setBounds(247, 349, 100, 40);
		add(btnCapNhatDS);

		loadData();
	}

	public void loadData() {

	}
}
