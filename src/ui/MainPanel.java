package ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ui.hocvien.HocVien_Panel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public MainPanel() {
		this.setLayout(null);
		this.setSize(1400, 800);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1400, 800);
		add(tabbedPane);

		tabbedPane.addTab("Quan ly giang vien", new ui.giangvien.GiangVien_Panel());
		tabbedPane.addTab("Quản lý học viên", new HocVien_Panel());
	}
}
