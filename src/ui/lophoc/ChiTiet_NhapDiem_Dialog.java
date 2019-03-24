package ui.lophoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import dao.HocVienDao;
import dao.HocVien_LopHocDAO;
import dao.LopHocDAO;
import entity.HocVien_LopHoc;
import entity.Hocvien;
import entity.LopHoc;

public class ChiTiet_NhapDiem_Dialog extends JDialog {

	private static final long serialVersionUID = 4835890768530431258L;
	private JTextField tfDiem1;
	private JTextField tfDiem2;
	private JTextField tfDiem3;
	private JTextField tfDiem4;
	private JTextArea taGhichu_LH;
	private HocVien_LopHoc hocVien_LopHoc;
	private ChiTiet_LopHoc parentPanel;
	private Font font;

	public ChiTiet_NhapDiem_Dialog(ChiTiet_LopHoc parentPanel, HocVien_LopHoc hocVien_LopHoc) {
		this.hocVien_LopHoc = hocVien_LopHoc;
		this.parentPanel = parentPanel;

		font = new Font("Tahoma", Font.PLAIN, 16);

		init();
		initTextFields();
		initLabels();
		initButtons();
	}

	private void btnOK_Click() {

		try {
			HocVien_LopHoc hvlh = new HocVien_LopHoc();

			hvlh.setId_HV(hocVien_LopHoc.getId_HV());
			hvlh.setId_LH(hocVien_LopHoc.getId_LH());
			String sDiem1 = tfDiem1.getText().trim();
			String sDiem2 = tfDiem2.getText().trim();
			String sDiem3 = tfDiem3.getText().trim();
			String sDiem4 = tfDiem4.getText().trim();
			String sGhiChu = taGhichu_LH.getText().trim();

			hvlh.setDiem_1(sDiem1 == "" ? -1 : Float.parseFloat(sDiem1));
			hvlh.setDiem_2(sDiem2 == "" ? -1 : Float.parseFloat(sDiem2));
			hvlh.setDiem_3(sDiem3 == "" ? -1 : Float.parseFloat(sDiem3));
			hvlh.setDiem_4(sDiem4 == "" ? -1 : Float.parseFloat(sDiem4));
			hvlh.setGhichu_HVLH(sGhiChu);
			new HocVien_LopHocDAO().updateHocVien_LopHoc(hvlh);
			JOptionPane.showMessageDialog(ChiTiet_NhapDiem_Dialog.this, "Cập nhật điểm thành công!");
			dispose();
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(ChiTiet_NhapDiem_Dialog.this, "Điểm nhập không hợp lệ!");
			nfe.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(ChiTiet_NhapDiem_Dialog.this, "Lỗi kết nối tới CSDL!");
			e1.printStackTrace();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(ChiTiet_NhapDiem_Dialog.this,
					"Có lỗi xảy ra! Vui lòng liên hệ team DEV!");
			e1.printStackTrace();
		}
	}
	
	private void initButtons() {
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(351, 417, 97, 40);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnOK_Click();
			}
		});
		getContentPane().add(btnOk);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBounds(520, 417, 97, 40);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHuy);

	}

	private void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 525);
		setLocationRelativeTo(parentPanel);
	}

	private void initTextFields() {

		tfDiem1 = new JTextField();
		tfDiem1.setFont(font);
		tfDiem1.setBounds(205, 106, 412, 40);
		getContentPane().add(tfDiem1);
		tfDiem1.setColumns(10);

		tfDiem2 = new JTextField();
		tfDiem2.setFont(font);
		tfDiem2.setBounds(205, 157, 412, 40);
		getContentPane().add(tfDiem2);
		tfDiem2.setColumns(10);

		tfDiem3 = new JTextField();
		tfDiem3.setFont(font);
		tfDiem3.setColumns(10);
		tfDiem3.setBounds(205, 208, 412, 40);
		getContentPane().add(tfDiem3);

		tfDiem4 = new JTextField();
		tfDiem4.setFont(font);
		tfDiem4.setColumns(10);
		tfDiem4.setBounds(205, 259, 412, 40);
		getContentPane().add(tfDiem4);

		taGhichu_LH = new JTextArea();
		taGhichu_LH.setLineWrap(true);
		taGhichu_LH.setWrapStyleWord(true);
		taGhichu_LH.setFont(font);
		JScrollPane scrollPane = new JScrollPane(taGhichu_LH, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollPane.setBounds(205, 323, 412, 74);
		getContentPane().add(scrollPane);

		if (hocVien_LopHoc != null) {
			tfDiem1.setText(hocVien_LopHoc.getDiem_1() == -1 ? "" : hocVien_LopHoc.getDiem_1()+"");
			tfDiem2.setText(hocVien_LopHoc.getDiem_2() == -1 ? "" : hocVien_LopHoc.getDiem_2()+"");
			tfDiem3.setText(hocVien_LopHoc.getDiem_3() == -1 ? "" : hocVien_LopHoc.getDiem_3()+"");
			tfDiem4.setText(hocVien_LopHoc.getDiem_4() == -1 ? "" : hocVien_LopHoc.getDiem_4()+"");
			taGhichu_LH.setText(hocVien_LopHoc.getGhichu_HVLH());
		}
	}

	private void initLabels() {
		JLabel lbKhoaHoc = new JLabel("Điểm số 1");
		lbKhoaHoc.setHorizontalAlignment(SwingConstants.LEFT);
		lbKhoaHoc.setFont(font);
		lbKhoaHoc.setBounds(83, 106, 91, 40);
		getContentPane().add(lbKhoaHoc);

		JLabel lbTenLop = new JLabel("Điểm số 2");
		lbTenLop.setHorizontalAlignment(SwingConstants.LEFT);
		lbTenLop.setFont(font);
		lbTenLop.setBounds(83, 157, 91, 40);
		getContentPane().add(lbTenLop);

		JLabel lblimS = new JLabel("Điểm số 3");
		lblimS.setHorizontalAlignment(SwingConstants.LEFT);
		lblimS.setFont(font);
		lblimS.setBounds(83, 208, 91, 40);
		getContentPane().add(lblimS);

		JLabel lbPhongHoc = new JLabel("Điểm số 4");
		lbPhongHoc.setHorizontalAlignment(SwingConstants.LEFT);
		lbPhongHoc.setFont(font);
		lbPhongHoc.setBounds(83, 259, 91, 40);
		getContentPane().add(lbPhongHoc);

		JLabel label = new JLabel("Ghi chú");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(font);
		label.setBounds(83, 323, 91, 40);
		getContentPane().add(label);

		JLabel lbHocVien = new JLabel("Học viên: ");
		lbHocVien.setHorizontalAlignment(SwingConstants.LEFT);
		lbHocVien.setFont(font);
		lbHocVien.setBounds(42, 27, 370, 40);
		getContentPane().add(lbHocVien);

		JLabel lbLopHoc = new JLabel("Lớp: ");
		lbLopHoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lbLopHoc.setFont(font);
		lbLopHoc.setBounds(474, 27, 200, 40);
		getContentPane().add(lbLopHoc);

		if (hocVien_LopHoc != null) {
			Hocvien hv;
			try {
				hv = new HocVienDao().findById(hocVien_LopHoc.getId_HV());
				lbHocVien.setText("Học viên: " + hv.getTen_HV());
				LopHoc lh = new LopHocDAO().findById_LH(hocVien_LopHoc.getId_LH());
				lbLopHoc.setText("Lớp: " + lh.getTen_LH());
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ChiTiet_NhapDiem_Dialog.this,
						"Có lỗi xảy ra! Vui lòng liên hệ với team DEV!");
				e.printStackTrace();
			}

		}
	}
}
