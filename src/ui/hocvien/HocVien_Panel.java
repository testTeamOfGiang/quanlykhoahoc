package ui.hocvien;

import ui.abstracts.AbsTractContainerPanel;

public class HocVien_Panel extends AbsTractContainerPanel {

	private static final long serialVersionUID = 1L;

	public HocVien_Panel() {
		QuanLyHocVien_Panel hocVien_Panel = new QuanLyHocVien_Panel();
		setQuanLyPanel(hocVien_Panel);

		ChiTietHocVien_Panel chiTietHocVien_Panel = new ChiTietHocVien_Panel();
		setChiTietPanel(chiTietHocVien_Panel);

		TimKiemHocVien_Panel timKiemHocVien_Panel = new TimKiemHocVien_Panel();
		setTimKiemPanel(timKiemHocVien_Panel);
	}

}
