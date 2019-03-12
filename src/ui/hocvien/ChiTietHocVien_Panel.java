package ui.hocvien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import entity.Hocvien;
import ui.abstracts.AbsTractChiTietPanel;

public class ChiTietHocVien_Panel extends AbsTractChiTietPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;

	public ChiTietHocVien_Panel() {
		JLabel lblChiTitGing = new JLabel("Chi Tiết Học Viên", JLabel.CENTER);
		lblChiTitGing.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChiTitGing.setBounds(500, 38, 400, 70);
		add(lblChiTitGing);
		JLabel lblMGingVin = new JLabel("Mã Học Viên");
		lblMGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMGingVin.setBounds(221, 121, 173, 40);
		add(lblMGingVin);

		JLabel lblTnGingVin = new JLabel("Tên Học Viên");
		lblTnGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnGingVin.setBounds(221, 196, 173, 40);
		add(lblTnGingVin);

		JLabel lblNgySinh = new JLabel("Số Điện Thoại");
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

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_4.setColumns(10);
		textField_4.setBounds(945, 120, 240, 42);
		add(textField_4);

		JLabel lblCcLpang = new JLabel("Các Lớp Theo Học", JLabel.CENTER);
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

		JButton btnngKLp = new JButton("Đăng Ký Lớp");
		btnngKLp.setBounds(945, 253, 114, 40);
		add(btnngKLp);

		JButton btnHyLp = new JButton("Hủy Lớp");
		btnHyLp.setBounds(1079, 253, 106, 40);
		add(btnHyLp);

		loadData();
	}

	@Override
	public void loadData() {
		Hocvien hv;
		if (obj != null) {
			hv = (Hocvien) obj;
			textField.setText(hv.getId_HV() + "");
			textField_1.setText(hv.getTen_HV());
			textField_2.setText(hv.getSodt_HV());
			textField_4.setText(hv.getDiachi_HV());
		}

	}

}
