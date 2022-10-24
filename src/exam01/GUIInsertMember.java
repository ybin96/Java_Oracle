package exam01;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIInsertMember extends JFrame {

	public GUIInsertMember() {
		JTextField jtf_name = new JTextField(10);
		JTextField jtf_phone = new JTextField(10);
		JTextField jtf_addr = new JTextField(30);
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		JPanel jp3 = new JPanel();
		JButton btn = new JButton("저장");
		JButton btn2 = new JButton("종료");
		
		setLayout(new FlowLayout());
		jp1.add(new JLabel("이름"));
		jp1.add(jtf_name);
		jp2.add(new JLabel("전화번호"));
		jp2.add(jtf_phone);
		jp3.add(new JLabel("주소"));
		jp3.add(jtf_addr);
				
		add(jp1, new GridLayout(1,1));
		add(jp2, new GridLayout(1,1));
		add(jp3, new GridLayout(1,1));
		add(btn);
		add(btn2);
		
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String sql = "insert into member values('"+jtf_name.getText()+"' ,'"+jtf_phone.getText()+"', '"+jtf_addr.getText()+"')";
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE", "c##sist0307", "sist0307"); // 서버의 주소 , 사용자이름, 암호 
					Statement stmt = conn.createStatement();
					int re = stmt.executeUpdate(sql);
					if(re == 1) {
						System.out.println("회원의 정보를 추가하였습니다"); // 자료하나하나를 레코드라고 한다.
						JOptionPane.showMessageDialog(null, "저장하였습니다");
					}else {
						System.out.println("회원의 정보 추가에 실패하였습니다");
						JOptionPane.showMessageDialog(null, "실패하였습니다");
					}
					stmt.close();
					conn.close();
					
				} catch (Exception e1) {
					System.out.println("예외발생:"+e1.getMessage());
				}
				
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
	}
	public static void main(String[] args) {
		
		new GUIInsertMember();

	}

}
