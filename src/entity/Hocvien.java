package entity;

import java.sql.Date;

public class Hocvien {

	private int id_HV;

	private String diachi_HV;

	private String sodt_HV;

	private String ten_HV;

	private Date ngaysinh_HV;
	
	// bi-directional many-to-many association to Lophoc

	public Hocvien() {
	}

	public int getId_HV() {
		return this.id_HV;
	}

	public void setId_HV(int id_HV) {
		this.id_HV = id_HV;
	}

	public String getDiachi_HV() {
		return this.diachi_HV;
	}

	public void setDiachi_HV(String diachi_HV) {
		this.diachi_HV = diachi_HV;
	}

	public String getSodt_HV() {
		return this.sodt_HV;
	}

	public void setSodt_HV(String sodt_HV) {
		this.sodt_HV = sodt_HV;
	}

	public String getTen_HV() {
		return this.ten_HV;
	}

	public void setTen_HV(String ten_HV) {
		this.ten_HV = ten_HV;
	}

	public Date getNgaysinh_HV() {
		return ngaysinh_HV;
	}

	public void setNgaysinh_HV(Date ngaysinh_HV) {
		this.ngaysinh_HV = ngaysinh_HV;
	}

}