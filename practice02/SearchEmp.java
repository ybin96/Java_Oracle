package practice02;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.Border;

public class SearchEmp extends JFrame {
	// 부서명을 입력받아 그 부서의 근무하는 모든 직원들의 이름,급여,주소,입사일을 출력
	// sql = select ename, salary, addr, hiredate from dept d, emp e where d.dno = e.dno and dname = '개발1팀'
	
	JComboBox<String> jcb;
	JTable table;
	Vector<Vector<String>> rowData;
	
	public SearchEmp() {
		Vector<String> v = new Vector<String>();
		EmpDAO dao = new EmpDAO();
		ArrayList<String> list = dao.listDname();
		for(String dname : list) {
			v.add(dname);
		}
		jcb = new JComboBox<String>(v);
		
		Vector<String> title = new Vector<String>();
		title.add("이름");
		title.add("급여");
		title.add("주소");
		title.add("입사일");
			
		rowData = new Vector<Vector<String>>();
		table = new JTable(rowData,title);
		JScrollPane jsp = new JScrollPane(table);
		JButton btn = new JButton("검색");
		
		JPanel jp01 = new JPanel();
		jp01.setLayout(new FlowLayout());
		jp01.add(jcb);
		jp01.add(btn);
		
		add(jp01,BorderLayout.NORTH);
		add(jsp,BorderLayout.CENTER);
		
		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				String dname =(String) jcb.getSelectedItem();
				EmpDAO dao = new EmpDAO();
				ArrayList<EmpVO> list = dao.searchEmp(dname);
				for(EmpVO e2 : list) {
					Vector<String> v = new Vector<String>();
					v.add(e2.getEname());
					v.add(e2.getAddr());
					v.add(e2.getSalary()+"");
					v.add(e2.getHiredate()+"");
					rowData.add(v);
				}
				table.updateUI();	
			}
		});
	}
	public static void main(String[] args) {
		new SearchEmp();
	}

}
