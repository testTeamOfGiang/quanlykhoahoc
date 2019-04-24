
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
import exception.DateSaiException;
import main.MainApp;
import utils.CheckPhone;
import utils.DateSQL;

public class HocVien_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;

	private JTextField textField_2;
	private Hocvien HV;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;

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
		setModal(true);
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
						String sdt = txtSDT.getText().trim();
						String ngaysinh = txtNgaySinh.getText().trim();
						String diaChi = textField_2.getText().trim();
						if (ten.equals("") || sdt.equals("") || diaChi.equals("") || ngaysinh.equals("")) {
							throw new ChuaChonException();
						}
						if (!CheckPhone.check(sdt)) {
							throw new Exception("Sai định dạng số điện thoại");
						}
						Hocvien hv = new Hocvien();
						hv.setTen_HV(ten);
						hv.setSodt_HV(sdt);
						hv.setNgaysinh_HV(DateSQL.parseDate(ngaysinh));
						hv.setDiachi_HV(diaChi);
						MainApp.hocVienDao.add(hv);
						panel.loadData();
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Thêm Học Viên Thành Công");
						dispose();
					} catch (ChuaChonException ex) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Hãy Điền Đầy Đủ Thông tin");
					} catch (SQLException e2) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Thêm Học Viên Không Thành Công");
					} catch (DateSaiException e1) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Lỗi ngày sinh sai định dạng");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Lỗi: " + e1.getMessage());
					}

				} else {
					try {
						String ten = textField.getText().trim();
						String sdt = txtSDT.getText().trim();
						String ngaysinh = txtNgaySinh.getText().trim();
						String diaChi = textField_2.getText().trim();
						if (ten.equals("") || sdt.equals("") || diaChi.equals("") || ngaysinh.equals("")) {
							throw new ChuaChonException();
						}
						if (!CheckPhone.check(sdt)) {
							throw new Exception("sai định dạng số điện thoại");
						}
						HV.setTen_HV(ten);
						HV.setSodt_HV(sdt);
						HV.setDiachi_HV(diaChi);
						HV.setNgaysinh_HV(DateSQL.parseDate(ngaysinh));
						MainApp.hocVienDao.update(HV);
						panel.loadData();
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Sửa Học Viên Thành Công");
						dispose();
					} catch (ChuaChonException ex) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Hãy Điền Đầy Đủ Thông tin");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Sửa Học Viên Không Thành Công");
					} catch (DateSaiException e1) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Lỗi ngày sinh sai định dạng");
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(HocVien_Dialog.this, "Lỗi: " + e1.getMessage());
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
		lblTnHcVin.setBounds(38, 35, 121, 40);
		getContentPane().add(lblTnHcVin);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(38, 181, 121, 40);
		getContentPane().add(lblSinThoi);

		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaCh.setBounds(38, 252, 121, 40);
		getContentPane().add(lblaCh);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(205, 35, 412, 40);
		getContentPane().add(textField);
		textField.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtSDT.setBounds(205, 181, 412, 40);
		getContentPane().add(txtSDT);
		txtSDT.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(205, 252, 412, 40);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNgySinh = new JLabel("Ngày sinh");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgySinh.setBounds(38, 102, 121, 40);
		getContentPane().add(lblNgySinh);

		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(205, 102, 412, 40);
		getContentPane().add(txtNgaySinh);

		/* ======================== */

		if (HV != null) {
			textField.setText(HV.getTen_HV());
			txtSDT.setText(HV.getSodt_HV());
			txtNgaySinh.setText(DateSQL.toVNDate(HV.getNgaysinh_HV()));
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
