package ui.thietke;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

	public ChiTietLopHoc() {
		setLayout(null);
		setSize(1400, 800);
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
		JLabel lblChiTitGing = new JLabel("Chi Tiết Học Viên", JLabel.CENTER);
		lblChiTitGing.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChiTitGing.setBounds(500, 38, 400, 70);
		add(lblChiTitGing);
		JLabel lb1 = new JLabel("Mã lớp");
		lb1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb1.setBounds(174, 140, 94, 40);
		add(lb1);

		JLabel lb2 = new JLabel("Tên lớp");
		lb2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb2.setBounds(174, 191, 94, 40);
		add(lb2);

		JLabel lb3 = new JLabel("Sĩ số");
		lb3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb3.setBounds(174, 242, 94, 40);
		add(lb3);

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
		
		JLabel lbSiso_LH = new JLabel("...");
		lbSiso_LH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSiso_LH.setBounds(293, 242, 155, 40);
		add(lbSiso_LH);
		
		JLabel lbTen_LH = new JLabel("...");
		lbTen_LH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTen_LH.setBounds(293, 191, 155, 40);
		add(lbTen_LH);
		
		JLabel lbID_LH = new JLabel("...");
		lbID_LH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbID_LH.setBounds(293, 140, 155, 40);
		add(lbID_LH);
		
		JLabel lblKhoHc_1 = new JLabel("Khoá học");
		lblKhoHc_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKhoHc_1.setBounds(500, 140, 94, 40);
		add(lblKhoHc_1);
		
		JLabel lblGingVin = new JLabel("Giảng viên");
		lblGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGingVin.setBounds(500, 191, 94, 40);
		add(lblGingVin);
		
		JLabel lblPhngHc = new JLabel("Phòng học");
		lblPhngHc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhngHc.setBounds(500, 242, 94, 40);
		add(lblPhngHc);
		
		JLabel lbTen_PH = new JLabel("...");
		lbTen_PH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTen_PH.setBounds(619, 242, 190, 40);
		add(lbTen_PH);
		
		JLabel lbTen_GV = new JLabel("...");
		lbTen_GV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTen_GV.setBounds(619, 191, 190, 40);
		add(lbTen_GV);
		
		JLabel lbTen_KH = new JLabel("...");
		lbTen_KH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTen_KH.setBounds(619, 140, 190, 40);
		add(lbTen_KH);
		
		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhiCh.setBounds(919, 140, 94, 40);
		add(lblGhiCh);
		
		JTextArea taGhiChu_LH = new JTextArea();
		taGhiChu_LH.setTabSize(4);
		taGhiChu_LH.setLineWrap(true);
		taGhiChu_LH.setWrapStyleWord(true);
		taGhiChu_LH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JScrollPane scrollPane1 = new JScrollPane(taGhiChu_LH, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setSize(300, 91);
		scrollPane1.setLocation(919, 191);
		add(scrollPane1);
		
		JButton btnSuaLIH = new JButton("Sửa lịch học");
		btnSuaLIH.setBounds(1034, 330, 125, 40);
		add(btnSuaLIH);

		loadData();
	}

	
	public void loadData() {
		

	}
}
