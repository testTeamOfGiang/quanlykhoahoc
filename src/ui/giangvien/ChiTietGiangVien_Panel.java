package ui.giangvien;

import java.awt.Color;

import entity.Giangvien;
import ui.abstracts.AbsTractChiTietPanel;

public class ChiTietGiangVien_Panel extends AbsTractChiTietPanel{

	private Giangvien gv;
	
	private static final long serialVersionUID = 1L;
	public ChiTietGiangVien_Panel() {
		this.setBackground(Color.green);
	}
	@Override
	public void loadData() {
		// TODO Auto-generated method stub
		
	}
	public Giangvien getGv() {
		return gv;
	}
	public void setGv(Giangvien gv) {
		this.gv = gv;
	}

}
