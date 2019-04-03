package entity;

public class HocVien_LopHoc {

	private int id_HV;
	private int id_LH;
	private float diem_1;
	private float diem_2;
	private float diem_3;
	private float diem_4;
	private String ghichu_HVLH;

	/**
	 * -1 tức là chưa có điểm
	 * 
	 * @return -1 nếu chưa có điểm và ngược lại
	 */
	public float getDiem_1() {
		return diem_1;
	}

	public void setDiem_1(float diem_1) {
		this.diem_1 = diem_1;
	}

	/**
	 * -1 tức là chưa có điểm
	 * 
	 * @return -1 nếu chưa có điểm và ngược lại
	 */
	public float getDiem_2() {
		return diem_2;
	}

	public void setDiem_2(float diem_2) {
		this.diem_2 = diem_2;
	}

	/**
	 * -1 tức là chưa có điểm
	 * 
	 * @return -1 nếu chưa có điểm và ngược lại
	 */
	public float getDiem_3() {
		return diem_3;
	}

	public void setDiem_3(float diem_3) {
		this.diem_3 = diem_3;
	}

	/**
	 * -1 tức là chưa có điểm
	 * 
	 * @return -1 nếu chưa có điểm và ngược lại
	 */
	public float getDiem_4() {
		return diem_4;
	}

	public void setDiem_4(float diem_4) {
		this.diem_4 = diem_4;
	}

	public int getId_HV() {
		return id_HV;
	}

	public int getId_LH() {
		return id_LH;
	}

	public String getGhichu_HVLH() {
		return ghichu_HVLH;
	}

	public void setGhichu_HVLH(String ghichu_HVLH) {
		this.ghichu_HVLH = ghichu_HVLH;
	}

	public void setId_HV(int id_HV) {
		this.id_HV = id_HV;
	}
	
	public void setId_LH(int id_LH) {
		this.id_LH = id_LH;
	}

}
