package ui.thietke;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class DialogDoiMatKhau extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430628595521387657L;
	private JTextField txtUsername;
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
	private JLabel lblNhpLiMt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDoiMatKhau dialog = new DialogDoiMatKhau();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */

	public void initBtns_Tfs() {
		txtUsername = new JTextField();
		txtUsername.setBounds(171, 41, 201, 40);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		JButton btnChangePassword = new JButton("Đổi mật khẩu");
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChangePassword.setBounds(171, 271, 119, 40);
		getContentPane().add(btnChangePassword);

		JButton btnCancel = new JButton("Huỷ");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(300, 271, 74, 40);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancel);

		txtOldPassword = new JPasswordField();
		txtOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtOldPassword.setColumns(10);
		txtOldPassword.setBounds(171, 111, 201, 40);
		getContentPane().add(txtOldPassword);

		txtNewPassword = new JPasswordField();
		txtNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNewPassword.setColumns(10);
		txtNewPassword.setBounds(171, 178, 201, 40);
		getContentPane().add(txtNewPassword);
		
	}

	public void initLabels() {
		JLabel lblMLp = new JLabel("Tài khoản");
		lblMLp.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMLp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMLp.setBounds(31, 41, 117, 40);
		getContentPane().add(lblMLp);

		JLabel lblGhiCh = new JLabel("Mật khẩu cũ");
		lblGhiCh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGhiCh.setBounds(31, 111, 117, 40);
		getContentPane().add(lblGhiCh);

		lblNhpLiMt = new JLabel("Mật khẩu mới");
		lblNhpLiMt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpLiMt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNhpLiMt.setBounds(31, 178, 117, 40);
		getContentPane().add(lblNhpLiMt);
	}

	public DialogDoiMatKhau() {
		setSize(440, 380);
		setTitle("Đổi mật khẩu");
		setLocationRelativeTo(null);
		setModal(true);
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		initLabels();
		initBtns_Tfs();
	}
}
