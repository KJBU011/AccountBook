package com.acc.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.acc.dao.AccountBookDao;

public class Main {
	public static void main(String[] args) {

		// 스캐너 생성
		Scanner sc = new Scanner(System.in);

		// Da 불러오기
		AccountBookDao ab = AccountBookDao.getInstance();
		
		// 파일 load
		ab.loadFile();

		// 반복작업이 가능하도록 무한루프
		while (true) {
			try { // 예외 방지
				System.out.println("================[ 가계부 ]==================");
				System.out.println("| 1. [가계부 검색]                        \t |");
				System.out.println("| 2. [가계부 확인]                        \t |");
				System.out.println("| 3. [가계부 추가]                        \t |");
				System.out.println("| 4. [가계부 삭제]                        \t |");
				System.out.println("| 5. [가계부 수정]                        \t |");
				System.out.println("| 6. [가계부 통계]                        \t |");
				System.out.println("| 7. [종료]                             \t |");
				System.out.println("==========================================");
				System.out.print("[입력] >> ");

				int selectMenu = sc.nextInt();
				System.out.println("------------------------------------------");

				switch (selectMenu) {
				case 1: // 검색
					ab.search();
					continue;
				case 2: // 확인
					ab.allPrint();
					continue;
				case 3: // 추가
					ab.insert();
					continue;
				case 4: // 삭제
					ab.delete();
					continue;
				case 5: // 수정
					ab.update();
					continue;
				case 6: // 총합
					ab.monthTotal();
					continue;
				case 7: // 종료
					ab.sysOff();
					break; // 탈출
				default: // 그 외의 숫자 입력 시
					System.out.println("------------------------------------------");
					System.out.println("[메뉴의 숫자 중에 골라주십시오]");
					continue;
				}
				break; // 탈출

			} catch (InputMismatchException e) {
				System.out.println("------------------------------------------");
				System.out.println("[숫자로 입력해주십시오]");
				sc.nextLine(); // 스캐너 비우기
			}
		}
	}
}
