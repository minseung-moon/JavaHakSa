package haksa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class MajorBtnAction extends MouseAdapter implements ActionListener {
	private MajorView view;
	private MajorDAO dao;
	private String[] majorValue = new String[3];
	private int rowIndex = -1;
	private JTable table;
	ArrayList<MajorDTO> selectAll;
	
	public MajorBtnAction(MajorView view) {
		super();
		this.view = view;
		view.BtnAddAction(this);
		view.MouseAddAction(this);
		table = view.table;
		dao = new MajorDAO();
		initSelect();
	}
	
	private void initSelect() {
		// 테이블에 기본 데이터 출력
		view.dtm.setRowCount(0);
		selectAll = dao.selectMajor();
		for(MajorDTO select : selectAll) {
			String[] major = select.toString().split(",");
			view.dtm.addRow(major);
		}
	}
	
	public void clear() {
		view.code.setText("");
		view.code.setEnabled(true);
		view.deptment.setText("");
		view.major.setText("");
		rowIndex = -1;
	}
	
	private boolean checkInput() {
		boolean check = false;
		if (view.code.getText() == null || view.code.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "코드를 입력해주세요");
			view.code.requestFocus();
		} else if (view.deptment.getText() == null
				|| view.deptment.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "학과를 입력해주세요");
			view.deptment.requestFocus();
		} else if (view.major.getText() == null
				|| view.major.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "전공을 입력해주세요");
			view.major.requestFocus();
		} else {
			check = true;
			majorValue[0] = view.code.getText();
			majorValue[1] = view.deptment.getText();
			majorValue[2] = view.major.getText();
		}
		return check;
	}
	
	private void selectCategory(int num, String searchStr) {
		view.dtm.setRowCount(0);
		String[] major = null;
		for(MajorDTO select : selectAll) {
			if(num == 1) {
				if(select.getCode().equals(searchStr)){
					major  =new String[] {select.getCode(), select.getDepartment(), select.getMajor()};	
				}
			}
			if(num == 2){
				if(select.getDepartment().equals(searchStr)){
					major  =new String[] {select.getCode(), select.getDepartment(), select.getMajor()};
				}
			}
			if(num == 3) {
				if(select.getMajor().equals(searchStr)){
					major  =new String[] {select.getCode(), select.getDepartment(), select.getMajor()};
				}
			}
		}
		view.dtm.addRow(major);
	}
	
	private void updateTable(int rowIndex) {
		table.setValueAt(majorValue[0], rowIndex, 0);
		table.setValueAt(majorValue[1], rowIndex, 1);
		table.setValueAt(majorValue[2], rowIndex, 2);
		JOptionPane.showMessageDialog(null, "수정 되었습니다");
	}

	public void mouseClicked(MouseEvent e) {
		 rowIndex = table.getSelectedRow();
		if (rowIndex == -1 || e.getSource() != table) {
			clear();
			return;
		}
		majorValue[0] = table.getValueAt(rowIndex, 0).toString();
		majorValue[1] = table.getValueAt(rowIndex, 1).toString();
		majorValue[2] = table.getValueAt(rowIndex, 2).toString();
		view.code.setText(majorValue[0]);
		view.code.setEnabled(false);
		view.deptment.setText(majorValue[1]);
		view.major.setText(majorValue[2]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == view.input) {
			if (!checkInput())
				return;

			int confirm = JOptionPane.showConfirmDialog(null, "정말 "
					+ majorValue[0] + "를 추가하시겠습니까?", "학과추가",
					JOptionPane.OK_CANCEL_OPTION);
			if (confirm != 0) {
				JOptionPane.showMessageDialog(null, "학과추가를 취소하셨습니다");
				return;
			}
			if (dao.inserMajor(majorValue)) {
				JOptionPane.showMessageDialog(null, "저장에 성공하셨습니다");
				view.dtm.addRow(majorValue);
			} else
				JOptionPane.showMessageDialog(null, "저장에 실패하셨습니다!");
		} else if (obj == view.edit) {
			if (!checkInput())
				return;
			int check = JOptionPane.showConfirmDialog(null, majorValue[0]
					+ "를 수정 하시겠습니까?", "수정 여부", JOptionPane.YES_NO_OPTION);

			if (check == 1) {
				JOptionPane.showMessageDialog(null, "취소 되었습니다");
				return;
			} else {
				if (dao.updateMajor(majorValue) && rowIndex != -1) {
					updateTable(rowIndex);
				} else if(dao.updateMajor(majorValue) && rowIndex == -1){
					for (int i = 0; i < view.table.getRowCount(); i++) {
						if(table.getValueAt(i, 0).toString().equals(majorValue[0])){
							rowIndex = i;
							break;
						}
					}
					updateTable(rowIndex);
				} else JOptionPane.showMessageDialog(null, "데이터베이스 수정에 실패하였습니다!");
			}
		} else if (obj == view.delte) {
			rowIndex = table.getSelectedRow(); 
			if( rowIndex == -1) {
				JOptionPane.showMessageDialog(null, "삭제할 행을 선택해주세요!");
				return;
			}
			else {
				String code = table.getValueAt(rowIndex, 0).toString();
				int check = JOptionPane.showConfirmDialog(null, code+"를 정말 삭제하시겠습니까?", "삭제 여부", JOptionPane.YES_NO_OPTION);
				if(check == 0) {
					if(dao.deleteMajor(code)) {
						view.dtm.removeRow(rowIndex);
						JOptionPane.showMessageDialog(null, "삭제 되었습니다");
					} else {
						JOptionPane.showMessageDialog(null, "데이터베이스에 삭제되지 못했습니다!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "취소 되었습니다");
				}
			}
		} else if (obj == view.exit) {
			dao.close();
			view.close();
		} else if (obj == view.bsearch) {
			// 0 ~ 4
			int menuNum = view.search.getSelectedIndex();
			String searchStr = view.tsearch.getText();
			if(searchStr == null || searchStr.equals("")){
				JOptionPane.showMessageDialog(null, "검색항목을 입력해주세요!");
				view.tsearch.requestFocus();
				return;
			}
			if(menuNum == 1) {
				selectCategory(1, searchStr);
			} else if (menuNum == 2) {
				selectCategory(2, searchStr);
			} else if (menuNum == 3) {
				selectCategory(3, searchStr);
			} else {
				JOptionPane.showMessageDialog(null, "메뉴를 선택하세요!");
			}
			
		} else if (obj == view.tbsearch) {
			initSelect();
		}
		
		clear();
	}

}
