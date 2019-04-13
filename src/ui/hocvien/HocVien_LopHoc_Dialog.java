package ui.hocvien;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import entity.LopHoc;
import exception.ThieuThongTinException;
import main.MainApp;

public class HocVien_LopHoc_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JLabel lbThongBao;

	static enum Type {
		ADD, DELETE
	};

	public HocVien_LopHoc_Dialog(Type type, ChiTietHocVien_Panel panel, int id_HV) {
		if (type == Type.ADD) {
			setTitle("Đăng Ký lớp");
		} else {
			setTitle("Hủy Đắng ký lớp");
		}
		setModal(true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(440, 300);
		setLocationRelativeTo(panel);

		lbThongBao = new JLabel("", JLabel.CENTER);
		lbThongBao.setBounds(163, 60, 200, 40);
		add(lbThongBao);

		JLabel lblMLp = new JLabel("Mã Lớp");
		lblMLp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMLp.setBounds(51, 103, 100, 40);
		getContentPane().add(lblMLp);

		textField = new JTextField();
		textField.setBounds(163, 103, 201, 40);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(163, 185, 97, 40);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (type == Type.ADD) {
					try {
						String idText = textField.getText().trim();
						if (idText.equals("")) {
							throw new ThieuThongTinException();
						}
						int id_LH = Integer.parseInt(idText);
						MainApp.hocVienDao.addLop(id_HV, id_LH);
						panel.loadData();
						JOptionPane.showMessageDialog(HocVien_LopHoc_Dialog.this, "Đăng ký học thành công");
						dispose();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(HocVien_LopHoc_Dialog.this, "Đăng Ký học không thành công");
						e1.printStackTrace();
					} catch (ThieuThongTinException e1) {
						JOptionPane.showMessageDialog(HocVien_LopHoc_Dialog.this, "Hãy Nhập mã lớp vào");
						e1.printStackTrace();
					}
				} else {
					try {
						String idText = textField.getText().trim();
						if (idText.equals("")) {
							throw new ThieuThongTinException();
						}
						int id_LH = Integer.parseInt(idText);
						int result = MainApp.hocVienDao.deleteLop(id_HV, id_LH);
						if (result != 0) {
							panel.loadData();
							JOptionPane.showMessageDialog(HocVien_LopHoc_Dialog.this, "Hủy Lớp học thành công");
						} else {
							JOptionPane.showMessageDialog(HocVien_LopHoc_Dialog.this, "Bạn Chưa Đăng Ký lớp này");
						}
						dispose();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(HocVien_LopHoc_Dialog.this, "Hủy lớp học không thành công");
						System.out.println(e1.getMessage());
					} catch (ThieuThongTinException e1) {
						JOptionPane.showMessageDialog(HocVien_LopHoc_Dialog.this, "Hãy Nhập mã lớp vào");
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		getContentPane().add(btnOk);

		JButton btnHy = new JButton("Hủy");
		btnHy.setBounds(272, 185, 97, 40);
		btnHy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHy);
		addEvent();
	}

	public void addEvent() {
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				try {
					String idLH = textField.getText().trim();
					Integer idLop = Integer.parseInt(idLH);
					LopHoc lh = MainApp.lopHocDAO.findById_LH(idLop);
					lbThongBao.setForeground(Color.BLUE);
					lbThongBao.setText(lh.getTen_LH());
				} catch (Exception ex) {
					lbThongBao.setForeground(Color.RED);
					lbThongBao.setText("Không tìm thấy lớp học");
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				try {
					String idLH = textField.getText().trim();
					Integer idLop = Integer.parseInt(idLH);
					LopHoc lh = MainApp.lopHocDAO.findById_LH(idLop);
					lbThongBao.setForeground(Color.BLUE);
					lbThongBao.setText(lh.getTen_LH());
				} catch (Exception ex) {
					lbThongBao.setForeground(Color.RED);
					lbThongBao.setText("Không tìm thấy lớp học");
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println(3);
			}
		});
	}
}
