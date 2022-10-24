package exam02;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.management.monitor.MonitorSettingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUIListGoods extends JFrame {

	private JTextArea jta;
	
	public GUIListGoods() {
		setTitle("상품 조회하기");
		jta = new JTextArea();
		JScrollPane jsp = new JScrollPane(jta);
		setLayout(new BorderLayout());
		JButton btn = new JButton("모든 상품 조회");
		
		add(jsp, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql = "select item,price,qty from goods";
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE","c##sist0307","sist0307");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()) {
						String item = rs.getString(1);
						int price = rs.getInt(2);
						int qty = rs.getInt(3);
						
						jta.append(item+"\t"+price+"\t"+qty+"\n");
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
		new GUIListGoods();
		
	}

}
