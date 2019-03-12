package ui.thietke;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ui.hocvien.ChiTietHocVien_Panel;

import javax.swing.JButton;

public class dankyhocdialog extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public dankyhocdialog(ChiTietHocVien_Panel panel) {
		setLayout(null);
		
		JLabel lblMLp = new JLabel("Mã Lớp");
		lblMLp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMLp.setBounds(51, 103, 100, 40);
		add(lblMLp);
		
		textField = new JTextField();
		textField.setBounds(163, 103, 201, 40);
		add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(163, 185, 97, 40);
		add(btnOk);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setBounds(272, 185, 97, 40);
		add(btnHy);
	}
}
