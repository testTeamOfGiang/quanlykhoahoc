package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.UserDAO;
import entity.User;

public class DoiMatKhauDialog extends JDialog {

	private static final long serialVersionUID = -7149338816438669463L;
	private JTextField txtUsername;
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
	private JLabel lblNhpLiMt;
	private MainPanel mainPanel;
	private final String passwordPattern = "\\w{8,20}"; // [a-zA-Z_0-9]

	/**
	 * Create the dialog.
	 */

	public void initButons() {

		JButton btnChangePassword = new JButton("Đổi mật khẩu");
		btnChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChangePassword.setBounds(171, 251, 119, 40);
		btnChangePassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnChangePassword_Click();
			}
		});
		getContentPane().add(btnChangePassword);

		JButton btnCancel = new JButton("Huỷ");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCancel.setBounds(300, 251, 74, 40);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DangNhapDialog(mainPanel).setVisible(true);
			}
		});
		getContentPane().add(btnCancel);
	}

	protected void btnChangePassword_Click() {
		String username = txtUsername.getText();
		char[] OldPassword = txtOldPassword.getPassword();
		char[] NewPassword = txtNewPassword.getPassword();

		if (username.equals("") || OldPassword.length == 0 || NewPassword.length == 0) {
			JOptionPane.showMessageDialog(this, "Các trường không được để trống!", "Lỗi", JOptionPane.WARNING_MESSAGE);
			if(username.equals(""))
				txtUsername.grabFocus();
			else if(OldPassword.length == 0)
				txtOldPassword.grabFocus();
			else
				txtNewPassword.grabFocus();
		} else {
			User user = new User(username, new String(OldPassword));
			try {
				boolean checkOldPW = new UserDAO().checkPassword(user);
				if (!checkOldPW) {
					JOptionPane.showMessageDialog(this, "Mật khẩu cũ không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					txtOldPassword.grabFocus();
				} else if (!new String(NewPassword).matches(passwordPattern)) {
					JOptionPane.showMessageDialog(this,
							"Mật khẩu mới phải có từ 8 đến 20 ký tự bao gồm chữ cái, số hoặc dấu gạch dưới!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					txtNewPassword.grabFocus();
				} else {
					user.setPassword(new String(NewPassword));
					new UserDAO().changePassword(user);
					JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!");
					dispose();
					new DangNhapDialog(this.mainPanel).setVisible(true);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi kết nối tới CSDL!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

	}

	public void initLabels_TextFields() {
		txtUsername = new JTextField();
		txtUsername.setBounds(171, 41, 201, 40);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

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

	public DoiMatKhauDialog(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
		setSize(440, 380);
		setTitle("Đổi mật khẩu");
		setLocationRelativeTo(this.mainPanel);
		setModal(true);
		setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);

		initLabels_TextFields();
		initButons();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
