package exam03;

import java.sql.Date;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = dao.searchEmp("개발1팀");
		for(EmpVO e : list) {
			String ename = e.getEname();
			int salary = e.getSalary();
			String addr = e.getAddr();
			Date hiredate = e.getHiredate();
			
			String row = ename+","+salary+","+addr+","+hiredate;
			System.out.println(row);
		}
		
		System.out.println("==================================");
		
		ArrayList<String> list2 =dao.listDname();
		for(String dname : list2) {
			System.out.println(dname);
		}
	}

}
