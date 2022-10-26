package practice03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class EmpDAO {
	
	public ArrayList<String> listdname(){
		String sql = "select dname from dept";
		ArrayList<String> list = new ArrayList<String>();
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
			System.out.println("예외발생:"+e.getMessage());
		}
		return list;
	}
	
	public ArrayList<EmpVO> listdata(String dname){
		String sql = "select ename, salary, addr, hiredate from dept d, emp e where d.dno = e.dno and dname = '"+dname+"'";
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.4:1521:XE","c##madang","madang");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(new EmpVO(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getDate(4)));
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
