package ui.giangvien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import entity.Giangvien;
import exception.DateSaiException;
import exception.ThieuThongTinException;
import main.MainApp;
import utils.CheckPhone;
import utils.DateSQL;

public class GiangVien_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tenGV;
	private JTextField ngaySinh;
	private JTextField soDT;
	private JTextField diaChi;
	private JTextArea ghiChu;
	private Giangvien GV;

	static enum Type {
		ADD, UPDATE
	};

	public GiangVien_Dialog(Type type, QuanLyGiangVien_Panel panel, Giangvien GV) {
		if (type == Type.ADD) {
			setTitle("Thêm Giảng Viên");
		} else {
			setTitle("Sửa Giảng Viên");
		}
		this.GV = GV;

		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 600);
		getContentPane().setLayout(null);
		setLocationRelativeTo(panel);

		JButton btnOk = new JButton("OK");
		btnOk.setBounds(121, 478, 97, 40);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String ten = tenGV.getText().trim();
					String sdt = soDT.getText().trim();
					String dc = diaChi.getText().trim();
					String gc = ghiChu.getText().trim();
					String ngay = ngaySinh.getText().trim();
					if (ten.equals("") || sdt.equals("") || dc.equals("") || ngay.equals("")) {
						throw new ThieuThongTinException();
					}
					if(CheckPhone.check(sdt.trim())==false)
						throw new Exception("Sai định dạng số điện thoại");
					
					java.sql.Date date = DateSQL.parseDate(ngay);
					Giangvien gv = new Giangvien();
					gv.setTen_GV(ten);
					gv.setNgaysinh_GV(date);
					gv.setSodt_GV(sdt);
					gv.setDiachi_GV(dc);
					gv.setGhichu_GV(gc);
					if (type == Type.ADD) {
						try {
							MainApp.giangVienDao.add(gv);
							panel.loadData();
							JOptionPane.showMessageDialog(GiangVien_Dialog.this, "Thành công");
							dispose();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(GiangVien_Dialog.this, "Thất bại");
							e1.printStackTrace();
						}
					} else {
						try {
							gv.setId_GV(GV.getId_GV());
							MainApp.giangVienDao.update(gv);
							panel.loadData();
							JOptionPane.showMessageDialog(GiangVien_Dialog.this, "Thành công");
							dispose();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(GiangVien_Dialog.this, "Thất bại");
							e1.printStackTrace();
						}
					}
				} catch (ThieuThongTinException e1) {
					JOptionPane.showMessageDialog(GiangVien_Dialog.this, "Hãy nhập đầy đủ thông tin");
					e1.printStackTrace();
				} catch (DateSaiException e2) {
					JOptionPane.showMessageDialog(GiangVien_Dialog.this, "Sai Định giạng ngày tháng");
					e2.printStackTrace();
				}catch (Exception e2) {
					JOptionPane.showMessageDialog(GiangVien_Dialog.this, e2.getMessage());
				}

			}
		});
		getContentPane().add(btnOk);

		JButton btnCanel = new JButton("Cancel");
		btnCanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCanel.setBounds(409, 478, 97, 40);
		getContentPane().add(btnCanel);

		JLabel lblTnGingVin = new JLabel("Tên Giảng Viên", JLabel.CENTER);
		lblTnGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnGingVin.setBounds(12, 57, 117, 40);
		getContentPane().add(lblTnGingVin);

		tenGV = new JTextField();
		tenGV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tenGV.setBounds(195, 57, 425, 40);
		getContentPane().add(tenGV);
		tenGV.setColumns(10);

		JLabel label = new JLabel("Ngày sinh", JLabel.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(12, 138, 117, 40);
		getContentPane().add(label);

		ngaySinh = new JTextField();
		ngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ngaySinh.setBounds(195, 138, 425, 40);
		getContentPane().add(ngaySinh);
		ngaySinh.setColumns(10);

		JLabel lblSDt = new JLabel("Số DT", JLabel.CENTER);
		lblSDt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSDt.setBounds(12, 225, 117, 40);
		getContentPane().add(lblSDt);

		soDT = new JTextField();
		soDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		soDT.setBounds(195, 225, 425, 40);
		getContentPane().add(soDT);
		soDT.setColumns(10);

		JLabel lblaCh = new JLabel("Địa chỉ", JLabel.CENTER);
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblaCh.setBounds(12, 309, 117, 40);
		getContentPane().add(lblaCh);

		diaChi = new JTextField();
		diaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		diaChi.setBounds(195, 309, 425, 40);
		getContentPane().add(diaChi);
		diaChi.setColumns(10);

		JLabel lblGhiCh = new JLabel("Ghi Chú", JLabel.CENTER);
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhiCh.setBounds(12, 385, 117, 40);
		getContentPane().add(lblGhiCh);

		ghiChu = new JTextArea();
		ghiChu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ghiChu.setBounds(195, 385, 425, 68);
		getContentPane().add(ghiChu);

		if (GV != null) {
			tenGV.setText(GV.getTen_GV());
			ngaySinh.setText(DateSQL.toVNDate(GV.getNgaysinh_GV()));
			soDT.setText(GV.getSodt_GV());
			diaChi.setText(GV.getDiachi_GV());
			ghiChu.setText(GV.getGhichu_GV());
		}
		
	}


	public JPanel getContentPanel() {
		return contentPanel;
	}

	public Giangvien getGV() {
		return GV;
	}

	public void setGV(Giangvien gV) {
		GV = gV;
	}
}
