package ui.thietke;

import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class LopHoc_Diaglog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8732830908063843181L;

	private JTextField tfId_KH;
	private JTextField tfNgayHoc;
	private JTextField tfNgayKetThuc;
	private JTextField tfTen_LH;
	private JTextArea taGhichu_LH;
	Font font;

	private JTextField tfId_GV;

	private JTextField tfId_PH;
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
	public LopHoc_Diaglog() {
		font = new Font("Tahoma", Font.PLAIN, 16);
		init();
		initTextFields();
		initLabels();
		bonusLabels();
	}

	private void bonusLabels() {
		// TODO Auto-generated method stub
		
	}

	private void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 525);

	}

	private void initTextFields() {

		tfId_KH = new JTextField();
		tfId_KH.setFont(font);
		tfId_KH.setBounds(205, 40, 412, 40);
		getContentPane().add(tfId_KH);
		tfId_KH.setColumns(10);

		tfTen_LH = new JTextField();
		tfTen_LH.setFont(font);
		tfTen_LH.setBounds(205, 91, 412, 40);
		getContentPane().add(tfTen_LH);
		tfTen_LH.setColumns(10);

		tfId_GV = new JTextField();
		tfId_GV.setFont(font);
		tfId_GV.setColumns(10);
		tfId_GV.setBounds(205, 168, 412, 40);
		getContentPane().add(tfId_GV);

		tfNgayHoc = new JTextField();
		tfNgayHoc.setFont(font);
		tfNgayHoc.setBounds(205, 219, 120, 40);
		tfNgayHoc.setToolTipText("Định dạng: dd/mm/yyyy hoặc yy/mm/dd");
		getContentPane().add(tfNgayHoc);
		tfNgayHoc.setColumns(10);

		tfNgayKetThuc = new JTextField();
		tfNgayKetThuc.setToolTipText("Định dạng: dd/mm/yyyy hoặc yy/mm/dd");
		tfNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfNgayKetThuc.setColumns(10);
		tfNgayKetThuc.setBounds(497, 219, 120, 40);
		getContentPane().add(tfNgayKetThuc);

		tfId_PH = new JTextField();
		tfId_PH.setFont(font);
		tfId_PH.setColumns(10);
		tfId_PH.setBounds(205, 290, 412, 40);
		getContentPane().add(tfId_PH);

		taGhichu_LH = new JTextArea();
		taGhichu_LH.setLineWrap(true);
		taGhichu_LH.setWrapStyleWord(true);
		taGhichu_LH.setFont(font);
		JScrollPane scrollPane = new JScrollPane(taGhichu_LH, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollPane.setBounds(205, 341, 412, 74);
		getContentPane().add(scrollPane);
	}

	private void initLabels() {
		JLabel lbKhoaHoc = new JLabel("Khoá học");
		lbKhoaHoc.setHorizontalAlignment(SwingConstants.LEFT);
		lbKhoaHoc.setFont(font);
		lbKhoaHoc.setBounds(83, 40, 91, 40);
		getContentPane().add(lbKhoaHoc);

		JLabel lbTenLop = new JLabel("Tên lớp");
		lbTenLop.setHorizontalAlignment(SwingConstants.LEFT);
		lbTenLop.setFont(font);
		lbTenLop.setBounds(83, 91, 91, 40);
		getContentPane().add(lbTenLop);

		JLabel label_1 = new JLabel("Giảng viên");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(font);
		label_1.setBounds(83, 168, 91, 40);
		getContentPane().add(label_1);

		JLabel lbNgayHoc = new JLabel("Ngày học");
		lbNgayHoc.setHorizontalAlignment(SwingConstants.LEFT);
		lbNgayHoc.setFont(font);
		lbNgayHoc.setBounds(83, 219, 91, 40);
		getContentPane().add(lbNgayHoc);

		JLabel lbNgayKetThuc = new JLabel("Ngày kết thúc");
		lbNgayKetThuc.setFont(font);
		lbNgayKetThuc.setBounds(378, 219, 110, 40);
		getContentPane().add(lbNgayKetThuc);

		JLabel lbPhongHoc = new JLabel("Phòng học");
		lbPhongHoc.setHorizontalAlignment(SwingConstants.LEFT);
		lbPhongHoc.setFont(font);
		lbPhongHoc.setBounds(83, 290, 91, 40);
		getContentPane().add(lbPhongHoc);

		JLabel label = new JLabel("Ghi chú");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(font);
		label.setBounds(83, 341, 91, 40);
		getContentPane().add(label);
		
		JLabel lbTenPH = new JLabel("");
		lbTenPH.setBounds(205, 265, 208, 19);
		getContentPane().add(lbTenPH);
		
		JLabel lbTenGV = new JLabel("");
		lbTenGV.setBounds(205, 142, 208, 19);
		getContentPane().add(lbTenGV);
		
		JLabel lbTenKH = new JLabel("");
		lbTenKH.setBounds(205, 11, 208, 19);
		getContentPane().add(lbTenKH);
	}
}
