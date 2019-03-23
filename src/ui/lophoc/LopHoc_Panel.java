package ui.lophoc;

import ui.abstracts.AbsTractContainerPanel;

public class LopHoc_Panel extends AbsTractContainerPanel{

	private static final long serialVersionUID = -1709862763514983259L;
	
	public LopHoc_Panel(){
		QuanLyLopHoc_Panel quanLyLopHoc_Panel = new QuanLyLopHoc_Panel();
		setQuanLyPanel(quanLyLopHoc_Panel);
		
		TimKiem_LopHoc timKiem_LopHoc = new TimKiem_LopHoc();
		setTimKiemPanel(timKiem_LopHoc);
		
		ChiTiet_LopHoc chiTiet_LopHoc = new ChiTiet_LopHoc();
		setChiTietPanel(chiTiet_LopHoc);
	}
	
}
