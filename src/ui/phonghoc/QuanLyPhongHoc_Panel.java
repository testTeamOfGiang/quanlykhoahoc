package ui.phonghoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.Phonghoc;
import exception.ChuaChonException;
import main.MainApp;
import ui.abstracts.AbsTractQuanLyPanel;
import ui.phonghoc.PhongHoc_Dialog.Type;

public class QuanLyPhongHoc_Panel extends AbsTractQuanLyPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tableModel;
	private int page;
	private Map<Integer, Phonghoc> data;

	public QuanLyPhongHoc_Panel() {
		page = 0;
		data = new HashMap<Integer, Phonghoc>();
		initComponent();
		loadData();
	}

	public void initComponent() {
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "MÃ£ PhÃ²ng Há»�c", "TÃªn PhÃ²ng", "Suc chua" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(tableModel);
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int current = table.getSelectedRow();
					Phonghoc ph = data.get(current);
					containerPanel.setObject(ph);
					containerPanel.showChiTiet();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1400, 550);
		add(scrollPane);
		/* ========================================= */
		JButton btnTrc = new JButton("TrÆ°á»›c");
		btnTrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(page>0) {
					page--;
				}
			}
		});
		btnTrc.setBounds(492, 582, 106, 40);
		add(btnTrc);

		JButton btnSau = new JButton("Sau");
		btnSau.setBounds(801, 582, 106, 40);
		btnSau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				page++;
				loadData();
			}
		});
		add(btnSau);

		JButton btnThm = new JButton("ThÃªm");
		btnThm.setBounds(389, 676, 106, 40);
		btnThm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new PhongHoc_Dialog(Type.ADD, QuanLyPhongHoc_Panel.this, null).setVisible(true);
				;
			}
		});
		add(btnThm);

		JButton btnSa = new JButton("Sá»­a");
		btnSa.setBounds(644, 676, 106, 40);
		btnSa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int current = table.getSelectedRow();
					if (current == -1) {
						throw new ChuaChonException();
					}
					Phonghoc ph = data.get(current);
					new PhongHoc_Dialog(Type.UPDATE, QuanLyPhongHoc_Panel.this, ph).setVisible(true);
				} catch (ChuaChonException ex) {
					JOptionPane.showMessageDialog(QuanLyPhongHoc_Panel.this, "HÃ£y Chá»�n Má»™t PhÃ²ng Há»�c Ä�Ãª Sá»­a");
				}
			}
		});
		add(btnSa);

		JButton btnXa = new JButton("XÃ³a");
		btnXa.setBounds(910, 676, 106, 40);
		btnXa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int current = table.getSelectedRow();
					if (current == -1) {
						throw new ChuaChonException();
					}
					int confirm = JOptionPane.showConfirmDialog(QuanLyPhongHoc_Panel.this,
							"Báº¡n cÃ³ muá»‘n xÃ³a phÃ²ng há»�c nÃ y");
					if (confirm == JOptionPane.YES_OPTION) {
						Phonghoc ph = data.get(current);
						MainApp.phongHocDao.delete(ph);
						loadData();
						JOptionPane.showMessageDialog(QuanLyPhongHoc_Panel.this, "XÃ³a PhÃ²ng Há»�c ThÃ nh CÃ´ng");
					}
				} catch (ChuaChonException ex) {
					JOptionPane.showMessageDialog(QuanLyPhongHoc_Panel.this, "Chon phong truoc!");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(QuanLyPhongHoc_Panel.this, "XÃ³a PhÃ²ng Há»�c KhÃ´ng ThÃ nh CÃ´ng");
					e1.printStackTrace();
				}

			}
		});
		add(btnXa);

		JButton btnTmKim = new JButton("TÃ¬m Kiáº¿m");
		btnTmKim.setBounds(1106, 676, 97, 40);
		btnTmKim.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				containerPanel.showTimKiem();
			}
		});
		add(btnTmKim);

		JButton btnReload = new JButton("Reload");
		btnReload.setBounds(177, 677, 97, 39);
		btnReload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				loadData();
			}
		});
		add(btnReload);
	}

	@Override
	public void loadData() {
		data.clear();
		while (table.getRowCount() > 0) {
			tableModel.removeRow(0);
		}

		try {
			List<Phonghoc> list = MainApp.phongHocDao.getPage(page);
			if(list.size()==0) {
				page--;
				list=MainApp.phongHocDao.getPage(page);
			}
			int stt = 1;
			for (Phonghoc ph : list) {
				tableModel.addRow(new Object[] { stt, ph.getId_PH(), ph.getTen_PH(), ph.getSucChua_PH()
					});
				data.put(stt - 1, ph);
				stt += 1;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(QuanLyPhongHoc_Panel.this, "KhÃ´ng Thá»ƒ Load Dá»¯ Liá»‡u");
			e.printStackTrace();
		}
	}

}
