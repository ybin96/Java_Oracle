package exam01;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextField;

public class GUIGoodsTest_DB_Update extends JFrame {

	private JTextField jtf_item;
	private JTextField jtf_price;
	private JTextField jtf_qty;
	Vector<Vector<String>> rowData;
	JTable table;
	
	public void deleteGoods() { // 삭제
		String item = jtf_item.getText();
		String sql = "delete goods where item='"+item+"'";
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
	
	public void updateGoods() { // 수정
		
		String item = jtf_item.getText();
		int price = Integer.parseInt(jtf_price.getText());
		int qty = Integer.parseInt(jtf_qty.getText());
		
		String sql = "update goods set price="+price+",qty="+qty+" where item='"+item+"'";
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
			
		} catch (Exception e2) {
			System.out.println("예외처리:"+e2.getMessage());
		}
	}

	public void insertGoods() { // 상품 등록

		String item = jtf_item.getText();
		int price = Integer.parseInt(jtf_price.getText());
		int qty = Integer.parseInt(jtf_qty.getText());

		String sql = "insert into goods values('" + item + "'," + price + "," + qty + ")";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE", "c##sist0307",
					"sist0307");
			Statement stmt = conn.createStatement();
			int re = stmt.executeUpdate(sql); // 정수 re는 성공적으로 실행한 건수를 반환
			if (re == 1) {
				JOptionPane.showMessageDialog(null, "상품을 등록하였습니다.");
			}
			stmt.close();
			conn.close();

		} catch (Exception e2) {
			System.out.println("예외발생:" + e2.getMessage());
		}

	}

	public void listGoods() { // 리스트 보여주기
		rowData.clear();

		try {
			String sql = "select item,price,qty from goods";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE", "c##sist0307",
					"sist0307");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String item = rs.getString(1);
				String price = rs.getString(2);
				String qty = rs.getString(3);
				Vector<String> v = new Vector<>();
				v.add(item);
				v.add(price + "");
				v.add(qty + "");
				rowData.add(v);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
	}

	public GUIGoodsTest_DB_Update() {

		Vector<String> colNames = new Vector<String>();
		colNames.add("상품명");
		colNames.add("가격");
		colNames.add("수량");

		rowData = new Vector<Vector<String>>();

		listGoods();

//		Vector<String> v1 = new Vector<String>();
//		v1.add("색종이");
//		v1.add(100 + "");
//		v1.add(10 + "");
//		Vector<String> v2 = new Vector<String>();
//		v2.add("딱풀");
//		v2.add(700 + "");
//		v2.add(5 + "");
//
//		rowData.add(v1);
//		rowData.add(v2);

		table = new JTable(rowData, colNames);
		JScrollPane jsp = new JScrollPane(table);

		jtf_item = new JTextField();
		jtf_price = new JTextField();
		jtf_qty = new JTextField();
		JButton btn = new JButton("등록");
		JButton btn2 = new JButton("수정");
		JButton btn3 = new JButton("삭제");
		setLayout(new BorderLayout());
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		jp1.setLayout(new GridLayout(3, 2, 10, 10));
		jp1.add(new JLabel("상품명"));
		jp1.add(jtf_item);
		jp1.add(new JLabel("단가"));
		jp1.add(jtf_price);
		jp1.add(new JLabel("수량"));
		jp1.add(jtf_qty);

		jp2.add(btn);
		jp2.add(btn2);
		jp2.add(btn3);

		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());

		p.add(jp1, BorderLayout.CENTER);
		p.add(jp2, BorderLayout.SOUTH);

		setLayout(new GridLayout(2, 1));
		add(jsp);
		add(p);

		setSize(400, 500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertGoods();
				listGoods();
				table.updateUI(); // 벡터에 변경된 내용을 갖고 테이블에 적용해주세요
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateGoods();
				listGoods();
				table.updateUI(); 
				
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteGoods();
				listGoods();
				table.updateUI(); 
			}
		});

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow(); // table에 있는 선택한 index를 가져온다. // 첫번째를 누르면 row에 0이라고 담긴다
				Vector<String> v = rowData.get(row);
				System.out.println(v);
				String item = v.get(0);
				int price = Integer.parseInt(v.get(1));
				int qty = Integer.parseInt(v.get(2));

				jtf_item.setText(item);
				jtf_price.setText(price + "");
				jtf_qty.setText(qty + "");
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}

	public static void main(String[] args) {
		new GUIGoodsTest_DB_Update();

	}

}
