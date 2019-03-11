
package ui.hocvien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.Hocvien;
import exception.ChuaChonException;
import main.MainApp;

public class HocVien_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Hocvien HV;

	static enum Type {
		ADD, UPDATE
	};

	public HocVien_Dialog(Type type, QuanLyHocVien_Panel panel, Hocvien HV) {
		this.setHV(HV);
		if (type == Type.ADD) {
			setTitle("Thêm Học Viên");
		} else {
			setTitle("Sửa Học Viên");
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 500);
		setLocationRelativeTo(panel);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(335, 355, 97, 40);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (type == Type.ADD) {
					try {
						String ten = textField.getText().trim();
						String sdt = textField_1.getText().trim();
						String diaChi = textField_2.getText().trim();
						if (ten.equals("") || sdt.equals("") || diaChi.equals("")) {
							throw new ChuaChonException();
						}
						Hocvien hv = new Hocvien();
						hv.setTen_HV(ten);
						hv.setSodt_HV(sdt);
						hv.setDiachi_HV(diaChi);
						MainApp.hocVienDao.add(hv);
						panel.loadData();
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Thêm Học Viên Thành Công");
						dispose();
					} catch (ChuaChonException ex) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Hãy Điền Đầy Đủ Thông tin");
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Thêm Học Viên Không Thành Công");
					}

				} else {
					try {
						String ten = textField.getText().trim();
						String sdt = textField_1.getText().trim();
						String diaChi = textField_2.getText().trim();
						if (ten.equals("") || sdt.equals("") || diaChi.equals("")) {
							throw new ChuaChonException();
						}
						HV.setTen_HV(ten);
						HV.setSodt_HV(sdt);
						HV.setDiachi_HV(diaChi);

						MainApp.hocVienDao.update(HV);
						panel.loadData();
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Sửa Học Viên Thành Công");
						dispose();
					} catch (ChuaChonException ex) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Hãy Điền Đầy Đủ Thông tin");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Sửa Học Viên Không Thành Công");
						e1.printStackTrace();
					}
				}
			}
		});
		getContentPane().add(btnOk);

		JButton btnHy = new JButton("Hủy");
		btnHy.setBounds(504, 355, 97, 40);
		btnHy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHy);

		JLabel lblTnHcVin = new JLabel("Tên Học Viên");
		lblTnHcVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnHcVin.setBounds(38, 85, 121, 40);
		getContentPane().add(lblTnHcVin);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(38, 167, 121, 40);
		getContentPane().add(lblSinThoi);

		JLabel lblaCh = new JLabel("Địa Chỉ");
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

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(205, 252, 412, 40);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		/* ======================== */

		if (HV != null) {
			textField.setText(HV.getTen_HV());
			textField_1.setText(HV.getSodt_HV());
			textField_2.setText(HV.getDiachi_HV());
		}
	}

	public Hocvien getHV() {
		return HV;
	}

	public void setHV(Hocvien hV) {
		HV = hV;
	}
}
