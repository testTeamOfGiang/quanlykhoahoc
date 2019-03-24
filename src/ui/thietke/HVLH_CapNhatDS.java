package ui.thietke;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class HVLH_CapNhatDS extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6324064288562662414L;
	
	private JTextField tfDiem1;
	Font font;

	/**
	 * Create the dialog.
	 */
	public HVLH_CapNhatDS() {
		font = new Font("Tahoma", Font.PLAIN, 16);
		init();
		initTextFields();
		initLabels();
		initButtons();
	}

	private void initButtons() {
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setBounds(351, 417, 97, 40);
		getContentPane().add(btnXoa);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setBounds(514, 73, 90, 40);
		getContentPane().add(btnCheck);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBounds(520, 417, 97, 40);
		getContentPane().add(btnHuy);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(172, 417, 97, 40);
		getContentPane().add(btnThem);
	}

	private void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 525);

	}

	private void initTextFields() {

		tfDiem1 = new JTextField();
		tfDiem1.setFont(font);
		tfDiem1.setBounds(267, 73, 210, 40);
		getContentPane().add(tfDiem1);
		tfDiem1.setColumns(10);
	}

	private void initLabels() {
		JLabel lblNhpMHc = new JLabel("Nhập mã học viên");
		lblNhpMHc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhpMHc.setFont(font);
		lblNhpMHc.setBounds(83, 73, 150, 40);
		getContentPane().add(lblNhpMHc);
		
		JLabel label1 = new JLabel("Tên học viên:");
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label1.setBounds(83, 176, 150, 40);
		getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Ngày sinh:");
		label2.setHorizontalAlignment(SwingConstants.LEFT);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label2.setBounds(83, 227, 150, 40);
		getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Số điện thoại");
		label3.setHorizontalAlignment(SwingConstants.LEFT);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label3.setBounds(83, 278, 150, 40);
		getContentPane().add(label3);
		
		JLabel lbTenHV = new JLabel("...");
		lbTenHV.setHorizontalAlignment(SwingConstants.LEFT);
		lbTenHV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTenHV.setBounds(267, 176, 246, 40);
		getContentPane().add(lbTenHV);
		
		JLabel lbNgaySinh = new JLabel("...");
		lbNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lbNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbNgaySinh.setBounds(267, 227, 246, 40);
		getContentPane().add(lbNgaySinh);
		
		JLabel lbSDT = new JLabel("...");
		lbSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lbSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSDT.setBounds(267, 278, 246, 40);
		getContentPane().add(lbSDT);
		
		JLabel lbErrorId_HV = new JLabel("");
		lbErrorId_HV.setForeground(Color.RED);
		lbErrorId_HV.setHorizontalAlignment(SwingConstants.LEFT);
		lbErrorId_HV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbErrorId_HV.setBounds(267, 118, 256, 20);
		getContentPane().add(lbErrorId_HV);
	}

}
