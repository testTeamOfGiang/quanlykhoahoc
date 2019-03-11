package ui.thietke;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class ChiTietGiangVien extends JPanel {

	/**
	 * Create the panel.
	 */
	public ChiTietGiangVien() {
		this.setLayout(null);

		JLabel lblChiTitGing = new JLabel("Chi Tiết Giảng Viên", JLabel.CENTER);
		lblChiTitGing.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChiTitGing.setBounds(500, 38, 400, 70);
		add(lblChiTitGing);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 400, 1400, 400);
		add(panel);
		
		JLabel lblMGingVin = new JLabel("Mã Giảng Viên");
		lblMGingVin.setBounds(143, 164, 101, 40);
		add(lblMGingVin);
		
		JLabel lblTnGingVin = new JLabel("Tên Giảng Viên");
		lblTnGingVin.setBounds(143, 256, 101, 40);
		add(lblTnGingVin);
	}
}
