package ui.thietke;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class GiangVien_Dialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6737549415297333019L;
	@SuppressWarnings("unused")
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			GiangVien_Dialog dialog = new GiangVien_Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public GiangVien_Dialog() {
		setBounds(100, 100, 650, 600);
		getContentPane().setLayout(null);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(121, 478, 97, 40);
		getContentPane().add(btnOk);

		JButton btnCanel = new JButton("Cancel");
		btnCanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCanel.setBounds(409, 478, 97, 40);
		getContentPane().add(btnCanel);
		
		JLabel lblTnGingVin = new JLabel("Tên Giảng Viên",JLabel.CENTER);
		lblTnGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnGingVin.setBounds(12, 57, 117, 40);
		getContentPane().add(lblTnGingVin);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(195, 57, 425, 40);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("Ngày sinh",JLabel.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(12, 138, 117, 40);
		getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(195, 138, 425, 40);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSDt = new JLabel("Số DT",JLabel.CENTER);
		lblSDt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDt.setBounds(12, 225, 117, 40);
		getContentPane().add(lblSDt);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(195, 225, 425, 40);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblaCh = new JLabel("Địa chỉ",JLabel.CENTER);
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaCh.setBounds(12, 309, 117, 40);
		getContentPane().add(lblaCh);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setBounds(195, 309, 425, 40);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblGhiCh = new JLabel("Ghi Chú",JLabel.CENTER);
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhiCh.setBounds(12, 385, 117, 40);
		getContentPane().add(lblGhiCh);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPane.setBounds(195, 385, 425, 68);
		getContentPane().add(textPane);
	}
}
