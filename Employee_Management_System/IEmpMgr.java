package com.scsa.jadv.test;

import java.util.ArrayList;

public interface IEmpMgr {
	void load(String filename);
	void save(String filename);
	Employee search(int empNo) throws RecordNotFoundException;
	void add(Employee e) throws DuplicateException;
	void allList();
	ArrayList<Employee> getList();
	void update(Employee e) throws RecordNotFoundException;
	boolean delete(int empNo) throws RecordNotFoundException;
	ArrayList<Employee> search(String name);
	Employee getEmployee(int index);
}
