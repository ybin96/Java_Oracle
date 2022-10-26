package practice;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	GenderPanel jp ;
	CharPanel cp;
	
	public MainFrame() {
		setTitle("희주");
		jp = new GenderPanel();
		cp = new CharPanel();
		
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("성별", jp);	
		tab.addTab("성격", cp);
		
		add(tab);
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new MainFrame();

	}

}
