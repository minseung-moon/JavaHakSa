package haksa;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class professor extends JFrame{
	
	JPanel nnorthp, northp, orthp, southp;
	JLabel ltitle, lcode, lname, luserId1, luserId2, laddress, lmphone, lhphone, lent_year, ldegree, lmajor_code, lroomno;
	JTextField code, name, userId1, userId2, address, mphone, hphone, ent_year, degree, major_code, roomno, tsearch;
	JComboBox search;
	JButton bsearch, tbsearch, input, edit, delte, exit;
	Font f1, f2;
	DefaultTableModel dtm;
	JTable table;
	JScrollPane scroll;
	
	public professor() {
		this.setTitle("���� ����");
		this.setLayout(null);
		this.setBounds(50, 50, 1000, 660);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		// �ֻ�� Panel
		nnorthp = new JPanel();
		nnorthp.setLayout(null);
		nnorthp.setBounds(0, 0, 1000, 40);
		
		ltitle = new JLabel("���� ����");
		ltitle.setBounds(430, 10, 1000, 25);
		f1 = new Font("����", Font.BOLD, 23);
		f2 = new Font("serif", Font.BOLD, 20);
		
		ltitle.setFont(f1);
		ltitle.setForeground(Color.WHITE);
		
		nnorthp.setBackground(Color.BLACK);
		nnorthp.add(ltitle);
		this.add(nnorthp);
		
		// ��� Panel
		northp = new JPanel();
		northp.setLayout(null);
		northp.setBounds(0, 40, 1000, 200);
		
		lcode = new JLabel("�����ڵ�");
		code = new JTextField();
		lname = new JLabel("�̸�");
		name = new JTextField();
		luserId1 = new JLabel("�ֹι�ȣ");
		luserId2 = new JLabel("-");
		userId1 = new JTextField();
		userId2 = new JTextField();
		
		laddress = new JLabel("�ּ�");
		address = new JTextField();
		
		lmphone = new JLabel("�޴���");
		lhphone = new JLabel("��ȭ");
		mphone = new JTextField();
		hphone = new JTextField();
		
		lent_year = new JLabel("�ӿ�⵵");
		ldegree = new JLabel("����");
		lmajor_code = new JLabel("�а�/����");
		ent_year = new JTextField();
		degree = new JTextField();
		major_code = new JTextField();
		
		lroomno = new JLabel("������");
		roomno = new JTextField();
		
		lcode.setBounds(30, 20, 55, 25);
		code.setBounds(90, 20, 100, 25);
		lname.setBounds(200, 20, 55, 25);
		name.setBounds(260, 20, 100, 25);
		luserId1.setBounds(370, 20, 55, 25);
		userId1.setBounds(430, 20, 100, 25);
		luserId2.setBounds(540, 20, 10, 25);
		userId2.setBounds(560, 20, 100, 25);
		laddress.setBounds(30, 50, 55, 25);
		address.setBounds(80, 50, 580, 25);
		lmphone.setBounds(30, 80, 55, 25);
		mphone.setBounds(90, 80, 200, 25);
		lhphone.setBounds(300, 80, 55, 25);
		hphone.setBounds(360, 80, 200, 25);
		
		lent_year.setBounds(30, 110, 55, 25);
		ent_year.setBounds(90, 110, 100, 25);
		ldegree.setBounds(200, 110, 55, 25);
		degree.setBounds(260, 110, 100, 25);
		lmajor_code.setBounds(370, 110, 55, 25);
		major_code.setBounds(430, 110, 100, 25);
		lroomno.setBounds(30, 140, 55, 25);
		roomno.setBounds(90, 140, 100, 25);
		
		northp.add(lcode);
		northp.add(lname);
		northp.add(luserId1);
		northp.add(luserId2);
		northp.add(code);
		northp.add(name);
		northp.add(userId1);
		northp.add(userId2);
		northp.add(laddress);
		northp.add(address);
		northp.add(lmphone);
		northp.add(mphone);
		northp.add(lhphone);
		northp.add(hphone);
		northp.add(lent_year);
		northp.add(ent_year);
		northp.add(ldegree);
		northp.add(degree);
		northp.add(lmajor_code);
		northp.add(major_code);
		northp.add(lroomno);
		northp.add(roomno);
		this.add(northp);
		
		// �߰� Panel
		orthp = new JPanel();
		orthp.setLayout(null);
		orthp.setBounds(0, 200, 1000, 100);
		orthp.setBackground(Color.BLUE);
		
		search = new JComboBox();
		search.addItem("�˻��� ī�װ��� �����ϼ���");
		search.addItem("code");
		search.addItem("name");
		search.addItem("ent_year");
		search.addItem("major_code");
		
		tsearch = new JTextField();
		bsearch = new JButton("��ȸ");
		tbsearch = new JButton("��ü��ȸ");
		
		search.setBounds(80, 60, 170, 25);
		tsearch.setBounds(270, 60, 140, 25);
		bsearch.setBounds(430, 60, 74, 25);
		tbsearch.setBounds(524, 60, 90, 25);
		
		bsearch.setBackground(Color.GREEN);
		bsearch.setForeground(Color.RED);
		tbsearch.setBackground(Color.GREEN);
		tbsearch.setForeground(Color.RED);
		
		this.add(orthp);
		orthp.add(search);
		orthp.add(tsearch);
		orthp.add(bsearch);
		orthp.add(tbsearch);
		
		String[] columnNames = {"�а��ڵ�", "�а���", "������"};
		Object[][] rowData = {};
		
		dtm = new DefaultTableModel(rowData, columnNames);
		table = new JTable(dtm);
		scroll = new JScrollPane(table);
		scroll.setBounds(0, 300, 1000, 250);
		this.add(scroll);
		
		southp = new JPanel();
		southp.setLayout(null);
		southp.setBounds(5, 500, 1000, 200);
		
		input = new JButton("���");
		edit = new JButton("����");
		delte = new JButton("����");
		exit = new JButton("����");
		
		input.setBounds(80, 70, 142, 40);
		edit.setBounds(226, 70, 142, 40);
		delte.setBounds(372, 70, 142, 40);
		exit.setBounds(518, 70, 138, 40);
		
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
	}
	
	public static void main(String[] args) {
		new professor();
	}
}
