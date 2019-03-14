package ui.khoahoc;

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

import entity.Khoahoc;
import exception.ThieuThongTinException;
import main.MainApp;

public class KhoaHoc_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;

	static enum Type {
		ADD, UPDATE
	};

	public KhoaHoc_Dialog(QuanLyKhoaHoc_Panel panel, Type type, Khoahoc kh) {
		setSize(700, 530);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(panel);
		setModal(true);

		getContentPane().setLayout(null);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(335, 403, 97, 40);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (type == Type.ADD) {
					try {
						String ten = textField.getText().trim();
						String giaTam = textField_1.getText().trim();
						String ghiChu = textArea.getText().trim();
						if (ten.equals("") || giaTam.equals("")) {
							throw new ThieuThongTinException();
						}
						int gia = Integer.parseInt(giaTam);
						Khoahoc khoaHoc = new Khoahoc();
						khoaHoc.setTen_KH(ten);
						khoaHoc.setGia_KH(gia);
						khoaHoc.setGhichu_KH(ghiChu);
						MainApp.khoaHocDao.add(khoaHoc);
						panel.loadData();
						JOptionPane.showMessageDialog(KhoaHoc_Dialog.this, "Thêm Khóa Học Thành công");
						dispose();
					} catch (ThieuThongTinException ex) {
						JOptionPane.showMessageDialog(KhoaHoc_Dialog.this, "Hãy Nhập Đầy Đủ Thông Tin");
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(KhoaHoc_Dialog.this, "Học Phí Là Số Nguyên");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(KhoaHoc_Dialog.this, "Thêm Khóa Học Không Thành Công");
						e1.printStackTrace();
					}
				} else {
					try {
						String ten = textField.getText().trim();
						String giaTam = textField_1.getText().trim();
						String ghiChu = textArea.getText().trim();
						if (ten.equals("") || giaTam.equals("")) {
							throw new ThieuThongTinException();
						}
						int gia = Integer.parseInt(giaTam);
						kh.setTen_KH(ten);
						kh.setGia_KH(gia);
						kh.setGhichu_KH(ghiChu);
						MainApp.khoaHocDao.update(kh);
						panel.loadData();
						JOptionPane.showMessageDialog(KhoaHoc_Dialog.this, "Sửa Khóa Học Thành công");
						dispose();
					} catch (ThieuThongTinException e2) {
						JOptionPane.showMessageDialog(KhoaHoc_Dialog.this, "Hãy Nhập Đầy Đủ Thông Tin");
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(KhoaHoc_Dialog.this, "Học Phí Là Số Nguyên");
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(KhoaHoc_Dialog.this, "Sửa Khóa Học Không Thành Công");
					}
				}
			}
		});
		getContentPane().add(btnOk);

		JButton btnHy = new JButton("Hủy");
		btnHy.setBounds(504, 403, 97, 40);
		btnHy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHy);

		JLabel lblTnHcVin = new JLabel("Tên Khóa Học");
		lblTnHcVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnHcVin.setBounds(38, 85, 121, 40);
		getContentPane().add(lblTnHcVin);

		JLabel lblSinThoi = new JLabel("Giá Khóa Học");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(38, 167, 121, 40);
		getContentPane().add(lblSinThoi);

		JLabel lblaCh = new JLabel("Ghi Chú");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaCh.setBounds(38, 252, 121, 40);
		getContentPane().add(lblaCh);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(205, 85, 412, 40);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(205, 167, 412, 40);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textArea = new JTextArea();
		textArea.setBounds(205, 252, 412, 104);
		getContentPane().add(textArea);

		if (type == Type.ADD) {
			setTitle("Thêm Khóa Học");
		} else {
			setTitle("Sửa Khóa Học");
		}

		if (kh != null) {
			textField.setText(kh.getTen_KH());
			textField_1.setText(kh.getGia_KH() + "");
			textArea.setText(kh.getGhichu_KH());
		}
	}
}
