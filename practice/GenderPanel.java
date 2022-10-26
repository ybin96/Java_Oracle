package practice;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GenderPanel extends JPanel {

	public GenderPanel() {
		JRadioButton jb01 = new JRadioButton("남자");
		JRadioButton jb02 = new JRadioButton("여자");
		ButtonGroup bg = new ButtonGroup();
		
		bg.add(jb01);
		bg.add(jb02);
		
		add(jb01);
		add(jb02);
	}
}
