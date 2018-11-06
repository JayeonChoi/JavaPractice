package com.scsa.jadv.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class EmpMgrImpl implements IEmpMgr {
	private static EmpMgrImpl instance = new EmpMgrImpl();
	private ArrayList<Employee> list = new ArrayList<Employee>();
	
	private EmpMgrImpl() {};
	public static EmpMgrImpl getInstance() {
		return instance;
	}
	
	public void load(String filename) {
		ObjectInputStream in = null;
		int cnt = 0;
		Employee emp = null;
		File file = new File(filename);
		
		if (file.exists()) {
			try {
				in = new ObjectInputStream(new FileInputStream(filename));
				cnt = in.readInt();
				for (int i=0; i<cnt; i++) {
					emp = (Employee) in.readObject();
					list.add(emp);
				}
			} 
			catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
	}

	public void save(String filename) {
		ObjectOutputStream out=null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeInt(list.size());
			for (int i=0; i<list.size(); i++) {
				out.writeObject(list.get(i));
			}		
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void allList() {
		for (Employee e:list) {
			System.out.println(e.toString());
		}
	}

	public boolean delete(int empNo) throws RecordNotFoundException {
		Employee e = search(empNo);
		list.remove(e);
		return true;
	}

	public Employee search(int empNo) throws RecordNotFoundException {
		Employee tmp=null;
		for (Employee e:list) {
			if (e.getEmpNo()==empNo) tmp = e;
		}
		if (tmp==null) {
			throw new RecordNotFoundException("정보가 올바르지 않습니다.");
		}
		return tmp;
	}
	
	public void add(Employee e) throws DuplicateException {
		for (Employee tmp:list) {
			if (tmp.equals(e)) throw new DuplicateException("이미 같은 정보가 있습니다.");
		}
		list.add(e);
	}
	
	public ArrayList<Employee> getList() {
		return list;
	}
	
	public void update(Employee e) throws RecordNotFoundException {
		Employee change = search(e.getEmpNo());
		change.setName(e.getName());
		change.setDivision(e.getDivision());
		change.setPosition(e.getPosition());
	}
	
	@Override
	public ArrayList<Employee> search(String name) {
		ArrayList<Employee> tmplist = new ArrayList<Employee>();
		for (Employee tmp:list) {
			if (tmp.getName().equals(name)) tmplist.add(tmp);
		}
		return tmplist;
	}
	
	@Override
	public Employee getEmployee(int index) {
		return list.get(index);
	}

}
