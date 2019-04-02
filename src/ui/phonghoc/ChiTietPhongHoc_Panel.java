package ui.phonghoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import config.JDBC_Connection;
import entity.Phonghoc;
import exception.DateSaiException;
import ui.abstracts.AbsTractChiTietPanel;
import utils.DateSQL;

public class ChiTietPhongHoc_Panel extends AbsTractChiTietPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JTable table;
	private DefaultTableModel tableModel;

	public ChiTietPhongHoc_Panel() {

		tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 400, 1400, 500);
		add(scrollPane);

		/* ========================== */
		JLabel lblChiTitGing = new JLabel("Chi Tiết Phòng Học", JLabel.CENTER);
		lblChiTitGing.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblChiTitGing.setBounds(500, 38, 400, 70);
		add(lblChiTitGing);

		JLabel lblMGingVin = new JLabel("Mã Phòng Học");
		lblMGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMGingVin.setBounds(221, 151, 173, 40);
		add(lblMGingVin);

		JLabel lblTnGingVin = new JLabel("Tên Phòng Học");
		lblTnGingVin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnGingVin.setBounds(221, 226, 173, 40);
		add(lblTnGingVin);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(406, 150, 240, 42);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(406, 226, 240, 40);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblSinThoi = new JLabel("Ghi Chú");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinThoi.setBounds(755, 151, 145, 40);
		add(lblSinThoi);

		JLabel lblCcLpang = new JLabel("Các Lớp Sử Dụng", JLabel.CENTER);
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

		JButton btnXemLIH = new JButton("Xem lịch học");
		btnXemLIH.setBounds(950, 300, 125, 40);
		btnXemLIH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ChonNgayDialog(ChiTietPhongHoc_Panel.this).setVisible(true);
			}
		});
		add(btnXemLIH);

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(945, 158, 300, 97);
		add(textArea);

		loadData();
	}

	@Override
	public void loadData() {
		if (obj != null) {
			Phonghoc ph = (Phonghoc) obj;
			textField.setText(ph.getId_PH() + "");
			textField_1.setText(ph.getTen_PH());
			textArea.setText(ph.getGhichu_PH());

			while (table.getRowCount() > 0) {
				tableModel.removeRow(0);
			}

			try {
				for (int i = 1; i <= 22; i++) {
					tableModel.addRow(new Object[] { "Tiết" + i, null, null, null, null, null, null, null });
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public void getLichHoc(int idPhong, Date bd, Date kt) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select * from fn_GetLichHoc(?,?,?)";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, idPhong);
		preparedStatement.setDate(2, bd);
		preparedStatement.setDate(3, kt);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<LinkedList<Object>> matric = new ArrayList<LinkedList<Object>>();
		while (resultSet.next()) {
			LinkedList<Object> row = new LinkedList<Object>();
			row.add(resultSet.getString("ten_LH"));
			row.add(resultSet.getInt("thu"));
			row.add(resultSet.getString("tiet"));
			matric.add(row);
		}
		matric.stream().forEach(row -> {
			String tenLop = (String) row.get(0);
			int thu = (int) row.get(1);
			String grt = (String) row.get(2);
			String[] lt = grt.split(";");
			for (String str : lt) {
				String[] listT = str.split(",");
				int k = Integer.parseInt(listT[0].trim());
				int h = Integer.parseInt(listT[1].trim());
				for (int j = k; j <= h; j++) {
					tableModel.setValueAt(tenLop, j - 1, thu - 1);
				}
			}
		});
	}

}
