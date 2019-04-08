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

public class DangNhapDialog extends JDialog {

	private static final long serialVersionUID = 8236003470781028379L;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private MainPanel mainPanel;

	public DangNhapDialog(MainPanel mainPanel) {
		this.mainPanel = mainPanel;

		setSize(440, 380);
		setTitle("Đăng nhập");
		setLocationRelativeTo(this.mainPanel);
		setModal(true);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		initButtons();
		initLabels_textfields();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void initButtons() {

		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setBounds(108, 227, 119, 40);
		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnLogin_Click();
			}
		});
		getContentPane().add(btnLogin);

		JButton btnChangePW = new JButton("Đổi mật khẩu");
		btnChangePW.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnChangePW.setBounds(260, 227, 119, 40);
		btnChangePW.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnChangePW_Click();
			}
		});
		getContentPane().add(btnChangePW);
	}

	protected void btnChangePW_Click() {
		dispose();
		new DoiMatKhauDialog(this.mainPanel).setVisible(true);
	}

	protected void btnLogin_Click() {
		String username = txtUsername.getText();
		char[] password = txtPassword.getPassword();

		if (username.equals("") || password.length == 0) {
			JOptionPane.showMessageDialog(this, "Các trường không được để trống!", "Lỗi", JOptionPane.WARNING_MESSAGE);
			if(username.equals(""))
				txtUsername.grabFocus();
			else
				txtPassword.grabFocus();
		} else {
			User user = new User(username, new String(password));
			try {
				int level = new UserDAO().getLevel(user);
				// 0 = admin
				if (level == 0) {
					dispose();
					new Thread(new Runnable() {

						@Override
						public void run() {
							mainPanel.showPanel_Lv0();
						}
					}).start();
				}
				// 1 == quản lý học viên only
				else if (level == 1) {
					dispose();
					new Thread(new Runnable() {

						@Override
						public void run() {
							mainPanel.showPanel_Lv1();
						}
					}).start();
				} else if (level == -1) {
					JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác");
					txtUsername.grabFocus();
				} else {
					JOptionPane.showMessageDialog(this, "Có lỗi xảy ra. Vui lòng liên hệ quản trị viên", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					txtUsername.grabFocus();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void initLabels_textfields() {

		txtUsername = new JTextField();
		txtUsername.setBounds(166, 71, 201, 40);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setColumns(10);
		txtPassword.setBounds(166, 141, 201, 40);
		getContentPane().add(txtPassword);

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

	}
}
