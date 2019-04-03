package ui.lophoc;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import config.JDBC_Connection;
import entity.LopHoc;

public class ThemLichHoc_Dialog extends JDialog {

	private static final long serialVersionUID = -4646071127228369177L;

	Font font;
	private ChiTiet_LopHoc parentPanel;
	private LopHoc lh;
	private ArrayList<Checkbox> ckTiets;
	private JComboBox<String> cbThu;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThemLichHoc_Dialog dialog = new ThemLichHoc_Dialog(null, null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	public ThemLichHoc_Dialog(ChiTiet_LopHoc parentPanel, LopHoc lh) {
		getContentPane().setForeground(Color.GRAY);
		font = new Font("Tahoma", Font.PLAIN, 16);
		this.parentPanel = parentPanel;
		this.lh = lh;

		initDialog();
		initLabels();
		iniTCheckBoxes();
		initButtons();
	}

	private void initDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setSize(700, 460);
		setLocationRelativeTo(parentPanel);
		setTitle("Thêm lịch học");
	}

	private void initButtons() {

		JButton btnHuy = new JButton("Hủy");
		btnHuy.setBounds(577, 370, 97, 40);
		btnHuy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnHuy);

		JButton btnThem = new JButton("Thêm");
		btnThem.setBounds(455, 370, 97, 40);
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnThem_Click();
			}
		});
		getContentPane().add(btnThem);

		cbThu = new JComboBox<String>(new String[] { "Hai", "Ba", "Bốn", "Năm", "Sáu", "Bảy", "Chủ Nhật" });
		cbThu.setFont(font);
		cbThu.setBounds(131, 19, 171, 30);
		getContentPane().add(cbThu);

	}

	protected void btnThem_Click() {
		// thu 2 = 0
		// chu nhat = 6
		int thu = cbThu.getSelectedIndex() + 2;
		String tietsChecked = getTietsChecked();
		if (tietsChecked == null) {
			JOptionPane.showMessageDialog(ThemLichHoc_Dialog.this, "Bạn chưa chọn tiết học!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		ArrayList<String> buois = new ArrayList<String>();
		try {
			for(String s: getLstLich(thu)) {
				for (String sub: s.split(";")) {
					buois.add(sub);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(ThemLichHoc_Dialog.this, "Lỗi khi kết nối tới CSDL!", "ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
	}

	private ArrayList<String> getLstLich(int thu) throws SQLException {
		Connection con = JDBC_Connection.getConnection();
		String sql = "select ten_LH, tiet from fn_GetLichHoc(?, ?, ?) where thu = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, lh.getId_PH());
		preparedStatement.setDate(2, lh.getNgaybatdau());
		preparedStatement.setDate(3, lh.getNgayketthuc());
		preparedStatement.setInt(4, thu);
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<String> tiets = new ArrayList<>();
		while (resultSet.next()) {
			tiets.add(resultSet.getString("tiet"));
		}
		return tiets;
	}

	private String getTietsChecked() {
		ArrayList<String> lstBuoi = new ArrayList<>();
		boolean status = false;
		int i = 0;
		int dau = 0, cuoi = 0;
		while (true) {

			if (status && ckTiets.get(i).getState()) {
				i++;
			} else if (!status && !ckTiets.get(i).getState()) {
				i++;
			}
			// bắt đầu 1 chuỗi checked
			if (!status && ckTiets.get(i).getState()) {
				dau = i;
				status = true;
			}
			// kết thúc chuỗi checked
			if (status && !ckTiets.get(i).getState()) {
				cuoi = i - 1;
				status = false;
				String s = dau == cuoi ? (dau + 1) + ";" : (dau + 1) + "," + (cuoi + 1) + ";";
				lstBuoi.add(s);
			}

			// xử lý riêng vị trí cuối cùng
			if (status && i == ckTiets.size() - 1) {
				cuoi = i;
				status = false;
				String s = dau == cuoi ? (dau + 1) + ";" : (dau + 1) + "," + (cuoi + 1) + ";";
				lstBuoi.add(s);
			}

			if (i == ckTiets.size() - 1)
				break;
		}

		StringBuffer result = new StringBuffer();
		for (String buoi : lstBuoi) {
			result.append(buoi);
		}
		if (result.length() != 0)
			return result.toString();
		else
			return null;
	}

	private void iniTCheckBoxes() {

		Checkbox ckTiet1 = new Checkbox("Tiết 1");
		ckTiet1.setForeground(Color.BLACK);
		ckTiet1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet1.setBounds(71, 121, 90, 30);
		getContentPane().add(ckTiet1);

		Checkbox ckTiet2 = new Checkbox("Tiết 2");
		ckTiet2.setForeground(Color.BLACK);
		ckTiet2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet2.setBounds(71, 165, 90, 30);
		getContentPane().add(ckTiet2);

		Checkbox ckTiet3 = new Checkbox("Tiết 3");
		ckTiet3.setForeground(Color.BLACK);
		ckTiet3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet3.setBounds(71, 206, 90, 30);
		getContentPane().add(ckTiet3);

		Checkbox ckTiet4 = new Checkbox("Tiết 4");
		ckTiet4.setForeground(Color.BLACK);
		ckTiet4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet4.setBounds(71, 249, 90, 30);
		getContentPane().add(ckTiet4);

		Checkbox ckTiet5 = new Checkbox("Tiết 5");
		ckTiet5.setForeground(Color.BLACK);
		ckTiet5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet5.setBounds(71, 291, 90, 30);
		getContentPane().add(ckTiet5);

		Checkbox ckTiet6 = new Checkbox("Tiết 6");
		ckTiet6.setForeground(Color.BLACK);
		ckTiet6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet6.setBounds(214, 121, 90, 30);
		getContentPane().add(ckTiet6);

		Checkbox ckTiet7 = new Checkbox("Tiết 7");
		ckTiet7.setForeground(Color.BLACK);
		ckTiet7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet7.setBounds(214, 165, 90, 30);
		getContentPane().add(ckTiet7);

		Checkbox ckTiet8 = new Checkbox("Tiết 8");
		ckTiet8.setForeground(Color.BLACK);
		ckTiet8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet8.setBounds(214, 206, 90, 30);
		getContentPane().add(ckTiet8);

		Checkbox ckTiet9 = new Checkbox("Tiết 9");
		ckTiet9.setForeground(Color.BLACK);
		ckTiet9.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet9.setBounds(214, 249, 90, 30);
		getContentPane().add(ckTiet9);

		Checkbox ckTiet10 = new Checkbox("Tiết 10");
		ckTiet10.setForeground(Color.BLACK);
		ckTiet10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet10.setBounds(214, 291, 90, 30);
		getContentPane().add(ckTiet10);

		Checkbox ckTiet16 = new Checkbox("Tiết 16");
		ckTiet16.setForeground(Color.BLACK);
		ckTiet16.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet16.setBounds(531, 121, 90, 30);
		getContentPane().add(ckTiet16);

		Checkbox ckTiet17 = new Checkbox("Tiết 17");
		ckTiet17.setForeground(Color.BLACK);
		ckTiet17.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet17.setBounds(531, 165, 90, 30);
		getContentPane().add(ckTiet17);

		Checkbox ckTiet18 = new Checkbox("Tiết 18");
		ckTiet18.setForeground(Color.BLACK);
		ckTiet18.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet18.setBounds(531, 206, 90, 30);
		getContentPane().add(ckTiet18);

		Checkbox ckTiet19 = new Checkbox("Tiết 19");
		ckTiet19.setForeground(Color.BLACK);
		ckTiet19.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet19.setBounds(531, 249, 90, 30);
		getContentPane().add(ckTiet19);

		Checkbox ckTiet20 = new Checkbox("Tiết 20");
		ckTiet20.setForeground(Color.BLACK);
		ckTiet20.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet20.setBounds(531, 291, 90, 30);
		getContentPane().add(ckTiet20);

		Checkbox ckTiet11 = new Checkbox("Tiết 11");
		ckTiet11.setForeground(Color.BLACK);
		ckTiet11.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet11.setBounds(368, 121, 90, 30);
		getContentPane().add(ckTiet11);

		Checkbox ckTiet12 = new Checkbox("Tiết 12");
		ckTiet12.setForeground(Color.BLACK);
		ckTiet12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet12.setBounds(368, 165, 90, 30);
		getContentPane().add(ckTiet12);

		Checkbox ckTiet13 = new Checkbox("Tiết 13");
		ckTiet13.setForeground(Color.BLACK);
		ckTiet13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet13.setBounds(368, 206, 90, 30);
		getContentPane().add(ckTiet13);

		Checkbox ckTiet14 = new Checkbox("Tiết 14");
		ckTiet14.setForeground(Color.BLACK);
		ckTiet14.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet14.setBounds(368, 249, 90, 30);
		getContentPane().add(ckTiet14);

		Checkbox ckTiet15 = new Checkbox("Tiết 15");
		ckTiet15.setForeground(Color.BLACK);
		ckTiet15.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ckTiet15.setBounds(368, 291, 90, 30);
		getContentPane().add(ckTiet15);

		ckTiets = new ArrayList<>();
		ckTiets.add(ckTiet1);
		ckTiets.add(ckTiet2);
		ckTiets.add(ckTiet3);
		ckTiets.add(ckTiet4);
		ckTiets.add(ckTiet5);
		ckTiets.add(ckTiet6);
		ckTiets.add(ckTiet7);
		ckTiets.add(ckTiet8);
		ckTiets.add(ckTiet9);
		ckTiets.add(ckTiet10);
		ckTiets.add(ckTiet11);
		ckTiets.add(ckTiet12);
		ckTiets.add(ckTiet13);
		ckTiets.add(ckTiet14);
		ckTiets.add(ckTiet15);
		ckTiets.add(ckTiet16);
		ckTiets.add(ckTiet17);
		ckTiets.add(ckTiet18);
		ckTiets.add(ckTiet19);
		ckTiets.add(ckTiet20);
	}

	private void initLabels() {
		JLabel lblNhpMHc = new JLabel("Thứ");
		lblNhpMHc.setHorizontalAlignment(SwingConstants.LEFT);
		lblNhpMHc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNhpMHc.setBounds(71, 11, 50, 40);
		getContentPane().add(lblNhpMHc);

		JSeparator separator = new JSeparator();
		separator.setBounds(71, 62, 537, 2);
		getContentPane().add(separator);

		JLabel lblChnTitHc = new JLabel("Tiết học");
		lblChnTitHc.setHorizontalAlignment(SwingConstants.LEFT);
		lblChnTitHc.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChnTitHc.setBounds(71, 75, 70, 40);
		getContentPane().add(lblChnTitHc);
	}
}
