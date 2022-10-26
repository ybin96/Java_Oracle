package practice;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CharPanel extends JPanel {
	public CharPanel() {
		JCheckBox jb01 = new JCheckBox("귀여움");
		JCheckBox jb02 = new JCheckBox("아름다움");
		JCheckBox jb03 = new JCheckBox("사랑스러움");
		
		add(jb01);
		add(jb02);
		add(jb03);
		
	}
}
