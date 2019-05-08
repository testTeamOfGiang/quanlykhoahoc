package ui.lophoc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import dao.GiangVienDao;
import dao.KhoaHocDao;
import dao.LopHocDAO;
import dao.PhongHocDao;
import entity.Giangvien;
import entity.Khoahoc;
import entity.LopHoc;
import entity.Phonghoc;
import exception.DateSaiException;
import utils.DateSQL;

public class Them_Sua_LopHoc_Dialog extends JDialog {

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

	private JLabel lbFindTenPH;

	private JLabel lbFindTenGV;

	private JLabel lbFindTenKH;

	/**
	 * Tạo dialog để thêm hoặc sửa lớp học
	 * 
	 * @param parentPanel
	 * @param lh          đưa vào nếu muốn sửa
	 */
	public Them_Sua_LopHoc_Dialog(QuanLyLopHoc_Panel parentPanel, LopHoc lh) {

		font = new Font("Tahoma", Font.PLAIN, 16);
		this.parentPanel = parentPanel;

		initDialog();
		initTextFields();
		initLabels();
		initButtons();
		addTextFieldEvents();

		if (lh != null) {
			setTitle("Sửa thông tin lớp");
			this.lh = lh;
			setTextForEditing();
		} else {
			setTitle("Thêm lớp");
			setTipText();
		}

		addPlaceHolder();
	}

	private void setTipText() {
		// set tip text placeholder cho lần đầu
		if (this.lh == null) {
			tfId_GV.setText("Nhập ID giảng viên...");
			tfId_GV.setForeground(Color.LIGHT_GRAY);

			tfId_KH.setText("Nhập ID khoá học...");
			tfId_KH.setForeground(Color.LIGHT_GRAY);

			tfId_PH.setText("Nhập ID phòng học...");
			tfId_PH.setForeground(Color.LIGHT_GRAY);

			tfNgayHoc.setText("dd/mm/yyyy");
			tfNgayHoc.setForeground(Color.LIGHT_GRAY);

			tfNgayKetThuc.setText("dd/mm/yyyy");
			tfNgayKetThuc.setForeground(Color.LIGHT_GRAY);

			tfTen_LH.setText("Nhập tên lớp học...");
			tfTen_LH.setForeground(Color.LIGHT_GRAY);
		}
	}

	private void setTextForEditing() {
		tfId_KH.setText(lh.getId_KH() + "");
		tfTen_LH.setText(lh.getTen_LH());
		tfId_PH.setText(lh.getId_PH() + "");
		tfId_GV.setText(lh.getId_GV() + "");
		tfNgayHoc.setText(DateSQL.toVNDate(lh.getNgaybatdau()));
		tfNgayKetThuc.setText(DateSQL.toVNDate(lh.getNgayketthuc()));
		taGhichu_LH.setText(lh.getGhichu_LH());
	}

	private void initDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 525);
		setLocationRelativeTo(parentPanel);
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
		getContentPane().add(tfNgayHoc);
		tfNgayHoc.setColumns(10);

		tfNgayKetThuc = new JTextField();
		tfNgayKetThuc.setFont(font);
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

	// Thêm sự kiện nhập sửa gì thì nó hiện luôn kết quả bên trên
	private void addTextFieldEvents() {

		tfId_GV.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String tenGV = loadTenGV();
				if (tenGV != null) {
					lbFindTenGV.setText("GV: " + loadTenGV());
					lbFindTenGV.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy giảng viên!";
					lbFindTenGV.setText(errMessage);
					lbFindTenGV.setForeground(Color.red);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String tenGV = loadTenGV();
				if (tenGV != null) {
					lbFindTenGV.setText("GV: " + loadTenGV());
					lbFindTenGV.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy giảng viên!";
					lbFindTenGV.setText(errMessage);
					lbFindTenGV.setForeground(Color.red);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				String tenGV = loadTenGV();
				if (tenGV != null) {
					lbFindTenGV.setText("GV: " + loadTenGV());
					lbFindTenGV.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy giảng viên!";
					lbFindTenGV.setText(errMessage);
					lbFindTenGV.setForeground(Color.red);
				}
			}
		});

		tfId_KH.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String tenKH = loadTenKH();
				if (tenKH != null) {
					lbFindTenKH.setText("KH: " + loadTenKH());
					lbFindTenKH.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy khoá học!";
					lbFindTenKH.setText(errMessage);
					lbFindTenKH.setForeground(Color.red);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String tenKH = loadTenKH();
				if (tenKH != null) {
					lbFindTenKH.setText("KH: " + loadTenKH());
					lbFindTenKH.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy khoá học!";
					lbFindTenKH.setText(errMessage);
					lbFindTenKH.setForeground(Color.red);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				String tenKH = loadTenKH();
				if (tenKH != null) {
					lbFindTenKH.setText("KH: " + loadTenKH());
					lbFindTenKH.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy khoá học!";
					lbFindTenKH.setText(errMessage);
					lbFindTenKH.setForeground(Color.red);
				}
			}
		});

		tfId_PH.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				String tenPH = loadTenPH();
				if (tenPH != null) {
					lbFindTenPH.setText("PH: " + loadTenPH());
					lbFindTenPH.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy phòng học!";
					lbFindTenPH.setText(errMessage);
					lbFindTenPH.setForeground(Color.red);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				String tenPH = loadTenPH();
				if (tenPH != null) {
					lbFindTenPH.setText("PH: " + loadTenPH());
					lbFindTenPH.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy phòng học!";
					lbFindTenPH.setText(errMessage);
					lbFindTenPH.setForeground(Color.red);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				String tenPH = loadTenPH();
				if (tenPH != null) {
					lbFindTenPH.setText("PH: " + loadTenPH());
					lbFindTenPH.setForeground(Color.blue);
				} else {
					String errMessage = "Không tìm thấy phòng học!";
					lbFindTenPH.setText(errMessage);
					lbFindTenPH.setForeground(Color.red);
				}
			}
		});
	}

	private String loadTenGV() {
		String tenGV = null;
		try {
			String text = tfId_GV.getText().trim();
			if (!text.equals("") && text.matches("\\d*")) {
				Giangvien gv = new GiangVienDao().findByID(Integer.parseInt(text));
				if (gv != null)
					tenGV = gv.getTen_GV();
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return tenGV;
	}

	private String loadTenPH() {
		String tenPH = null;
		try {
			String text = tfId_PH.getText().trim();
			if (!text.equals("") && text.matches("\\d*")) {
				Phonghoc ph = new PhongHocDao().findById(Integer.parseInt(text));
				if (ph != null)
					tenPH = ph.getTen_PH();
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return tenPH;
	}

	private String loadTenKH() {
		String tenKH = null;
		try {
			String text = tfId_KH.getText().trim();
			if (!text.equals("") && text.matches("\\d*")) {
				Khoahoc kh = new KhoaHocDao().findById(Integer.parseInt(text));
				if (kh != null)
					tenKH = kh.getTen_KH();
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return tenKH;
	}

	// Thêm placeholder cho text field
	private void addPlaceHolder() {
		tfId_GV.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (tfId_GV.getText().equals("")) {
					tfId_GV.setText("Nhập ID giảng viên...");
					tfId_GV.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (tfId_GV.getText().equals("Nhập ID giảng viên...")) {
					tfId_GV.setText("");
					tfId_GV.setForeground(Color.black);
				}
			}
		});

		tfId_KH.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (tfId_KH.getText().equals("")) {
					tfId_KH.setText("Nhập ID khoá học...");
					tfId_KH.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (tfId_KH.getText().equals("Nhập ID khoá học...")) {
					tfId_KH.setText("");
					tfId_KH.setForeground(Color.black);
				}
			}
		});

		tfId_PH.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (tfId_PH.getText().equals("")) {
					tfId_PH.setText("Nhập ID phòng học...");
					tfId_PH.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (tfId_PH.getText().equals("Nhập ID phòng học...")) {
					tfId_PH.setText("");
					tfId_PH.setForeground(Color.black);
				}
			}
		});

		tfNgayHoc.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (tfNgayHoc.getText().equals("")) {
					tfNgayHoc.setText("dd/mm/yyyy");
					tfNgayHoc.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (tfNgayHoc.getText().equals("dd/mm/yyyy")) {
					tfNgayHoc.setText("");
					tfNgayHoc.setForeground(Color.black);
				}
			}
		});

		tfNgayKetThuc.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (tfNgayKetThuc.getText().equals("")) {
					tfNgayKetThuc.setText("dd/mm/yyyy");
					tfNgayKetThuc.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (tfNgayKetThuc.getText().equals("dd/mm/yyyy")) {
					tfNgayKetThuc.setText("");
					tfNgayKetThuc.setForeground(Color.black);
				}
			}
		});

		tfTen_LH.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if (tfTen_LH.getText().equals("")) {
					tfTen_LH.setText("Nhập tên lớp học...");
					tfTen_LH.setForeground(Color.LIGHT_GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (tfTen_LH.getText().equals("Nhập tên lớp học...")) {
					tfTen_LH.setText("");
					tfTen_LH.setForeground(Color.black);
				}
			}
		});
	}

	private void btnOK_Click() {
		String id_KH = tfId_KH.getText().trim();
		String ten_LH = tfTen_LH.getText().trim();
		String id_GV = tfId_GV.getText().trim();
		String ngayHoc = tfNgayHoc.getText().trim();
		String ngayketthuc = tfNgayKetThuc.getText().trim();
		String id_PH = tfId_PH.getText().trim();
		String ghiChu_LH = taGhichu_LH.getText().trim();

		if (id_KH.isEmpty() || ten_LH.isEmpty() || ngayHoc.isEmpty() || id_PH.isEmpty() || ngayketthuc.isEmpty()) {
			JOptionPane.showMessageDialog(Them_Sua_LopHoc_Dialog.this, "Hãy nhập đầy đủ thông tin!");
			return;
		}

		try {
			if (DateSQL.Compare(DateSQL.parseDate(ngayHoc), DateSQL.parseDate(ngayketthuc)) > 0) {
				JOptionPane.showMessageDialog(Them_Sua_LopHoc_Dialog.this, "Ngày kết thúc phải sau ngày bắt đầu học!");
				return;
			}
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
				JOptionPane.showMessageDialog(Them_Sua_LopHoc_Dialog.this, "Thêm lớp học thành công!");

			} else {
				lh_new.setId_LH(lh.getId_LH());
				new LopHocDAO().updateLopHoc(lh_new);
				JOptionPane.showMessageDialog(Them_Sua_LopHoc_Dialog.this, "Cập nhật lớp học thành công!");
			}

			parentPanel.loadData();
			dispose();
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(Them_Sua_LopHoc_Dialog.this,
					"Dữ liệu nhập vào có vấn đề! Vui lòng kiểm tra lại.", "Thông báo", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (DateSaiException e1) {
			JOptionPane.showMessageDialog(Them_Sua_LopHoc_Dialog.this,
					"Ngày tháng phải có định dạng dd/MM/yyyy hoặc yyyy/MM/dd!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(Them_Sua_LopHoc_Dialog.this, "Dữ liệu nhập vào có vấn đề! Vui lòng kiểm tra lại.", "ERROR",
					JOptionPane.ERROR_MESSAGE);
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

		Font f2 = new Font("Tahoma", Font.PLAIN, 14);
		lbFindTenPH = new JLabel("");
		lbFindTenPH.setBounds(205, 270, 208, 19);
		lbFindTenPH.setFont(f2);
		getContentPane().add(lbFindTenPH);

		lbFindTenGV = new JLabel("");
		lbFindTenGV.setBounds(205, 147, 208, 19);
		lbFindTenGV.setFont(f2);
		getContentPane().add(lbFindTenGV);

		lbFindTenKH = new JLabel("");
		lbFindTenKH.setBounds(205, 16, 208, 19);
		lbFindTenKH.setFont(f2);
		getContentPane().add(lbFindTenKH);
	}

}
