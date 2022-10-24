package exam02;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MemberTest extends JFrame {

	private JTextField jtf_name;
	private JTextField jtf_phone;
	private JTextArea jta_addr;
	
	public MemberTest() {
		setLayout(new BorderLayout());
		
		JPanel p_center = new JPanel();
		p_center.setLayout(null);
		
		JLabel jlb_name = new JLabel("이름");
		jlb_name.setBounds(10, 10, 100, 40);
		p_center.add(jlb_name);
		
		JLabel jlb_phone = new JLabel("전화번호");
		jlb_phone.setBounds(10, 60, 100, 40);
		p_center.add(jlb_phone);
		
		JLabel jlb_addr = new JLabel("주소");
		jlb_addr.setBounds(10, 110, 100, 40);
		p_center.add(jlb_addr);
		
		jtf_name = new JTextField();
		jtf_phone = new JTextField();
		jta_addr = new JTextArea();
		
		jtf_name.setBounds(120, 10, 200, 30);
		p_center.add(jtf_name);
		
		jtf_phone.setBounds(120, 60, 200, 30);
		p_center.add(jtf_phone);
		
		jta_addr.setBounds(10, 150, 350, 70);
		p_center.add(jta_addr);
		
		JButton btn_save = new JButton("저장");
		JButton btn_search = new JButton("검색");
		JButton btn_exit = new JButton("종료");
		
		JPanel p_south = new  JPanel();
		p_south.setLayout(new GridLayout(1, 3, 5, 0));
		
		p_south.add(btn_save);
		p_south.add(btn_search);
		p_south.add(btn_exit);
		
		add(p_center, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
		
		btn_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = jtf_name.getText();
				String phone = jtf_phone.getText();
				String addr = jta_addr.getText();
				
				String sql = "insert into member values('"+name+"','"+phone+"','"+addr+"')";
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
					String url = "jdbc:oracle:thin:@192.168.25.4:1521:XE";
					String user = "c##sist0307";
					String pwd = "sist0307";
					Connection conn = DriverManager.getConnection(url, user, pwd);
					
					Statement stmt = conn.createStatement();
					
					int re = stmt.executeUpdate(sql); 
					if(re ==1) {
						JOptionPane.showMessageDialog(null,"회원의 정보를 추가하였습니다");
					}
					
					stmt.close();
					conn.close();
					
				} catch (Exception e2) {
					System.out.println("예외발생:"+e2.getMessage());
				}
			}
		});
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		new MemberTest();

	}

}
