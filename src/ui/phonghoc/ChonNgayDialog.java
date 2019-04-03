package ui.phonghoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.Phonghoc;
import exception.DateSaiException;
import utils.DateSQL;

public class ChonNgayDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JTextField txtBd;
	private JTextField txtKt;

	public ChonNgayDialog(ChiTietPhongHoc_Panel panel) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Xem Lịch Học");
		setModal(true);
		setSize(400, 400);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(panel);

		JLabel batDau = new JLabel("Ngày bắt đầu:", JLabel.CENTER);
		batDau.setBounds(20, 50, 110, 40);
		batDau.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(batDau);
		JLabel ketThuc = new JLabel("Ngày kết thúc:", JLabel.CENTER);
		ketThuc.setBounds(20, 130, 110, 40);
		ketThuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(ketThuc);

		txtBd = new JTextField();
		txtBd.setBounds(150, 50, 200, 40);
		txtBd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(txtBd);

		txtKt = new JTextField();
		txtKt.setBounds(150, 130, 200, 40);
		txtKt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(txtKt);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(50, 300, 120, 40);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Date bd = DateSQL.parseDate(txtBd.getText());
					Date kt = DateSQL.parseDate(txtKt.getText());
					Phonghoc ph = (Phonghoc) panel.getObj();
					try {
						panel.getLichHoc(ph.getId_PH(), bd, kt);
						dispose();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(ChonNgayDialog.this, "Không thể load dữ liệu");

						e1.printStackTrace();
					}
				} catch (DateSaiException e1) {
					JOptionPane.showMessageDialog(ChonNgayDialog.this, "Sai định dạng ngày tháng");
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(btnOk);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBounds(210, 300, 120, 40);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHuy);
	}

}
