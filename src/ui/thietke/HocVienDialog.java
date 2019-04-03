package ui.thietke;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HocVienDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8453225231394141750L;
	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HocVienDialog dialog = new HocVienDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HocVienDialog() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 500);
		getContentPane().setLayout(null);

		JLabel lblTnHcVin = new JLabel("Tên Học Viên");
		lblTnHcVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnHcVin.setBounds(38, 35, 121, 40);
		getContentPane().add(lblTnHcVin);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(38, 181, 121, 40);
		getContentPane().add(lblSinThoi);
		
		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaCh.setBounds(38, 252, 121, 40);
		getContentPane().add(lblaCh);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(205, 35, 412, 40);
		getContentPane().add(textField);
		textField.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setBounds(205, 181, 412, 40);
		getContentPane().add(txtSDT);
		txtSDT.setColumns(10);

		JTextField textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(205, 252, 412, 40);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgySinh.setBounds(38, 102, 121, 40);
		getContentPane().add(lblNgySinh);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(205, 102, 412, 40);
		getContentPane().add(txtNgaySinh);
	}
}
