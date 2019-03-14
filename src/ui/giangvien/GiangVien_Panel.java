package ui.giangvien;

import ui.abstracts.AbsTractContainerPanel;

public class GiangVien_Panel extends AbsTractContainerPanel {

	private static final long serialVersionUID = 1L;

	public GiangVien_Panel() {
		QuanLyGiangVien_Panel quanLyGiangVien_Panel = new QuanLyGiangVien_Panel();
		setQuanLyPanel(quanLyGiangVien_Panel);

		ChiTietGiangVien_Panel chiTietGiangVien_Panel = new ChiTietGiangVien_Panel();
		setChiTietPanel(chiTietGiangVien_Panel);

		TimKiemGiangVien_Panel timKiemGiangVien_Panel = new TimKiemGiangVien_Panel();
		setTimKiemPanel(timKiemGiangVien_Panel);
	}

}
