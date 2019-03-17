package entity;

public class LopCho {
	private int id_HV;
	private int id_LH;
	private String ghichu_LC;

	public LopCho() {
		super();
	}

	public LopCho(int id_HV, int id_LH, String ghichu_LC) {
		super();
		this.id_HV = id_HV;
		this.id_LH = id_LH;
		this.ghichu_LC = ghichu_LC;
	}

	public int getId_HV() {
		return id_HV;
	}

	public void setId_HV(int id_HV) {
		this.id_HV = id_HV;
	}

	public int getId_LH() {
		return id_LH;
	}

	public void setId_LH(int id_LH) {
		this.id_LH = id_LH;
	}

	public String getGhichu_LC() {
		return ghichu_LC;
	}

	public void setGhichu_LC(String ghichu_LC) {
		this.ghichu_LC = ghichu_LC;
	}

}
