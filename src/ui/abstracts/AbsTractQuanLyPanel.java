package ui.abstracts;

import javax.swing.JPanel;

public abstract class AbsTractQuanLyPanel extends JPanel implements SubPanel {

	private static final long serialVersionUID = 1L;
	public AbsTractContainerPanel containerPanel;

	public AbsTractQuanLyPanel() {
		this.setLayout(null);
	}

	@Override
	public void setParrent(AbsTractContainerPanel parrent) {
		this.containerPanel = parrent;
	}
}
