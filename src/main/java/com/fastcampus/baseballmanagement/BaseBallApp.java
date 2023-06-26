package com.fastcampus.baseballmanagement;

import com.fastcampus.baseballmanagement.core.DispatcherServlet;

import java.util.Scanner;

public class BaseBallApp {

    public static void main(String[] args) throws Exception {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        String pkg = "com.fastcampus.baseballmanagement";
        Scanner sc = new Scanner(System.in);
        String uri = sc.nextLine();
        dispatcherServlet.parseUri(pkg, uri);
    }
}
