package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import dao.GiangVienDao;
import dao.HocVienDao;
import dao.KhoaHocDao;
import dao.PhongHocDao;
import ui.MainFrame;

public class MainApp {

	public static GiangVienDao giangVienDao;
	public static HocVienDao hocVienDao;
	public static KhoaHocDao khoaHocDao;
	public static PhongHocDao phongHocDao;

	public static void init() {
		giangVienDao = new GiangVienDao();
		hocVienDao = new HocVienDao();
		khoaHocDao = new KhoaHocDao();
		phongHocDao = new PhongHocDao();
	}

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		init();
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		new MainFrame().setVisible(true);
	}
}
