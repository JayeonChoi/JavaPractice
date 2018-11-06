package com.scsa.jadv.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class EmpServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		BufferedReader in = null;
		Socket s = null;

		System.out.println("ServerSocket ok. port=8888");
		while (true) {
			try {
				System.out.println("server ready...");
				
				s = ss.accept();
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String str = null;
				while ((str = in.readLine()) != null) {
					System.out.println(str);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("receive ok");
				try {
					if (in != null)
						in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
