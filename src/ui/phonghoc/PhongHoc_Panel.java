package ui.phonghoc;

import ui.abstracts.AbsTractContainerPanel;

public class PhongHoc_Panel extends AbsTractContainerPanel {

	private static final long serialVersionUID = 1L;

	public PhongHoc_Panel() {
		setQuanLyPanel(new QuanLyPhongHoc_Panel());

		setChiTietPanel(new ChiTietPhongHoc_Panel());

		setTimKiemPanel(new TimKiemPhongHoc_Panel());
	}
}
