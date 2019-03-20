package ui.thietke;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class ChiTietGV extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3700370625867400955L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public ChiTietGV() {
		this.setLayout(null);

		JLabel lblChiTitGing = new JLabel("Chi Tiết Phòng Học", JLabel.CENTER);
		lblChiTitGing.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChiTitGing.setBounds(500, 38, 400, 70);
		add(lblChiTitGing);

		JPanel panel = new JPanel();
		panel.setBounds(0, 400, 1400, 400);
		add(panel);

		JLabel lblMGingVin = new JLabel("Mã Phòng Học");
		lblMGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMGingVin.setBounds(221, 151, 173, 40);
		add(lblMGingVin);

		JLabel lblTnGingVin = new JLabel("Tên Phòng Học");
		lblTnGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnGingVin.setBounds(221, 226, 173, 40);
		add(lblTnGingVin);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(406, 150, 240, 42);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(406, 226, 240, 40);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblSinThoi = new JLabel("Chi Chú");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(755, 151, 145, 40);
		add(lblSinThoi);

		JLabel lblCcLpang = new JLabel("Các Lớp Sử Dụng", JLabel.CENTER);
		lblCcLpang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCcLpang.setBounds(500, 333, 400, 40);
		add(lblCcLpang);
		
		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.setBounds(65, 58, 114, 40);
		add(btnQuayLi);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(945, 158, 300, 97);
		add(textArea);
	}
}
