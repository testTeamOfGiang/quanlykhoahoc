package entity;

public class LichHoc {
	private int id_LIH;
	private int id_LH;
	private String thu;
	private String tiet;
	private String ghichu_LIH;

	public LichHoc() {
	}

	public LichHoc(int id_LH, String thu, String tiet, String ghichu_LIH) {
		super();
		this.id_LH = id_LH;
		this.thu = thu;
		this.tiet = tiet;
		this.ghichu_LIH = ghichu_LIH;
	}

	/**
	 * id Lớp học
	 * @return int
	 */
	public int getId_LH() {
		return id_LH;
	}

	/**
	 * id Lớp học
	 * @param id_LH id Lớp học
	 */
	public void setId_LH(int id_LH) {
		this.id_LH = id_LH;
	}

	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}

	public String getTiet() {
		return tiet;
	}

	public void setTiet(String tiet) {
		this.tiet = tiet;
	}

	public String getGhichu_LIH() {
		return ghichu_LIH;
	}

	public void setGhichu_LIH(String ghichu_LIH) {
		this.ghichu_LIH = ghichu_LIH;
	}

	public int getId_LIH() {
		return id_LIH;
	}

	public void setId_LIH(int id_LIH) {
		this.id_LIH = id_LIH;
	}

}
