package exam03;

import java.lang.reflect.Array;
import java.nio.file.attribute.AclEntry;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class EmpDAO {
	
	// sql = select dname from dept
	// 모든 부서이름을 검색하여 봔한하는 메소드를 정의
	
	public ArrayList<String> listDname() {
		ArrayList<String> list = new ArrayList<String>();
		String sql = "select dname from dept";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE","c##madang","madang");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외처리:"+e.getMessage());
		}
		return list;
	}
	

	// sql = select ename, salary, addr, hiredate from dept d, emp e where d.dno = e.dno and dname = '개발1팀'
	// 부서명을 매개변수로 전달받아 위의 sql을 실행한 결과를 ArrayList에 담아 반환하는 메소드를 정의
	// ArrayList에 담기는 자료형은 EmpVO로 한다
	
	public ArrayList<EmpVO> searchEmp(String dname) {
		String sql = "select ename, salary, addr, hiredate from dept d, emp e where d.dno = e.dno and dname ='"+dname+"'";
		
		ArrayList<EmpVO> list = new ArrayList<>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE","c##madang","madang");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new EmpVO(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getDate(4)));
//				String ename = rs.getString(1);
//				int salary = rs.getInt(2);
//				String addr = rs.getString(3);
//				Date hiredate = rs.getDate(4);
//				
//				EmpVO vo = new EmpVO();
//				vo.setEname(ename);
//				vo.setAddr(addr);
//				vo.setSalary(salary);
//				vo.setHiredate(hiredate);
//				list.add(vo);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		return list;
	}
	
	
}
