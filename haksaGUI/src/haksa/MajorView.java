package haksa;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MajorView extends JFrame {
	JPanel nnorthp, northp, orthp, southp;
	JLabel ltitle, lcode, ldeptment, lmajor;
	JTextField code, deptment, major, tsearch;
	JComboBox search;
	JButton bsearch, tbsearch, input, edit, delte, exit;
	Font f1, f2;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scroll;
	
	public MajorView() {
		this.setTitle("학과/전공 관리");
		this.setLayout(null);
		this.setBounds(50, 50, 600, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		// 최상단 Panel
		nnorthp = new JPanel();
		nnorthp.setLayout(null);
		nnorthp.setBounds(0, 0, 600, 40);
		
		ltitle = new JLabel("학과/전공 관리");
		ltitle.setBounds(230, 10, 600, 25);
		f1 = new Font("돋움", Font.BOLD, 23);
		f2 = new Font("serif", Font.BOLD, 20);
		
		ltitle.setFont(f1);
		ltitle.setForeground(Color.WHITE);
		
		nnorthp.setBackground(Color.BLACK);
		nnorthp.add(ltitle);
		this.add(nnorthp);
		
		// 상단 Panel
		northp = new JPanel();
		northp.setLayout(null);
		northp.setBounds(0, 40, 600, 200);
		lcode = new JLabel("학과코드");
		code = new JTextField();
		
		ldeptment = new JLabel("학과명");
		deptment = new JTextField();
		
		lmajor = new JLabel("전공명");
		major = new JTextField();
		
		lcode.setBounds(30, 20, 55, 25);
		code.setBounds(90, 20, 100, 25);
		ldeptment.setBounds(200, 20, 55, 25);
		deptment.setBounds(260, 20, 100, 25);
		lmajor.setBounds(370, 20, 55, 25);
		major.setBounds(430, 20, 100, 25);
		
		northp.add(lcode);
		northp.add(ldeptment);
		northp.add(lmajor);
		northp.add(code);
		northp.add(deptment);
		northp.add(major);
		this.add(northp);
		
		// 중간 Panel
		orthp = new JPanel();
		orthp.setLayout(null);
		orthp.setBounds(0, 200, 600, 100);
		orthp.setBackground(Color.BLUE);
		
		search = new JComboBox();
		search.addItem("검색할 카테고리를 선택하세요");
		search.addItem("code");
		search.addItem("deptment");
		search.addItem("major");
		
		tsearch = new JTextField();
		bsearch = new JButton("조회");
		tbsearch = new JButton("전체조회");
		
		search.setBounds(40, 60, 170, 25);
		tsearch.setBounds(230, 60, 140, 25);
		bsearch.setBounds(380, 60, 74, 25);
		tbsearch.setBounds(470, 60, 90, 25);
		
		bsearch.setBackground(Color.GREEN);
		bsearch.setForeground(Color.RED);
		tbsearch.setBackground(Color.GREEN);
		tbsearch.setForeground(Color.RED);
		
		this.add(orthp);
		orthp.add(search);
		orthp.add(tsearch);
		orthp.add(bsearch);
		orthp.add(tbsearch);
		
		String[] columnNames = {"학과코드", "학과명", "전공명"};
		Object[][] rowData = {};
		
		dtm = new DefaultTableModel(rowData, columnNames);
		table = new JTable(dtm);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 300, 600, 250);
		this.add(scroll);
		
		southp = new JPanel();
		southp.setLayout(null);
		southp.setBounds(5, 500, 575, 200);
		
		input = new JButton("등록");
		edit = new JButton("수정");
		delte = new JButton("삭제");
		exit = new JButton("종료");
		
		input.setBounds(0, 70, 142, 40);
		edit.setBounds(146, 70, 142, 40);
		delte.setBounds(292, 70, 142, 40);
		exit.setBounds(438, 70, 138, 40);
		
		input.setFont(f2);
		input.setBackground(Color.BLUE);
		input.setForeground(Color.YELLOW);
		edit.setFont(f2);
		edit.setBackground(Color.YELLOW);
		edit.setForeground(Color.BLACK);
		delte.setFont(f2);
		delte.setBackground(Color.GREEN);
		delte.setForeground(Color.RED);
		exit.setFont(f2);
		exit.setBackground(Color.MAGENTA);
		exit.setForeground(Color.WHITE);
		
		southp.add(input);
		southp.add(edit);
		southp.add(delte);
		southp.add(exit);
		this.add(southp);
		
		this.setVisible(true);
		// action 이벤트
		new MajorBtnAction(this);
	}
	
	public void close() {
		System.exit(0);
	}
	
	public void BtnAddAction(ActionListener listener) {
		input.addActionListener(listener);
		edit.addActionListener(listener);
		delte.addActionListener(listener);
		exit.addActionListener(listener);
		bsearch.addActionListener(listener);
		tbsearch.addActionListener(listener);
	}
	public void MouseAddAction(MouseAdapter adapter) {
		this.addMouseListener(adapter);
		table.addMouseListener(adapter);
	}
}
