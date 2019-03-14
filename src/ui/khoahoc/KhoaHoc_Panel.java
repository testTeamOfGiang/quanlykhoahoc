package ui.khoahoc;

import ui.abstracts.AbsTractContainerPanel;

public class KhoaHoc_Panel extends AbsTractContainerPanel {

	private static final long serialVersionUID = 1L;

	public KhoaHoc_Panel() {
		setQuanLyPanel(new QuanLyKhoaHoc_Panel());

		setChiTietPanel(new ChiTietKhoaHoc_Panel());

		setTimKiemPanel(new TimKiemKhoaHoc_Panel());
	}
}
