package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import dao.GiangVienDao;
import dao.HocVienDao;
import dao.KhoaHocDao;
import dao.LopHocDAO;
import dao.PhongHocDao;
import dao.UserDAO;
import ui.MainFrame;

public class MainApp {

	public static GiangVienDao giangVienDao;
	public static HocVienDao hocVienDao;
	public static KhoaHocDao khoaHocDao;
	public static PhongHocDao phongHocDao;
	public static LopHocDAO lopHocDAO;
	public static UserDAO userDao;

	public static void init() {
		giangVienDao = new GiangVienDao();
		hocVienDao = new HocVienDao();
		khoaHocDao = new KhoaHocDao();
		phongHocDao = new PhongHocDao();
		lopHocDAO = new LopHocDAO();
		userDao = new UserDAO();
	}

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		init();

		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		new MainFrame().setVisible(true);
	}
}
