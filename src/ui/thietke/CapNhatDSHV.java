package ui.thietke;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CapNhatDSHV extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6324064288562662414L;
	
	private JTextField tfDiem1;
	Font font;

	private JLabel lbSDT;

	private JLabel lbNgaySinh;

	private JLabel lbTenHV;

	/**
	 * Create the dialog.
	 */
	public CapNhatDSHV() {
		font = new Font("Tahoma", Font.PLAIN, 16);
		init();
		initTextFields();
		initLabels();
		initButtons();
	}

	private void initButtons() {
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setBounds(298, 342, 97, 40);
		getContentPane().add(btnXoa);
		
		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBounds(467, 342, 97, 40);
		getContentPane().add(btnHuy);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(119, 342, 97, 40);
		getContentPane().add(btnThem);
	}

	private void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 460);

	}

	private void initTextFields() {

		tfDiem1 = new JTextField();
		tfDiem1.setFont(font);
		tfDiem1.setBounds(339, 36, 210, 40);
		getContentPane().add(tfDiem1);
		tfDiem1.setColumns(10);
	}

	private void initLabels() {
		JLabel lblNhpMHc = new JLabel("Nhập mã học viên");
		lblNhpMHc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhpMHc.setFont(font);
		lblNhpMHc.setBounds(155, 36, 150, 40);
		getContentPane().add(lblNhpMHc);
		
		JLabel label1 = new JLabel("Tên học viên:");
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label1.setBounds(99, 117, 129, 40);
		getContentPane().add(label1);
		
		JLabel label2 = new JLabel("Ngày sinh:");
		label2.setHorizontalAlignment(SwingConstants.LEFT);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label2.setBounds(99, 168, 129, 40);
		getContentPane().add(label2);
		
		JLabel label3 = new JLabel("Số điện thoại");
		label3.setHorizontalAlignment(SwingConstants.LEFT);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label3.setBounds(99, 219, 129, 40);
		getContentPane().add(label3);
		
		lbTenHV = new JLabel("...");
		lbTenHV.setHorizontalAlignment(SwingConstants.LEFT);
		lbTenHV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTenHV.setBounds(238, 117, 326, 40);
		getContentPane().add(lbTenHV);
		
		lbNgaySinh = new JLabel("...");
		lbNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lbNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbNgaySinh.setBounds(238, 168, 326, 40);
		getContentPane().add(lbNgaySinh);
		
		lbSDT = new JLabel("...");
		lbSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lbSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSDT.setBounds(238, 219, 326, 40);
		getContentPane().add(lbSDT);
	}

}
