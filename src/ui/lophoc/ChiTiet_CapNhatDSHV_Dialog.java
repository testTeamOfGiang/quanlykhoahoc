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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.HocVienDao;
import dao.HocVien_LopHocDAO;
import entity.Hocvien;
import entity.LopHoc;
import utils.DateSQL;

public class ChiTiet_CapNhatDSHV_Dialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1790616570158517480L;
	private JTextField tfId_HV;
	Font font;
	private JLabel lbTenHV;
	private JLabel lbNgaySinh;
	private JLabel lbSDT;
	private ChiTiet_LopHoc_Panel parentPanel;
	private LopHoc lh;
	private Hocvien hv;

	public ChiTiet_CapNhatDSHV_Dialog(ChiTiet_LopHoc_Panel parentPanel, LopHoc lh) {
		this.parentPanel = parentPanel;
		this.lh = lh;

		font = new Font("Tahoma", Font.PLAIN, 16);

		initDialog();
		initTextFields();
		initLabels();
		initButtons();
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
			JOptionPane.showMessageDialog(this, "Không thể xoá học viên!");
		}
	}

	private void btnThem_Click() {
		if (hv != null) {
			try {
				if (new HocVien_LopHocDAO().isExists(hv.getId_HV(), lh.getId_LH()))
					JOptionPane.showMessageDialog(this, "Học viên này đã ở trong lớp!");
				else {
					new HocVien_LopHocDAO().addHocVien_LopHoc(hv.getId_HV(), lh.getId_LH());
					JOptionPane.showMessageDialog(this,
							"Thêm thành công " + hv.getTen_HV() + " vào lớp " + lh.getTen_LH() + "!");
					this.dispose();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi kết nối tới CSDL. Vui lòng liên hệ team DEV!");
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Không thể thêm học viên!");
		}
	}

	private void initButtons() {
		JButton btnXoa = new JButton("Xoá");
		btnXoa.setBounds(298, 342, 97, 40);
		btnXoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnXoa_Click();
			}
		});
		getContentPane().add(btnXoa);

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBounds(467, 342, 97, 40);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHuy);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(119, 342, 97, 40);
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
		setSize(700, 460);
		setLocationRelativeTo(parentPanel);
		setTitle("Cập nhật danh sách học viên");
	}

	// Xử lý khi tf thay đổi dữ liệu
	private void onTfChange() {
		try {
			hv = loadHocVien();
			if (hv != null) {
				lbTenHV.setText(hv.getTen_HV());
				lbTenHV.setForeground(Color.blue);
				lbSDT.setText(hv.getSodt_HV());
				lbSDT.setForeground(Color.blue);
				lbNgaySinh.setText(DateSQL.toVNDate(hv.getNgaysinh_HV()));
				lbNgaySinh.setForeground(Color.blue);
			} else {
				lbTenHV.setText("Không tồn tại học viên này!");
				lbTenHV.setForeground(Color.red);
				lbSDT.setText("");
				lbSDT.setForeground(Color.red);
				lbNgaySinh.setText("");
				lbNgaySinh.setForeground(Color.red);
			}
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Hocvien loadHocVien() throws NumberFormatException, SQLException {
		Hocvien hv = null;
		String text = tfId_HV.getText();
		if (!text.equals("") && text.matches("\\d*")) {
			hv = new HocVienDao().findById(Integer.parseInt(text));
		}
		return hv;
	}

	private void initTextFields() {

		tfId_HV = new JTextField();
		tfId_HV.setFont(font);
		tfId_HV.setBounds(339, 36, 210, 40);
		getContentPane().add(tfId_HV);
		tfId_HV.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				onTfChange();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				onTfChange();
			}

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				onTfChange();
			}
		});
		tfId_HV.setColumns(10);
	}

	private void initLabels() {
		JLabel lblNhpMHc = new JLabel("Nhập mã học viên");
		lblNhpMHc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhpMHc.setFont(font);
		lblNhpMHc.setBounds(155, 36, 150, 40);
		getContentPane().add(lblNhpMHc);

		JLabel label1 = new JLabel("Tên học viên:");
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label1.setBounds(99, 117, 129, 40);
		getContentPane().add(label1);

		JLabel label2 = new JLabel("Ngày sinh:");
		label2.setHorizontalAlignment(SwingConstants.LEFT);
		label2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label2.setBounds(99, 168, 129, 40);
		getContentPane().add(label2);

		JLabel label3 = new JLabel("Số điện thoại");
		label3.setHorizontalAlignment(SwingConstants.LEFT);
		label3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label3.setBounds(99, 219, 129, 40);
		getContentPane().add(label3);

		lbTenHV = new JLabel("...");
		lbTenHV.setHorizontalAlignment(SwingConstants.LEFT);
		lbTenHV.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTenHV.setBounds(238, 117, 326, 40);
		getContentPane().add(lbTenHV);

		lbNgaySinh = new JLabel("...");
		lbNgaySinh.setHorizontalAlignment(SwingConstants.LEFT);
		lbNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbNgaySinh.setBounds(238, 168, 326, 40);
		getContentPane().add(lbNgaySinh);

		lbSDT = new JLabel("...");
		lbSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lbSDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbSDT.setBounds(238, 219, 326, 40);
		getContentPane().add(lbSDT);
	}
}
