package ui.abstracts;

import javax.swing.JPanel;

public abstract class AbsTractChiTietPanel extends JPanel implements SubPanel {

	private static final long serialVersionUID = 1L;

	public AbsTractContainerPanel containerPanel;
	protected Object obj;

	public AbsTractChiTietPanel() {
		this.setLayout(null);
	}

	@Override
	public void setParrent(AbsTractContainerPanel parrent) {
		this.containerPanel = parrent;
	}


	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
