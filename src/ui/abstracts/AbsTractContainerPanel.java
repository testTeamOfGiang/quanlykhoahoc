package ui.abstracts;

import java.awt.CardLayout;

import javax.swing.JPanel;

public abstract class AbsTractContainerPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public AbsTractContainerPanel() {
		this.setSize(1400, 800);
		this.setLayout(new CardLayout());
	}

	private AbsTractChiTietPanel chiTietPanel;
	private AbsTractQuanLyPanel quanLyPanel;
	private AbstractTimKiemPanel timKiemPanel;

	public void showChiTiet() {
		quanLyPanel.setVisible(false);
		timKiemPanel.setVisible(false);
		chiTietPanel.setVisible(true);
	}

	public void showQuanLy() {
		timKiemPanel.setVisible(false);
		chiTietPanel.setVisible(false);
		quanLyPanel.setVisible(true);
	}

	public void showTimKiem() {
		chiTietPanel.setVisible(false);
		quanLyPanel.setVisible(false);
		timKiemPanel.setVisible(true);
	}

	public void setChiTietPanel(AbsTractChiTietPanel chiTietPanel) {
		this.chiTietPanel = chiTietPanel;
		chiTietPanel.setParrent(this);
	}

	public void setTimKiemPanel(AbstractTimKiemPanel timKiemPanel) {
		this.timKiemPanel = timKiemPanel;
		timKiemPanel.setParrent(this);
	}

	public void setQuanLyPanel(AbsTractQuanLyPanel quanLyPanel) {
		this.quanLyPanel = quanLyPanel;
		quanLyPanel.setParrent(this);
	}

	public void setObject(Object obj) {
		chiTietPanel.setObj(obj);
		chiTietPanel.loadData();
	}
}
