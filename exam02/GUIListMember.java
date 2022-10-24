package exam02;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class GUIListMember extends JFrame {
	public JTextArea jta;
	
	public GUIListMember() {
		setTitle("회원정보 출력");
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);		
		JButton btn = new JButton("회원정보 출력");
		setLayout(new BorderLayout());
		
		add(jsp, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String sql = "select name,phone,addr from member";
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE","c##sist0307","sist0307");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						String name = rs.getString(1);
						String phone = rs.getString(2);
						String addr = rs.getString(3);
						String row = name+"\t"+phone+"\t"+addr+"\n";
						jta.append(row);
						
					}
				
					rs.close();
					stmt.close();
					conn.close();
					
				} catch (Exception e2) {
					System.out.println("예외처리:"+e2.getMessage());
				}
				
			}
		});
	}
	
	public static void main(String[] args) {
		new GUIListMember();
	}

}
