package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import dao.GiangVienDao;
import ui.MainFrame;

public class MainApp {

	public static GiangVienDao giangVienDao;

	public static void init() {
		giangVienDao = new GiangVienDao();
	}

	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		init();
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		new MainFrame().setVisible(true);
		System.out.println("exit");
	}
}
