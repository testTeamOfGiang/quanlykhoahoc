package ui.thietke;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import ui.lophoc.ChiTiet_LopHoc;

public class ThemLichHoc_Dialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2505373032096896088L;
	Font font;
	private ChiTiet_LopHoc parentPanel;
	public static void main(String[] args) {
		try {
			ThemLichHoc_Dialog dialog = new ThemLichHoc_Dialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public ThemLichHoc_Dialog() {
		getContentPane().setForeground(Color.GRAY);
		font = new Font("Tahoma", Font.PLAIN, 16);
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
		setTitle("Cập nhật danh sách học viên");
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
				
			}
		});
		getContentPane().add(btnThem);
		
		//JComboBox<String> comboBox = new JComboBox<String>(new String[] {"Hai", "Ba", "Bốn", "Năm", "Sáu", "Bảy", "Chủ Nhật"});
		JComboBox<?> comboBox = new JComboBox<Object>();
		comboBox.setFont(font);
		comboBox.setBounds(131, 19, 171, 30);
		getContentPane().add(comboBox);
		
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
