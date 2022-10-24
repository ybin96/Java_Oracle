package exam02;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
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

public class GUIGoods extends JFrame {

	private JTextField jtf_item;
	private JTextField jtf_price;
	private JTextField jtf_qty;

	public GUIGoods() {
		jtf_item = new JTextField();
		jtf_price = new JTextField();
		jtf_qty = new JTextField();
		JButton btn = new JButton("추가");
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

		add(jp1, BorderLayout.CENTER);
		add(jp2, BorderLayout.SOUTH);

		setSize(400, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String item = jtf_item.getText();
				int price = Integer.parseInt(jtf_price.getText());
				int qty = Integer.parseInt(jtf_qty.getText());

				String sql = "insert into goods values('" + item + "'," + price + "," + qty + ")";

				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE",
							"c##sist0307", "sist0307");
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
		});

	}

	public static void main(String[] args) {
		new GUIGoods();

	}

}
