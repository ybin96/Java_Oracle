package exam04;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class GenderPanel extends JPanel {
	
	public GenderPanel() {
		JRadioButton jrb01 = new JRadioButton("남자");
		JRadioButton jrb02 = new JRadioButton("여자");
		ButtonGroup gb = new ButtonGroup();
		
		gb.add(jrb01);
		gb.add(jrb02);
		
		add(jrb01);
		add(jrb02);
		
	}

}
