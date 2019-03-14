package ui.khoahoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Khoahoc;
import ui.abstracts.AbsTractChiTietPanel;

public class ChiTietKhoaHoc_Panel extends AbsTractChiTietPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea;
	private JTable table;
	private DefaultTableModel tableModel;

	public ChiTietKhoaHoc_Panel() {

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã Lớp", "Tên Lớp", "Ngày Bắt Đầu", "Ngày Kêt Thúc" }) {
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

		/* ========================== */
		JLabel lblChiTitGing = new JLabel("Chi Tiết Khóa Học", JLabel.CENTER);
		lblChiTitGing.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChiTitGing.setBounds(500, 38, 400, 70);
		add(lblChiTitGing);

		JLabel lblMGingVin = new JLabel("Mã Khóa Học");
		lblMGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMGingVin.setBounds(221, 121, 173, 40);
		add(lblMGingVin);

		JLabel lblTnGingVin = new JLabel("Tên Khóa Học");
		lblTnGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnGingVin.setBounds(221, 196, 173, 40);
		add(lblTnGingVin);

		JLabel lblNgySinh = new JLabel("Giá Khóa Học");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgySinh.setBounds(221, 270, 173, 40);
		add(lblNgySinh);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(406, 126, 240, 42);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(406, 201, 240, 40);
		add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(406, 275, 240, 40);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblSinThoi = new JLabel("Địa Chỉ");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(763, 126, 145, 40);
		add(lblSinThoi);

		JLabel lblCcLpang = new JLabel("Danh Sách Lớp", JLabel.CENTER);
		lblCcLpang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCcLpang.setBounds(500, 333, 400, 40);
		add(lblCcLpang);

		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.setBounds(65, 58, 114, 40);
		btnQuayLi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showQuanLy();
			}
		});
		add(btnQuayLi);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(945, 139, 300, 97);
		add(textArea);
	}

	@Override
	public void loadData() {
		if (obj != null) {
			Khoahoc kh = (Khoahoc) obj;
			textField.setText(kh.getId_KH() + "");
			textField_1.setText(kh.getTen_KH());
			textField_2.setText(kh.getGia_KH() + "");
			textArea.setText(kh.getGhichu_KH());
		}
	}

}
