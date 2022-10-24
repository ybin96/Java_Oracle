package exam01;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GUIBook_DB extends JFrame {

	private JTextField jtf_num;
	private JTextField jtf_title;
	private JTextField jtf_price;
	private JTextField jtf_pub;
	JScrollPane jsp;

	Vector<Vector<String>> rowData;
	JTable table;

	public void listbook() { // 목록 보여주기
		rowData.clear();
		try {
			String sql = "select num,title,price,pub from book";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE", "c##sist0307",
					"sist0307");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int num = Integer.parseInt(rs.getString(1));
				String title = rs.getString(2);
				int price = Integer.parseInt(rs.getString(3));
				String pub = rs.getString(4);
				Vector<String> v = new Vector<>();
				v.add(num + "");
				v.add(title);
				v.add(price + "");
				v.add(pub);
				rowData.add(v);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
	}

	public void insertbook() {
		int num = Integer.parseInt(jtf_num.getText());
		String title = jtf_title.getText();
		int price = Integer.parseInt(jtf_price.getText());
		String pub = jtf_pub.getText();

		String sql = "insert into book values(" + num + ",'" + title + "'," + price + ",'" + pub + "')";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE", "c##sist0307",
					"sist0307");
			Statement stmt = conn.createStatement();
			int re = stmt.executeUpdate(sql);

			if (re > 0) {
				JOptionPane.showMessageDialog(null, "등록되었습니다");
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
	}

	public void updateBook() {
		
			int num = Integer.parseInt(jtf_num.getText());
			String title = jtf_title.getText();
			int price = Integer.parseInt(jtf_price.getText());
			String pub = jtf_pub.getText();
			
			String sql = "update book set num="+num+", price="+price+",pub='"+pub+"' where title='"+title+"'";
					
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE", "c##sist0307",
						"sist0307");
				Statement stmt = conn.createStatement();
				int re = stmt.executeUpdate(sql);
				
				if(re > 0) {
					JOptionPane.showMessageDialog(null, "수정되었습니다");
				}
				stmt.close();
				conn.close();
			} catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}		
	}
	
	public void deleteBook() {
		
		String title = jtf_title.getText();
		
		String sql = "delete book where title='"+title+"'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE", "c##sist0307",
					"sist0307");
			Statement stmt = conn.createStatement();
			int re = stmt.executeUpdate(sql);
			
			if(re > 0) {
				JOptionPane.showMessageDialog(null, "삭제되었습니다");
			}
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}	
		
	}

	public GUIBook_DB() {

		Vector<String> list = new Vector<>();
		list.add("도서번호");
		list.add("도서명");
		list.add("가격");
		list.add("출판사명");
		
		rowData = new Vector<Vector<String>>();
		table = new JTable(rowData, list);
		jsp = new JScrollPane(table);
	
		
		jtf_num = new JTextField();
		jtf_title = new JTextField();
		jtf_price = new JTextField();
		jtf_pub = new JTextField();

		setLayout(new BorderLayout());
		JPanel jp01 = new JPanel();
		JButton btn_serach = new JButton("조회");
		JButton btn_insert = new JButton("등록");
		JButton btn_update = new JButton("수정");
		JButton btn_delete = new JButton("삭제");
		jp01.setLayout(new GridLayout(3, 2));
		jp01.add(new JLabel("도서번호"));
		jp01.add(jtf_num);
		jp01.add(new JLabel("도서명"));
		jp01.add(jtf_title);
		jp01.add(new JLabel("가격"));
		jp01.add(jtf_price);
		jp01.add(new JLabel("출판사명"));
		jp01.add(jtf_pub);

		jp01.add(btn_serach);
		jp01.add(btn_insert);
		jp01.add(btn_update);
		jp01.add(btn_delete);

		JPanel jp02 = new JPanel();
		jp02.setLayout(new BorderLayout());
		jp02.add(jsp);

		add(jp01, BorderLayout.NORTH);
		add(jp02, BorderLayout.CENTER);

		setSize(600, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn_serach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listbook();
				jp02.add(jsp);
			}
		});
		btn_insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertbook();
				listbook();
				table.updateUI();

			}
		});
		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateBook();
				listbook();
				table.updateUI();

			}
		});
		btn_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteBook();
				listbook();
				table.updateUI();
			}
		});

	}

	public static void main(String[] args) {
		new GUIBook_DB();
	}

}
