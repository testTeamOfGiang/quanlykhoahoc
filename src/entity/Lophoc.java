package entity;

import java.util.Date;

public class Lophoc {

	private int id_LH;

	private int id_KH;

	private int id_GV;

	private int id_PH;

	private Date ngaybatdau;

	private Date ngayketthuc;

	public Lophoc() {
	}

	public int getId_LH() {
		return this.id_LH;
	}

	public void setId_LH(int id_LH) {
		this.id_LH = id_LH;
	}

	public Date getNgaybatdau() {
		return this.ngaybatdau;
	}

	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}

	public Date getNgayketthuc() {
		return this.ngayketthuc;
	}

	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}

	public int getId_PH() {
		return id_PH;
	}

	public void setId_PH(int id_PH) {
		this.id_PH = id_PH;
	}

	public int getId_GV() {
		return id_GV;
	}

	public void setId_GV(int id_GV) {
		this.id_GV = id_GV;
	}

	public int getId_KH() {
		return id_KH;
	}

	public void setId_KH(int id_KH) {
		this.id_KH = id_KH;
	}

}