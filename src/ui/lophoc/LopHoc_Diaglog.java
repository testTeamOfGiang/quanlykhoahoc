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

import dao.LopHocDAO;
import entity.LopHoc;
import exception.DateSaiException;
import utils.DateSQL;

public class LopHoc_Diaglog extends JDialog {

	private static final long serialVersionUID = -2635979588415181063L;

	private QuanLyLopHoc_Panel parentPanel;
	private LopHoc lh;
	private JTextField tfId_KH;
	private JTextField tfNgayHoc;
	private JTextField tfNgayKetThuc;
	private JTextField tfTen_LH;
	private JTextField tfId_PH;
	private JTextField tfId_GV;
	private JTextArea taGhichu_LH;
	private Font font;

	/**
	 * Tạo dialog để thêm hoặc sửa lớp học
	 * 
	 * @param parentPanel
	 * @param lh          đưa vào nếu muốn sửa
	 */
	public LopHoc_Diaglog(QuanLyLopHoc_Panel parentPanel, LopHoc lh) {

		font = new Font("Tahoma", Font.PLAIN, 16);
		this.parentPanel = parentPanel;

		init();
		initTextFields();
		initLabels();
		initButtons();
		if (lh != null) {
			setTitle("Sửa thông tin lớp");
			this.lh = lh;
			setTextForEditing();
		} else
			setTitle("Thêm lớp");

	}

	private void setTextForEditing() {
		tfId_KH.setText(lh.getId_KH() + "");
		tfTen_LH.setText(lh.getTen_LH());
		tfId_PH.setText(lh.getId_PH() + "");
		tfId_GV.setText(lh.getId_GV()+"");
		tfNgayHoc.setText(lh.getNgaybatdau().toString().replace('-', '/'));
		tfNgayKetThuc.setText(lh.getNgayketthuc().toString().replace('-', '/'));
		taGhichu_LH.setText(lh.getGhichu_LH());
	}

	private void init() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 525);
		setLocationRelativeTo(parentPanel);
	}

	private void initButtons() {
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(351, 417, 97, 40);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id_KH = tfId_KH.getText().trim();
				String ten_LH = tfTen_LH.getText().trim();
				String id_GV = tfId_GV.getText().trim();
				String ngayHoc = tfNgayHoc.getText().trim();
				String ngayketthuc = tfNgayKetThuc.getText().trim();
				String id_PH = tfId_PH.getText().trim();
				String ghiChu_LH = taGhichu_LH.getText().trim();

				if (id_KH.isEmpty() || ten_LH.isEmpty() || ngayHoc.isEmpty() || id_PH.isEmpty()
						|| ngayketthuc.isEmpty()) {
					JOptionPane.showMessageDialog(LopHoc_Diaglog.this, "Hãy nhập đầy đủ thông tin!");
					return;
				}
				try {
					LopHoc lh_new = new LopHoc();
					lh_new.setId_KH(Integer.parseInt(id_KH));
					lh_new.setTen_LH(ten_LH);
					lh_new.setId_GV(Integer.parseInt(id_GV));
					lh_new.setNgaybatdau(DateSQL.parseDate(ngayHoc));
					lh_new.setNgayketthuc(DateSQL.parseDate(ngayketthuc));
					lh_new.setId_PH(Integer.parseInt(id_PH));
					lh_new.setGhichu_LH(ghiChu_LH);

					// ADD / UPDATE
					if (lh == null) {
						new LopHocDAO().addLopHoc(lh_new);
						JOptionPane.showMessageDialog(LopHoc_Diaglog.this, "Thêm lớp học thành công!");

					} else {
						lh_new.setId_LH(lh.getId_LH());
						new LopHocDAO().updateLopHoc(lh_new);
						JOptionPane.showMessageDialog(LopHoc_Diaglog.this, "Cập nhật lớp học thành công!");
					}

					parentPanel.loadData();
					dispose();
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(LopHoc_Diaglog.this, "Ngày tháng hoặc ID không hợp lệ!");
					e1.printStackTrace();
				} catch (DateSaiException e1) {
					JOptionPane.showMessageDialog(LopHoc_Diaglog.this,
							"Ngày tháng phải có định dạng dd/MM/yyyy hoặc yyyy/MM/dd!");
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(LopHoc_Diaglog.this, "Lỗi! Không thể kết nối tới CSDL!");
					e1.printStackTrace();
				}

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

	private void initTextFields() {

		tfId_KH = new JTextField();
		tfId_KH.setFont(font);
		tfId_KH.setBounds(205, 29, 412, 40);
		getContentPane().add(tfId_KH);
		tfId_KH.setColumns(10);

		tfTen_LH = new JTextField();
		tfTen_LH.setFont(font);
		tfTen_LH.setBounds(205, 91, 412, 40);
		tfTen_LH.setColumns(10);
		getContentPane().add(tfTen_LH);

		tfId_GV = new JTextField();
		tfId_GV.setFont(font);
		tfId_GV.setColumns(10);
		tfId_GV.setBounds(205, 151, 412, 40);
		getContentPane().add(tfId_GV);

		tfNgayHoc = new JTextField();
		tfNgayHoc.setFont(font);
		tfNgayHoc.setBounds(205, 206, 120, 40);
		tfNgayHoc.setToolTipText("Định dạng: dd/mm/yyyy hoặc yy/mm/dd");
		getContentPane().add(tfNgayHoc);
		tfNgayHoc.setColumns(10);

		tfNgayKetThuc = new JTextField();
		tfNgayKetThuc.setToolTipText("Định dạng: dd/mm/yyyy hoặc yy/mm/dd");
		tfNgayKetThuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tfNgayKetThuc.setColumns(10);
		tfNgayKetThuc.setBounds(497, 206, 120, 40);
		getContentPane().add(tfNgayKetThuc);

		tfId_PH = new JTextField();
		tfId_PH.setFont(font);
		tfId_PH.setColumns(10);
		tfId_PH.setBounds(205, 267, 412, 40);
		getContentPane().add(tfId_PH);

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
		JLabel lbKhoaHoc = new JLabel("Khoá học");
		lbKhoaHoc.setHorizontalAlignment(SwingConstants.LEFT);
		lbKhoaHoc.setFont(font);
		lbKhoaHoc.setBounds(83, 29, 91, 40);
		getContentPane().add(lbKhoaHoc);

		JLabel lbTenLop = new JLabel("Tên lớp");
		lbTenLop.setHorizontalAlignment(SwingConstants.LEFT);
		lbTenLop.setFont(font);
		lbTenLop.setBounds(83, 91, 91, 40);
		getContentPane().add(lbTenLop);

		JLabel label_1 = new JLabel("Giảng viên");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(font);
		label_1.setBounds(83, 151, 91, 40);
		getContentPane().add(label_1);

		JLabel lbNgayHoc = new JLabel("Ngày học");
		lbNgayHoc.setHorizontalAlignment(SwingConstants.LEFT);
		lbNgayHoc.setFont(font);
		lbNgayHoc.setBounds(83, 206, 91, 40);
		getContentPane().add(lbNgayHoc);

		JLabel lbNgayKetThuc = new JLabel("Ngày kết thúc");
		lbNgayKetThuc.setFont(font);
		lbNgayKetThuc.setBounds(378, 206, 110, 40);
		getContentPane().add(lbNgayKetThuc);

		JLabel lbPhongHoc = new JLabel("Phòng học");
		lbPhongHoc.setHorizontalAlignment(SwingConstants.LEFT);
		lbPhongHoc.setFont(font);
		lbPhongHoc.setBounds(83, 267, 91, 40);
		getContentPane().add(lbPhongHoc);

		JLabel label = new JLabel("Ghi chú");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(font);
		label.setBounds(83, 323, 91, 40);
		getContentPane().add(label);
	}

}
