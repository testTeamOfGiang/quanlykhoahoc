package ui.abstracts;

import javax.swing.JPanel;

public abstract class AbsTractChiTietPanel extends JPanel implements SubPanel {

	private static final long serialVersionUID = 1L;

	public AbsTractContainerPanel containerPanel;

	@Override
	public void setParrent(AbsTractContainerPanel parrent) {
		this.containerPanel = parrent;
	}


}
