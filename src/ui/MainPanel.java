package ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ui.hocvien.HocVien_Panel;
import ui.khoahoc.KhoaHoc_Panel;
import ui.lophoc.LopHoc_Panel;
import ui.phonghoc.PhongHoc_Panel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public MainPanel() {
		this.setLayout(null);
		this.setSize(1400, 800);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				new DangNhapDialog(MainPanel.this).setVisible(true);
			}
		}).start();
		
	}


	public void showPanel_Lv0() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1400, 800);
		add(tabbedPane);
		tabbedPane.addTab("Quản Lý Giảng Viên", new ui.giangvien.GiangVien_Panel());
		tabbedPane.addTab("Quản lý Học Viên", new HocVien_Panel());
		tabbedPane.addTab("Quản Lý Khóa Học", new KhoaHoc_Panel());
		tabbedPane.addTab("Quản Lý Phòng Học", new PhongHoc_Panel());
		tabbedPane.addTab("Quản Lý Lớp Học", new LopHoc_Panel());
	}
	
	public void showPanel_Lv1() {
		add(new HocVien_Panel());
	}
}
