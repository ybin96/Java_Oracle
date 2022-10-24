package exam01;

import java.awt.Taskbar.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class InsertMember {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name,phone,addr;
		System.out.println("이름을 입력하세요");
		name = sc.next();
		System.out.println("전화번호를 입력하세요");
		phone = sc.next();
		System.out.println("주소를 입력하세요");
		addr = sc.next();
		
		// System.out.println("이름:"+name+",전화:"+phone+",주소:"+addr);
		
		// 데이터베이스 연결하기(예외처리는 필수)
		
		String sql = "insert into member values('"+name+"' ,'"+phone+"', '"+addr+"')";
		try {
			
			// 1. jdbc (java database connection)드라이버를 메모리로 로드한다. 암기할것
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. db Server에 연결한다.
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE", "c##sist0307", "sist0307"); // 서버의 주소 , 사용자이름, 암호 
			
			// 3. SQL명령어를 실행시키기 위한 Statement객체를 생성한다. 생성할때 SQL로 성생한다
			Statement stmt = conn.createStatement();
			
			//4. SQL명령을 실행한다.
			int re = stmt.executeUpdate(sql);
			
			if(re == 1) {
				System.out.println("회원의 정보를 추가하였습니다"); // 자료하나하나를 레코드라고 한다.
			}else {
				System.out.println("회원의 정보 추가에 실패하였습니다");
			}
			
			// 5. 사용했던 자원들을 닫아 준다.
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
}

