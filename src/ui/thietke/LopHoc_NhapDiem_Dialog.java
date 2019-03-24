package ui.thietke;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class LopHoc_NhapDiem_Dialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7943968781498839790L;
	private JTextField tfDiem1;
	private JTextField tfDiem2;
	private JTextField tfDiem4;
	private JTextArea taGhichu_LH;
	Font font;
	private JTextField tfDiem3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LopHoc_Diaglog dialog = new LopHoc_Diaglog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LopHoc_NhapDiem_Dialog(){
		font = new Font("Tahoma", Font.PLAIN, 16);
		init();
		initTextFields();
		initLabels();

	}

	private void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 525);

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
		lbHocVien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbHocVien.setBounds(42, 27, 370, 40);
		getContentPane().add(lbHocVien);
		
		JLabel lbLopHoc = new JLabel("Lớp: ");
		lbLopHoc.setHorizontalAlignment(SwingConstants.RIGHT);
		lbLopHoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbLopHoc.setBounds(474, 27, 200, 40);
		getContentPane().add(lbLopHoc);
	}
}
