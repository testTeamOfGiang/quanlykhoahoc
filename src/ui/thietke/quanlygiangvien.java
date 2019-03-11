package ui.thietke;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class quanlygiangvien extends JPanel {

	/**
	 * Create the panel.
	 */
	public quanlygiangvien() {
		this.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1400, 550);
		add(panel);
		
		JButton btnTrc = new JButton("Trước");
		btnTrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnTrc.setBounds(492, 582, 106, 40);
		add(btnTrc);
		
		JButton btnSau = new JButton("Sau");
		btnSau.setBounds(801, 582, 106, 40);
		add(btnSau);
		
		JButton btnThm = new JButton("Thêm");
		btnThm.setBounds(389, 676, 106, 40);
		add(btnThm);
		
		JButton btnSa = new JButton("Sửa");
		btnSa.setBounds(644, 676, 106, 40);
		add(btnSa);
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setBounds(910, 676, 106, 40);
		add(btnXa);
		
		JButton btnTmKim = new JButton("Tìm Kiếm");
		btnTmKim.setBounds(1106, 676, 97, 40);
		add(btnTmKim);
		
		JButton btnReload = new JButton("Reload");
		btnReload.setBounds(177, 677, 97, 39);
		add(btnReload);
	}
}
