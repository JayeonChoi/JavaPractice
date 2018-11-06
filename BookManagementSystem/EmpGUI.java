package com.scsa.jadv.test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.scsa.jadv.test.EmpUI.EmpClient;

public class EmpGUI {
	private Frame frame;
	private List list;
	private TextField txtEmpNo;
	private TextField txtName;
	private TextField txtDivision;
	private TextField txtPosition;
	private Label lblStatus;
	private Button btnAdd;
	private Button btnSearch;
	private Button btnUpdate;
	private Button btnDelete;
	private Button btnSave;
	private Button btnUpload;
	private Button btnClear;
	private EmpMgrImpl empmgr = EmpMgrImpl.getInstance();
	String filename = "emp.dat";
	
	public EmpGUI(){
		frame = new Frame("EmpGUI");
		lblStatus = new Label();
		lblStatus.setBackground(Color.LIGHT_GRAY);
		lblStatus.setForeground(Color.BLUE);
		txtEmpNo = new TextField();
		txtName = new TextField();
		txtDivision = new TextField();
		txtPosition = new TextField();
		btnAdd = new Button("Add");
		btnUpdate = new Button("Update");
		btnSearch = new Button("Search");
		btnClear = new Button("Clear");
		btnDelete = new Button("Delete");
		btnSave = new Button("SaveTo");
		btnUpload = new Button("Upload");
		list = new List();

		Panel inputs = new Panel();
		Panel inputLables = new Panel();
		Panel inputFields = new Panel();
		Panel inputBtns = new Panel();
		inputLables.setLayout(new GridLayout(4, 1));
		Label lblEmpNo = new Label("EmpNo:");
		lblEmpNo.setAlignment(Label.CENTER);
		Label lblName = new Label("Name:");
		lblName.setAlignment(Label.CENTER);
		Label lblGrade = new Label("Position:");
		lblGrade.setAlignment(Label.CENTER);
		Label lblDiv = new Label("Division:");
		lblDiv.setAlignment(Label.CENTER);
		inputLables.add(lblEmpNo);
		inputLables.add(lblName);
		inputLables.add(lblGrade);
		inputLables.add(lblDiv);
		inputFields.setLayout(new GridLayout(4, 1));
		inputFields.add(txtEmpNo);
		inputFields.add(txtName);
		inputFields.add(txtPosition);
		inputFields.add(txtDivision);
		inputBtns.setLayout(new GridLayout(1, 4));
		inputBtns.add(btnAdd);
		inputBtns.add(btnUpdate);
		inputBtns.add(btnDelete);
		inputBtns.add(btnSearch);
		inputBtns.add(btnClear);

		inputs.setLayout(new BorderLayout());
		inputs.add(inputLables, BorderLayout.WEST);
		inputs.add(inputFields, BorderLayout.CENTER);
		inputs.add(inputBtns, BorderLayout.SOUTH);

		Panel center = new Panel();
		center.setLayout(new BorderLayout());
		center.add(list);
		center.add(lblStatus, BorderLayout.NORTH);

		Panel pnlBtns = new Panel();
		pnlBtns.setLayout(new GridLayout(1, 4));
		pnlBtns.add(btnSave);
		pnlBtns.add(btnUpload);

		frame.add(inputs, BorderLayout.NORTH);
		frame.add(center);
		frame.add(pnlBtns, BorderLayout.SOUTH);
		frame.setSize(300, 400);
		frame.setVisible(true);
	}
	/** 이벤트를 등록합니다. */
	public void addEvent(){
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee emp = new Employee();	
				emp.setName(txtName.getText());
				emp.setPosition(txtPosition.getText());
				emp.setDivision(txtDivision.getText());
				emp.setEmpNo(Integer.parseInt(txtEmpNo.getText()));
				try {
					empmgr.add(emp);
					list.add(emp.toString());
					lblStatus.setText("등록 완료");
				} catch (DuplicateException e1) {
					lblStatus.setText(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee emp = new Employee();
				emp.setEmpNo(Integer.parseInt(txtEmpNo.getText()));
				emp.setName(txtName.getText());
				emp.setPosition(txtPosition.getText());
				emp.setDivision(txtDivision.getText());
				try {
					empmgr.update(emp);
					lblStatus.setText("수정 완료");
				} catch (RecordNotFoundException e1) {
					lblStatus.setText(e1.getMessage());
					e1.printStackTrace();
				}
				int index = 0;
				for (index=0; index<empmgr.getList().size(); index++) {
					Employee tmp = empmgr.getEmployee(index);
					if (tmp.getEmpNo()==emp.getEmpNo()) break;
				}
				list.replaceItem(emp.toString(), index);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int empno = Integer.parseInt(txtEmpNo.getText());
				Employee tmp;
				try {
					tmp = empmgr.search(empno);
					empmgr.delete(empno);
					list.remove(tmp.toString());
					lblStatus.setText("삭제 완료");
				} catch (RecordNotFoundException e1) {
					lblStatus.setText(e1.getMessage());
					e1.printStackTrace();
				}
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee tmp;
				try {
					tmp = empmgr.search(Integer.parseInt(txtEmpNo.getText()));
					txtName.setText(tmp.getName());
					txtPosition.setText(tmp.getPosition());
					txtDivision.setText(tmp.getDivision());
					lblStatus.setText("검색 완료");
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
					lblStatus.setText("올바른 숫자형식이 아닙니다.");
				} catch (RecordNotFoundException e1) {
					lblStatus.setText(e1.getMessage());
					e1.printStackTrace();
				}
				
			}
		});
		
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblStatus.setText("취소");
				clearField();
			}
		});
		
		btnSave.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				empmgr.save(filename);
				lblStatus.setText("저장 완료");
			}
		});
		
		btnUpload.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new EmpClient().start();
			}
		});

	}
	/** AWT List 컴포넌트에 직원정보를 표시합니다. */
	private void showList(){
		for (Employee e:empmgr.getList()) {
			list.add(e.toString());
		}
	}
	/** 직원정보를 입력하는 TextField의 내용을 지움니다. */
	private void clearField(){
		txtDivision.setText(" ");
		txtName.setText(" ");
		txtPosition.setText(" ");
		txtEmpNo.setText(" ");
	}
	
	public static void main(String[] args){	
		EmpGUI ui = new EmpGUI();
		ui.addEvent();
		ui.empmgr.load(ui.filename);
		ui.showList();
	}	
}
