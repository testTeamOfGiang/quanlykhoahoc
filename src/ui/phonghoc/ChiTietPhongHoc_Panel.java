package ui.phonghoc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import entity.Phonghoc;
import ui.abstracts.AbsTractChiTietPanel;

public class ChiTietPhongHoc_Panel extends AbsTractChiTietPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JTable table;
	private DefaultTableModel tableModel;

	public ChiTietPhongHoc_Panel() {

		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã Lớp", "Tên Lớp" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 300, 1400, 500);
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
		}
	}

}
