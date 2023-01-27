package kr.or.ddit.board.controller;

import java.util.Scanner;

import kr.or.ddit.board.service.SubService;

public class SubController {
	private SubService service;
	private Scanner sc;
	
	public SubController() {
		sc = new Scanner(System.in);
		service = SubService.getInstance();
	}
	
	public static void main(String[] args) {
		new SubController().Start();
		
	}
	
	public void Start() {
		
		while(true) {
			int choice = displaymenu();
		}
		
	}

	private int displaymenu() {
		
		return 0;
	}

}
