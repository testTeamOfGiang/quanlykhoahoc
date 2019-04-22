package ui.giangvien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Giangvien;
import entity.LopHoc;
import main.MainApp;
import ui.abstracts.AbsTractChiTietPanel;
import utils.DateSQL;

public class ChiTietGiangVien_Panel extends AbsTractChiTietPanel {

	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextArea textArea;
	private DefaultTableModel tableModel;
	private JTable table;

	private static final long serialVersionUID = 1L;

	public ChiTietGiangVien_Panel() {
		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã Lớp học", "Tên lớp học", "Phòng học", "Ngày bắt đầu", "Ngày kết thúc" }) {
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

		/* ==================== */
		JLabel lblChiTitGing = new JLabel("Chi Tiết Giảng Viên", JLabel.CENTER);
		lblChiTitGing.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChiTitGing.setBounds(500, 38, 400, 70);
		add(lblChiTitGing);

		JPanel panel = new JPanel();
		panel.setBounds(0, 400, 1400, 400);
		add(panel);

		JLabel lblMGingVin = new JLabel("Mã Giảng Viên");
		lblMGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMGingVin.setBounds(221, 121, 173, 40);
		add(lblMGingVin);

		JLabel lblTnGingVin = new JLabel("Tên Giảng Viên");
		lblTnGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnGingVin.setBounds(221, 196, 173, 40);
		add(lblTnGingVin);

		JLabel lblNgySinh = new JLabel("Ngày sinh");
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

		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(763, 126, 145, 40);
		add(lblSinThoi);

		JLabel label = new JLabel("Địa chỉ");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(763, 201, 145, 40);
		add(label);

		JLabel lblGhiCh = new JLabel("Ghi Chú");
		lblGhiCh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGhiCh.setBounds(763, 275, 145, 40);
		add(lblGhiCh);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(946, 270, 239, 100);
		add(textArea);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(946, 196, 240, 40);
		add(textField_3);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_4.setColumns(10);
		textField_4.setBounds(945, 120, 240, 42);
		add(textField_4);

		JLabel lblCcLpang = new JLabel("Các lớp đang dạy", JLabel.CENTER);
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

		loadData();
	}

	@Override
	public void loadData() {
		if (obj != null) {
			Giangvien gv = (Giangvien) obj;
			textField.setText(gv.getId_GV() + "");
			textField_1.setText(gv.getTen_GV());
			textField_2.setText(gv.getNgaysinh_GV().toString().replace("-", "/"));
			textField_3.setText(gv.getDiachi_GV());
			textField_4.setText(gv.getSodt_GV());
			textArea.setText(gv.getGhichu_GV());

			/* ====================== */

			while (table.getRowCount() > 0) {
				tableModel.removeRow(0);
			}

			try {
				List<LopHoc> lopHocs = MainApp.giangVienDao.getLopHoc(gv);
				int stt = 1;
				for (LopHoc lh : lopHocs) {
					tableModel.addRow(new Object[] { stt, lh.getId_LH(), lh.getTen_LH(), lh.getId_PH(),
							DateSQL.toVNDate(lh.getNgaybatdau()), DateSQL.toVNDate(lh.getNgayketthuc()) });
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
