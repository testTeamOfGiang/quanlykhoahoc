package ui.abstracts;

import javax.swing.JPanel;

public abstract class AbstractTimKiemPanel extends JPanel implements SubPanel {

	private static final long serialVersionUID = 1L;
	public AbsTractContainerPanel containerPanel;

	public AbstractTimKiemPanel() {
		this.setLayout(null);
	}

	@Override
	public void setParrent(AbsTractContainerPanel parrent) {
		this.containerPanel = parrent;
	}
}
