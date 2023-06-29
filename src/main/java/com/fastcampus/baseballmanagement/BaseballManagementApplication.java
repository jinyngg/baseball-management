package com.fastcampus.baseballmanagement;

import com.fastcampus.baseballmanagement.core.MyDispatcherServlet;

import java.util.Scanner;

public class BaseballManagementApplication {

	private static final MyDispatcherServlet myDispatcherServlet = MyDispatcherServlet.getInstance();

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String uri = sc.nextLine();
			myDispatcherServlet.parseUri(uri);
		}
	}

}
