package ui.thietke;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimKiem_LopHoc extends JPanel {

	private static final long serialVersionUID = 8301408157653842883L;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public TimKiem_LopHoc() {
		this.setLayout(null);

		JLabel lblTmKimGing = new JLabel("Tìm Kiếm Giảng Viên", JLabel.CENTER);
		lblTmKimGing.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTmKimGing.setBounds(500, 53, 400, 60);
		add(lblTmKimGing);

		//JComboBox<String> comboBox = new JComboBox<String>(new String[] { "Theo Tên", "Theo Mã" });
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(354, 188, 155, 40);
		add(comboBox);
		
		JButton btnTmKim = new JButton("Tìm Kiếm");
		btnTmKim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTmKim.setBounds(893, 188, 155, 40);
		add(btnTmKim);
		
		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.setBounds(116, 103, 113, 40);
		add(btnQuayLi);
		
		textField = new JTextField();
		textField.setBounds(517, 188, 364, 40);
		add(textField);
		textField.setColumns(10);
	}

}
