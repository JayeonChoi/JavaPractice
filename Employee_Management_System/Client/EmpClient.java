package com.scsa.jadv.test.EmpUI;
import java.io.PrintWriter;
import java.net.Socket;

import com.scsa.jadv.test.EmpMgrImpl;
import com.scsa.jadv.test.Employee;

public class EmpClient extends Thread {
	Socket s = null;
	PrintWriter bw = null;
	
	@Override
	public void run() {
		try {
			s = new Socket("127.0.0.1", 8888);
			bw = new PrintWriter(s.getOutputStream(), true);
			for (Employee e: EmpMgrImpl.getInstance().getList()) {
				bw.println(e.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (s!=null) s.close();
				if (bw != null) bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
