package entity;

import java.sql.Date;


public class Giangvien {

	private int id_GV;

	private String diachi_GV;

	private String ghichu_GV;

	private Date ngaysinh_GV;

	private String sodt_GV;

	private String ten_GV;

	public Giangvien() {
	}

	public int getId_GV() {
		return this.id_GV;
	}

	public void setId_GV(int id_GV) {
		this.id_GV = id_GV;
	}

	public String getDiachi_GV() {
		return this.diachi_GV;
	}

	public void setDiachi_GV(String diachi_GV) {
		this.diachi_GV = diachi_GV;
	}

	public String getGhichu_GV() {
		return this.ghichu_GV;
	}

	public void setGhichu_GV(String ghichu_GV) {
		this.ghichu_GV = ghichu_GV;
	}

	public Date getNgaysinh_GV() {
		return this.ngaysinh_GV;
	}

	public void setNgaysinh_GV(Date ngaysinh_GV) {
		this.ngaysinh_GV = ngaysinh_GV;
	}

	public String getSodt_GV() {
		return this.sodt_GV;
	}

	public void setSodt_GV(String sodt_GV) {
		this.sodt_GV = sodt_GV;
	}

	public String getTen_GV() {
		return this.ten_GV;
	}

	public void setTen_GV(String ten_GV) {
		this.ten_GV = ten_GV;
	}

}