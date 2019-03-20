package ui.thietke;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ui.hocvien.ChiTietHocVien_Panel;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class dankyhocdialog extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6630418773802447002L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public dankyhocdialog(ChiTietHocVien_Panel panel) {
		setLayout(null);
		
		JLabel lblMLp = new JLabel("Tên Phòng");
		lblMLp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMLp.setBounds(51, 55, 100, 40);
		add(lblMLp);
		
		textField = new JTextField();
		textField.setBounds(163, 55, 201, 40);
		add(textField);
		textField.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(163, 271, 97, 40);
		add(btnOk);
		
		JButton btnHy = new JButton("Hủy");
		btnHy.setBounds(272, 271, 97, 40);
		add(btnHy);
		
		JLabel lblGhiCh = new JLabel("Ghi Chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhiCh.setBounds(51, 125, 100, 40);
		add(lblGhiCh);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(163, 135, 201, 95);
		add(textArea);
	}
}
