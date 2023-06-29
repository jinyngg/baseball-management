package com.fastcampus.baseballmanagement;

import com.fastcampus.baseballmanagement.core.MyDispatcherServlet;

import java.util.Scanner;

public class BaseballManagementApplication {

	private static final MyDispatcherServlet myDispatcherServlet = new MyDispatcherServlet();

	public static void main(String[] args) throws Exception {
		myDispatcherServlet.configurationInit();

		Scanner sc = new Scanner(System.in);
		String uri = sc.nextLine();
		myDispatcherServlet.parseUri(uri);
	}

}
