package ui.thietke;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class hocviendiaog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			hocviendiaog dialog = new hocviendiaog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public hocviendiaog() {
		getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(335, 355, 97, 40);
		getContentPane().add(btnOk);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setBounds(504, 355, 97, 40);
		getContentPane().add(btnHy);
		
		JLabel lblTnHcVin = new JLabel("Tên Học Viên");
		lblTnHcVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnHcVin.setBounds(38, 85, 121, 40);
		getContentPane().add(lblTnHcVin);
		
		JLabel lblSinThoi = new JLabel("Số Điện Thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(38, 167, 121, 40);
		getContentPane().add(lblSinThoi);
		
		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaCh.setBounds(38, 252, 121, 40);
		getContentPane().add(lblaCh);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(205, 85, 412, 40);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(205, 167, 412, 40);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(205, 252, 412, 40);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
	}
}
