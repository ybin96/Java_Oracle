package practice03;

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
import javax.swing.JSpinner;
import javax.swing.JTable;

public class SearchEmp extends JFrame {
	JComboBox<String> jcb_dname;
	JTable table;
	Vector<Vector<String>> rowData;

	public SearchEmp() {
		EmpDAO dao = new EmpDAO();
		ArrayList<String> list = dao.listdname();
		Vector<String> v = new Vector<String>();
		for (String dname : list) {
			v.add(dname);
		}

		jcb_dname = new JComboBox<String>(v);

		Vector<String> title = new Vector<String>();
		title.add("이름");
		title.add("주소");
		title.add("급여");
		title.add("입사일");

		rowData = new Vector<Vector<String>>();
		table = new JTable(rowData, title);
		JScrollPane jsp_table = new JScrollPane(table);
		JButton btn = new JButton("검색");

		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		jp.add(jcb_dname);
		jp.add(btn);

		add(jp, BorderLayout.NORTH);
		add(jsp_table, BorderLayout.CENTER);

		setSize(400, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				rowData.clear();
				String dname = (String) jcb_dname.getSelectedItem();
				EmpDAO dao = new EmpDAO();
				ArrayList<EmpVO> list = dao.listdata(dname);
				Vector<String> v = new Vector<String>();
				for (EmpVO e2 : list) {
					v.add(e2.getEname());
					v.add(e2.getAddr());
					v.add(e2.getSalary() + "");
					v.add(e2.getHiredate() + "");
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
