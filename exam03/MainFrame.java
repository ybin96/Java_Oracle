package exam03;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	SearchEmpCombo searchFrame;

	public MainFrame() {
		searchFrame = new SearchEmpCombo();
		
		setLayout(null);
		JButton btn = new JButton("부서별 검색");
		btn.setBounds(100, 100, 100, 100);
		add(btn);
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				searchFrame.setVisible(true);
				
			}
		});
	}
	public static void main(String[] args) {
		new MainFrame();
	}

}
