package exam02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListMember {

	public static void main(String[] args) {
		String sql = "select name,phone,addr from member";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE","c##sist0307","sist0307");
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs= stmt.executeQuery(sql);  // rs에는 db에 자료가 담겨져있다
			
			// next()는 cursor를 다음으로 옮겨주는 메소드. 다음 레코드가 없으면 false 반환.
			// 데이터가 있는만큼 반복실행한다는 뜻
			while(rs.next()) {   
				String name = rs.getString(1);	// 이름을 가져온다
				String phone = rs.getString(2); // 전화번호를 가져온다
				String addr = rs.getString(3);	// 주소를 가져온다	==> 다음 레코드가 없으면 while문을 빠져나온다
				System.out.println(name+","+phone+","+addr);
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
}