package ui.hocvien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Hocvien;
import entity.LopHoc;
import main.MainApp;
import ui.abstracts.AbsTractChiTietPanel;
import ui.hocvien.HocVien_LopHoc_Dialog.Type;
import utils.DateSQL;

public class ChiTietHocVien_Panel extends AbsTractChiTietPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField txtNgaySinh;
	private JTable table;
	private DefaultTableModel tableModel;

	public ChiTietHocVien_Panel() {

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã Lớp", "Tên Lớp", "Ngày Bắt Đầu", "Ngày Kết Thúc" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 400, 1400, 400);
		add(scrollPane);

		/* ================= */
		JLabel lblChiTitGing = new JLabel("Chi Tiết Học Viên", JLabel.CENTER);
		lblChiTitGing.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChiTitGing.setBounds(500, 38, 400, 70);
		add(lblChiTitGing);
		JLabel lblMGingVin = new JLabel("Mã Học Viên");
		lblMGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMGingVin.setBounds(221, 121, 173, 40);
		add(lblMGingVin);

		JLabel lblTnGingVin = new JLabel("Tên Học Viên");
		lblTnGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnGingVin.setBounds(221, 196, 173, 40);
		add(lblTnGingVin);

		JLabel lblNgySinh = new JLabel("Số Điện Thoại");
		lblNgySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNgySinh.setBounds(221, 270, 173, 40);
		add(lblNgySinh);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(406, 126, 240, 42);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(406, 201, 240, 40);
		add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(406, 275, 240, 40);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblSinThoi = new JLabel("Địa Chỉ");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(763, 126, 145, 40);
		add(lblSinThoi);

		JLabel lblSinThoi2 = new JLabel("Ngày Sinh");
		lblSinThoi2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi2.setBounds(763, 196, 145, 40);
		add(lblSinThoi2);
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(945, 196, 240, 42);
		add(txtNgaySinh);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_4.setColumns(10);
		textField_4.setBounds(945, 120, 240, 42);
		add(textField_4);

		JLabel lblCcLpang = new JLabel("Các Lớp Theo Học", JLabel.CENTER);
		lblCcLpang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCcLpang.setBounds(500, 333, 400, 40);
		add(lblCcLpang);

		JButton btnQuayLi = new JButton("Quay Lại");
		btnQuayLi.setBounds(65, 58, 114, 40);
		btnQuayLi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showQuanLy();
			}
		});
		add(btnQuayLi);

		JButton btnngKLp = new JButton("Đăng Ký Lớp");
		btnngKLp.setBounds(945, 253, 114, 40);
		btnngKLp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hocvien hv = (Hocvien) obj;
				new HocVien_LopHoc_Dialog(Type.ADD, ChiTietHocVien_Panel.this, hv.getId_HV()).setVisible(true);
			}
		});
		add(btnngKLp);

		JButton btnHyLp = new JButton("Hủy Lớp");
		btnHyLp.setBounds(1079, 253, 106, 40);
		btnHyLp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Hocvien hv = (Hocvien) obj;
				new HocVien_LopHoc_Dialog(Type.DELETE, ChiTietHocVien_Panel.this, hv.getId_HV()).setVisible(true);
			}
		});
		add(btnHyLp);

		loadData();
	}

	@Override
	public void loadData() {
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		Hocvien hv;
		if (obj != null) {
			hv = (Hocvien) obj;
			textField.setText(hv.getId_HV() + "");
			textField_1.setText(hv.getTen_HV());
			textField_2.setText(hv.getSodt_HV());
			textField_4.setText(hv.getDiachi_HV());
			txtNgaySinh.setText(DateSQL.toVNDate(hv.getNgaysinh_HV()));
			try {
				List<LopHoc> list = MainApp.hocVienDao.getLopHoc(hv);
				int stt=1;
				for(LopHoc lh:list) {
					tableModel.addRow(new Object[] {
						stt,lh.getId_LH(),lh.getTen_LH(),lh.getNgaybatdau().toString(),lh.getNgayketthuc().toString()
					});
					stt+=1;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(ChiTietHocVien_Panel.this, "Không thể load dữ liệu");
				e.printStackTrace();
			}
		}

	}

}
