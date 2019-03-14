package ui.thietke;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;

public class hocviendiaog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

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
		btnOk.setBounds(335, 403, 97, 40);
		getContentPane().add(btnOk);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setBounds(504, 403, 97, 40);
		getContentPane().add(btnHy);
		
		JLabel lblTnHcVin = new JLabel("Tên Khóa Học");
		lblTnHcVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnHcVin.setBounds(38, 85, 121, 40);
		getContentPane().add(lblTnHcVin);
		
		JLabel lblSinThoi = new JLabel("Giá Khóa Học");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(38, 167, 121, 40);
		getContentPane().add(lblSinThoi);
		
		JLabel lblaCh = new JLabel("Ghi Chú");
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(205, 252, 412, 104);
		getContentPane().add(textArea);
	}
}
