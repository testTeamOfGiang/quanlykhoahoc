package ui.thietke;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DialogDangNhap extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 966303933916423477L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDangNhap dialog = new DialogDangNhap();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDangNhap() {
		setSize(440, 380);
		setTitle("Đăng nhập");
		setLocationRelativeTo(null);
		setModal(true);
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		
		initBtns_Tfs();
		initLabels();
	}
	public void initBtns_Tfs() {
		JTextField txtUsername = new JTextField();
		txtUsername.setBounds(166, 71, 201, 40);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(108, 227, 119, 40);
		getContentPane().add(btnLogin);

		
		JTextField txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setColumns(10);
		txtPassword.setBounds(166, 141, 201, 40);
		getContentPane().add(txtPassword);
	}

	public void initLabels() {
		JLabel lblMLp = new JLabel("Tài khoản");
		lblMLp.setHorizontalAlignment(SwingConstants.CENTER);
		lblMLp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMLp.setBounds(40, 71, 116, 40);
		getContentPane().add(lblMLp);

		JLabel lblGhiCh = new JLabel("Mật khẩu");
		lblGhiCh.setHorizontalAlignment(SwingConstants.CENTER);
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGhiCh.setBounds(40, 141, 116, 40);
		getContentPane().add(lblGhiCh);
		
		JButton btnChangePW = new JButton("Đổi mật khẩu");
		btnChangePW.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChangePW.setBounds(260, 227, 119, 40);
		getContentPane().add(btnChangePW);
	}
}
