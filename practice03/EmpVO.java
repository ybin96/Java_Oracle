package practice03;

import java.sql.Date;

public class EmpVO {

	private String ename;
	private int salary;
	private String addr;
	private Date hiredate;
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public EmpVO(String ename, int salary, String addr, Date hiredate) {
		super();
		this.ename = ename;
		this.salary = salary;
		this.addr = addr;
		this.hiredate = hiredate;
	}
	public EmpVO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
