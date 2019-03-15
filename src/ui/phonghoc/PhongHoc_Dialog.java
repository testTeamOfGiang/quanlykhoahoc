package ui.phonghoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Phonghoc;
import exception.ThieuThongTinException;
import main.MainApp;

public class PhongHoc_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextArea textArea;

	static enum Type {
		ADD, UPDATE
	};

	public PhongHoc_Dialog(Type type, QuanLyPhongHoc_Panel panel, Phonghoc ph) {
		setSize(440, 380);
		setLocationRelativeTo(panel);
		setModal(true);

		getContentPane().setLayout(null);

		JLabel lblMLp = new JLabel("Tên Phòng");
		lblMLp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMLp.setBounds(51, 55, 100, 40);
		getContentPane().add(lblMLp);

		textField = new JTextField();
		textField.setBounds(163, 55, 201, 40);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(163, 271, 97, 40);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (type == Type.ADD) {
					try {
						String ten = textField.getText().trim();
						String ghiChu = textArea.getText().trim();
						if (ten.equals("") || ghiChu.equals("")) {
							throw new ThieuThongTinException();
						}
						Phonghoc phongHoc = new Phonghoc();
						phongHoc.setTen_PH(ten);
						phongHoc.setGhichu_PH(ghiChu);
						MainApp.phongHocDao.add(phongHoc);
						panel.loadData();
						JOptionPane.showMessageDialog(PhongHoc_Dialog.this, "Thêm Phòng Học Thành Công");
						dispose();
					} catch (ThieuThongTinException ex) {
						JOptionPane.showMessageDialog(PhongHoc_Dialog.this, "Hãy Nhập đầy đủ thông tin");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(PhongHoc_Dialog.this, "Thêm Phòng Học không thành công");
						e1.printStackTrace();
					}
				} else {
					try {
						String ten = textField.getText().trim();
						String ghiChu = textArea.getText().trim();
						if (ten.equals("") || ghiChu.equals("")) {
							throw new ThieuThongTinException();
						}
						ph.setTen_PH(ten);
						ph.setGhichu_PH(ghiChu);
						MainApp.phongHocDao.update(ph);
						panel.loadData();
						JOptionPane.showMessageDialog(PhongHoc_Dialog.this, "Sửa Phòng Học Thành Công");
						dispose();
					} catch (ThieuThongTinException ex) {
						JOptionPane.showMessageDialog(PhongHoc_Dialog.this, "Hãy Nhập đầy đủ thông tin");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(PhongHoc_Dialog.this, "Sửa Phòng Học không thành công");
						e1.printStackTrace();
					}
				}

			}
		});
		getContentPane().add(btnOk);

		JButton btnHy = new JButton("Hủy");
		btnHy.setBounds(272, 271, 97, 40);
		btnHy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHy);

		JLabel lblGhiCh = new JLabel("Ghi Chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhiCh.setBounds(51, 125, 100, 40);
		getContentPane().add(lblGhiCh);

		textArea = new JTextArea();
		textArea.setBounds(163, 135, 201, 95);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(textArea);

		if (ph != null) {
			textField.setText(ph.getTen_PH());
			textArea.setText(ph.getGhichu_PH());
		}
	}

}
