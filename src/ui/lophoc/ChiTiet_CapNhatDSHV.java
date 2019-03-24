package ui.lophoc;

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
import javax.swing.SwingConstants;

import dao.HocVienDao;
import dao.HocVien_LopHocDAO;
import entity.Hocvien;
import entity.LopHoc;

public class ChiTiet_CapNhatDSHV extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1790616570158517480L;
	private JTextField tfId_HV;
	Font font;
	private JLabel lbTenHV;
	private JLabel lbNgaySinh;
	private JLabel lbSDT;
	private JLabel lbErrorId_HV;
	private ChiTiet_LopHoc parentPanel;
	private LopHoc lh;
	private Hocvien hv;

	public ChiTiet_CapNhatDSHV(ChiTiet_LopHoc parentPanel, LopHoc lh) {
		this.parentPanel = parentPanel;
		this.lh = lh;

		font = new Font("Tahoma", Font.PLAIN, 16);

		initDialog();
		initTextFields();
		initLabels();
		initButtons();
	}

	private void btnCheck_Click() {
		lbErrorId_HV.setText("");
		lbTenHV.setText("");
		lbNgaySinh.setText("");
		lbSDT.setText("");

		try {
			int id_HV = Integer.parseInt(tfId_HV.getText().trim());
			hv = new HocVienDao().findById(id_HV);
			if (hv != null) {
				lbTenHV.setText(hv.getTen_HV());
				lbSDT.setText(hv.getSodt_HV());
			} else
				lbErrorId_HV.setText("Không tìm thấy học viên!");

		} catch (NumberFormatException nfe) {
			lbErrorId_HV.setText("Mã học viên không hợp lệ!");
			nfe.printStackTrace();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối tới CSDL! Vui lòng liên hệ team DEV!");
			e.printStackTrace();
		}
	}

	private void btnXoa_Click() {
		if (hv != null) {
			try {
				if (new HocVien_LopHocDAO().isExists(hv.getId_HV(), lh.getId_LH())) {
					new HocVien_LopHocDAO().deleteHocVien_LopHoc(hv.getId_HV(), lh.getId_LH());
					JOptionPane.showMessageDialog(this,
							"Đã xoá học viên " + hv.getTen_HV() + " khỏi lớp " + lh.getTen_LH() + "!");
				} else
					JOptionPane.showMessageDialog(this, "Sinh viên này không có trong lớp!");
				this.dispose();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi kết nối tới CSDL. Vui lòng liên hệ team DEV!");
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn học viên!");
		}
	}

	private void btnThem_Click() {
		if (hv != null) {
			try {
				new HocVien_LopHocDAO().addHocVien_LopHoc(hv.getId_HV(), lh.getId_LH());
				JOptionPane.showMessageDialog(this,
						"Đã thêm học viên " + hv.getTen_HV() + " vào lớp " + lh.getTen_LH() + "!");
				this.dispose();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi kết nối tới CSDL. Vui lòng liên hệ team DEV!");
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Bạn chưa chọn học viên!");
		}
	}

	private void initButtons() {
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setBounds(351, 417, 97, 40);
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnXoa_Click();
			}
		});
		getContentPane().add(btnXoa);

		JButton btnCheck = new JButton("Tìm");
		btnCheck.setBounds(514, 73, 90, 40);
		btnCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnCheck_Click();
			}
		});
		getContentPane().add(btnCheck);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBounds(520, 417, 97, 40);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHuy);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(172, 417, 97, 40);
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnThem_Click();
			}
		});
		getContentPane().add(btnThem);
	}

	private void initDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 525);
		setLocationRelativeTo(parentPanel);
		setTitle("Cập nhật danh sách học viên");
	}

	private void initTextFields() {

		tfId_HV = new JTextField();
		tfId_HV.setFont(font);
		tfId_HV.setBounds(267, 73, 210, 40);
		getContentPane().add(tfId_HV);
		tfId_HV.setColumns(10);
	}

	private void initLabels() {
		JLabel lblNhpMHc = new JLabel("Nhập mã học viên");
		lblNhpMHc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhpMHc.setFont(font);
		lblNhpMHc.setBounds(83, 73, 150, 40);
		getContentPane().add(lblNhpMHc);

		JLabel label1 = new JLabel("Tên học viên:");
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label1.setBounds(83, 176, 150, 40);
		getContentPane().add(label1);

		JLabel label2 = new JLabel("Ngày sinh:");
		label2.setHorizontalAlignment(SwingConstants.LEFT);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label2.setBounds(83, 227, 150, 40);
		getContentPane().add(label2);

		JLabel label3 = new JLabel("Số điện thoại");
		label3.setHorizontalAlignment(SwingConstants.LEFT);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label3.setBounds(83, 278, 150, 40);
		getContentPane().add(label3);

		lbTenHV = new JLabel("");
		lbTenHV.setHorizontalAlignment(SwingConstants.LEFT);
		lbTenHV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTenHV.setBounds(267, 176, 246, 40);
		getContentPane().add(lbTenHV);

		lbNgaySinh = new JLabel("");
		lbNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lbNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbNgaySinh.setBounds(267, 227, 246, 40);
		getContentPane().add(lbNgaySinh);

		lbSDT = new JLabel("");
		lbSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lbSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSDT.setBounds(267, 278, 246, 40);
		getContentPane().add(lbSDT);

		lbErrorId_HV = new JLabel("");
		lbErrorId_HV.setForeground(Color.RED);
		lbErrorId_HV.setHorizontalAlignment(SwingConstants.LEFT);
		lbErrorId_HV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbErrorId_HV.setBounds(267, 118, 256, 20);
		getContentPane().add(lbErrorId_HV);
	}
}
