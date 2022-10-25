package exam01;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SearchEmp extends JFrame {
	JTextField jtf_dname;
	JTable table;
	Vector<Vector<String>> rowData;
	// 부서명을 입력받아 그 부서의 근무하는 모든 직원들의 이름,급여,주소,입사일을 출력
	
	public SearchEmp() {
		jtf_dname = new  JTextField(10);
		Vector<String> colNames = new Vector<String>();
		colNames.add("이름");
		colNames.add("급여");
		colNames.add("주소");
		colNames.add("입사일");
		
		rowData = new Vector<Vector<String>>();
		table = new JTable(rowData,colNames);
		JScrollPane jsp = new JScrollPane(table);
		JButton btn = new JButton("검색");
		
		JPanel p = new JPanel();
		p.add(new JLabel("부서멍"));
		p.add(jtf_dname);
		p.add(btn);
		
		add(p,BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);
		
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				String dname = jtf_dname.getText();
				EmpDAO dao = new EmpDAO();
				ArrayList<EmpVO> list = dao.searchEmp(dname);
				for(EmpVO e2 : list) {
					Vector<String> v = new Vector<String>();
					v.add(e2.getEname());
					v.add(e2.getSalary()+"");
					v.add(e2.getAddr());
					v.add(e2.getHiredate()+"");
					rowData.add(v);
				}
				table.updateUI();
			}
		});
		
	}
	
	public void listDept() {
		
	}
	
	public static void main(String[] args) {
		new SearchEmp();
	}

}
