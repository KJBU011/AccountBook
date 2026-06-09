package com.acc.dao;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

import com.acc.dto.AccountBookDto;
import com.acc.file.FileProc;

public class AccountBookDao implements AccountBookDaoInterface {

	// singleton 으로 사용하기 위한 null
	public static AccountBookDao ab = null;

	// 추가 삭제가 용이하도록 List 생성
	public LinkedList<AccountBookDto> accounts = new LinkedList<AccountBookDto>();
	
	// File import
	FileProc file = new FileProc();

	// 스캐너 생성
	Scanner sc = new Scanner(System.in);

	// 기본 생성자 -> singleton 사용을 위해 private
	private AccountBookDao() {
	}
	
	// singleton 의 기본 구조
	public static AccountBookDao getInstance() {
		if (ab == null) {
			ab = new AccountBookDao();
		}
		return ab;
	}

	@Override
	public void search() { // 검색
		// 예외 시 돌아올 수 있도록 무한 루프
		while (true) {
			try { // 예외 처리
				System.out.println("==============[ 가계부 검색 ]================");
				System.out.println("| 1. [내용 검색]                            |");
				System.out.println("| 2. [메모 검색]                            |");
				System.out.println("| 3. [날짜 검색]                            |");
				System.out.println("| 4. [메뉴로 돌아가기]                        |");
				System.out.println("==========================================");
				System.out.print("[입력] >> ");

				int selectSearch = sc.nextInt();
				System.out.println("------------------------------------------");

				// 내용 검색
				if (selectSearch == 1) {
					System.out.println("[검색할 내용을 입력해주십시오]");
					System.out.println("------------------------------------------");
					System.out.print("[입력] >> ");
					String titleInput = sc.next();

					// 함수를 통해 index 추출
					if (searchTitle(titleInput) == -1) {
						System.out.println("------------------------------------------");
						System.out.println("[검색 결과를 찾을 수 없습니다]");
					} else { // 함수를 통해 추출한 index 를 이용한 함수를 통해 출력
						print(searchTitle(titleInput));
					}

				}

				// 메모 검색
				else if (selectSearch == 2) {
					System.out.println("[검색할 메모를 입력해주십시오]");
					System.out.println("------------------------------------------");
					String contentInput = sc.next();

					// 함수를 통해 index 추출
					if (searchContent(contentInput) == -1) {
						System.out.println("------------------------------------------");
						System.out.println("[검색 결과를 찾을 수 없습니다]");
					} else { // 함수를 통해 추출한 index 를 이용한 함수를 통해 출력
						print(searchContent(contentInput));
					}

				}

				else if (selectSearch == 3) {
					System.out.println("[검색할 날짜를 입력해주십시오 < 예시) 06-20 >]");
					System.out.println("------------------------------------------");
					String dateInput = sc.next();

					// 함수를 통해 index 추출
					if (searchDate(dateInput) == -1) {
						System.out.println("------------------------------------------");
						System.out.println("[검색 결과를 찾을 수 없습니다]");
					} else {
						print(searchDate(dateInput));
					}

				}
				
				// 메뉴로 돌아가기 -> 탈출
				else if(selectSearch == 4) {
					System.out.println("------------------------------------------");
					System.out.println("[메뉴로 돌아갑니다]");
					break;
				}

				else { // 선택지 외의 숫자 입력 시
					System.out.println("------------------------------------------");
					System.out.println("[메뉴의 숫자 중에 골라주십시오]");
				}

			} catch (InputMismatchException e) {
				System.out.println("------------------------------------------");
				System.out.println("[숫자로 입력해주십시오]");
				sc.nextLine();
			}
		}

	}

	@Override
	public void allPrint() { // 출력
		// 예외 시 돌아올 수 있도록 무한 루프
		while (true) {
			try { // 예외 처리
				System.out.println("==============[ 가계부 확인 ]================");
				System.out.println("| 1. [최신 순]                          \t |");
				System.out.println("| 2. [과거 순]                          \t |");
				System.out.println("| 3. [금액 높은 순]                      \t |");
				System.out.println("| 4. [금액 낮은 순]                      \t |");
				System.out.println("| 5. [메뉴로 돌아가기]                     \t |");
				System.out.println("==========================================");
				System.out.print("[입력] >> ");

				int selectSearch = sc.nextInt();
				System.out.println("------------------------------------------");

				// 최신 순 
				if (selectSearch == 1) {
					UptoDown(); // 날짜 데이터를 뽑아내 내림차순으로 정렬하는 함수
					// 반복문으로 전체 출력
					for (AccountBookDto dto : accounts) {
						print(dto);
					}

				}

				else if (selectSearch == 2) {
					DowntoUp(); // 날짜 데이터를 뽑아내 오름차순으로 정렬하는 함수
					// 반복문으로 전체 출력
					for (AccountBookDto dto : accounts) {
						print(dto);
					}

				}

				else if (selectSearch == 3) {
					moneyUp();// 금액 데이터를 뽑아내 내림차순으로 정렬하는 함수
					// 반복문으로 전체 출력
					for (AccountBookDto dto : accounts) {
						print(dto);
					}
				}
				
				else if (selectSearch == 4) {
					moneyDown(); // 금액 데이터를 뽑아내 오름차순으로 정렬하는 함수
					// 반복문으로 전체 출력
					for (AccountBookDto dto : accounts) {
						print(dto);
					}
				}
				
				// 메뉴로 돌아가기 -> 탈출
				else if (selectSearch == 5) {
					System.out.println("------------------------------------------");
					System.out.println("[메뉴로 돌아갑니다]");
					break;
				}

				else { // 선택지 외의 숫자 입력 시
					System.out.println("------------------------------------------");
					System.out.println("[메뉴의 숫자 중에 골라주십시오]");
				}
			} catch (InputMismatchException e) {
				System.out.println("------------------------------------------");
				System.out.println("[숫자로 입력해주십시오]");
				sc.nextLine();
			}
		}
	}

	@Override
	public void insert() { // 추가
		// 예외 시 돌아올 수 있도록 무한 루프
		while (true) {
			try { // 예외 처리
				System.out.println("==============[ 가계부 추가 ]================");
				System.out.print("[금액] : ");
				int inputMoney = sc.nextInt();
				
				String inputTime;
				while(true) { // 날짜를 20201122 의 형태로 입력하지 않았을 시 돌아오는 루프
				System.out.print("[날짜] : ");
				String tmpTime = sc.next();
				if(checkNum(tmpTime, 8)) { // 문자열과 글자수 제한을 넣으면 숫자로만 이루어져 있는지 검사하는 함수
					inputTime = addPoint(tmpTime); // 20201122 -> 2020-11-22 로 바꿔주는 함수
					break;
				}
				}
				
				String inputIoKind;
				while (true) { // 잘못 입력 시 재입력을 위한 루프
				System.out.print("[입/출금] : ");
				inputIoKind = sc.next();

				// 입급과 출금이 아니면 돌아가도록 하는 조건문
				if (inputIoKind.equals("입금") || inputIoKind.equals("출금")) {
					break;
				}

				else {
					System.out.println("[\"입금\" 혹은 \"출금\" 으로 적어주십시오]");
				}
				}
				
				System.out.print("[내용] : ");
				String inputTitle = sc.next();
				
				System.out.print("[메모] : ");
				String inputContent = sc.next();
				System.out.println("==========================================");
				
				// 입력받은 내용을 토대로 리스트 생성 -> 날짜를 입력하지 않고 시스템 시간을 사용할 경우 addFirst 를 사용해 자연스럽게 최신순 정렬이 가능하다
				accounts.addFirst(new AccountBookDto(inputIoKind, inputMoney, inputTitle, inputContent, inputTime));
				
				break;	

			} catch (InputMismatchException e) {
				System.out.println("------------------------------------------");
				System.out.println("[숫자로 입력해주십시오]");
				sc.nextLine();
			}
		}

	}

	@Override
	public void delete() { // 삭제
		System.out.println("==============[ 가계부 삭제 ]================");
		System.out.println("[가계부에서 삭제할 항목의 내용과 날짜를 입력해주십시오]");
		System.out.print("[내용] : ");
		String inputTitle = sc.next();
		String inputTime;

		while (true) { // 날짜를 20201122 의 형태로 입력하지 않았을 시 돌아오는 루프
			System.out.print("[날짜] : ");
			String tmpTime = sc.next();
			if (checkNum(tmpTime, 8)) {
				inputTime = addPoint(tmpTime);
				break;
			}
		}

		System.out.println("------------------------------------------");

		// 입력받은 내용과 날짜가 일치하는 지 검사
		boolean find = false;
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getTitle().equals(inputTitle) && accounts.get(i).getAdate().equals(inputTime)) {
				String delTitle = accounts.remove(i).getTitle();
				System.out.println("[" + delTitle + " 항목이 삭제되었습니다]");
				find = true;
				break;
			}
			if(find == false) {
				System.out.println("------------------------------------------");
				System.out.println("[검색 결과를 찾을 수 없습니다]");
			}
		}

	}

	@Override
	public void update() { // 수정
		System.out.println("==============[ 가계부 수정 ]================");
		System.out.println("[가계부에서 수정할 항목의 금액과 날짜를 입력해주십시오]");
		System.out.print("[내용] : ");
		String inputTitle = sc.next();
		String inputTime;

		while (true) { // 날짜를 20201122 의 형태로 입력하지 않았을 시 돌아오는 루프
			System.out.print("[날짜] : ");
			String tmpTime = sc.next();
			if (checkNum(tmpTime, 8)) {
				inputTime = addPoint(tmpTime);
				break;
			}
		}

		System.out.println("------------------------------------------");
		// 검색 결과가 있는지 알려주는 깃발
		boolean find = false;
		
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getTitle().equals(inputTitle) && accounts.get(i).getAdate().equals(inputTime)) {
				find = true; // 깃발 on
				while (true) { // 예외 처리될 경우 및 반복작업을 위한 루프
					try { // 예외 처리
						System.out.println("===============[ 검색 결과 ]=================");
						print(i); // 검색 결과 보여주기 -> 수정 작업에 도움
						System.out.println("==============[ 가계부 수정 ]================");
						System.out.println("| 1. [내용 수정]                            |");
						System.out.println("| 2. [메모 수정]                            |");
						System.out.println("| 3. [날짜 수정]                            |");
						System.out.println("| 4. [메뉴로 돌아가기]                        |");
						System.out.println("==========================================");
						System.out.print("[입력] >> ");

						int selectSearch = sc.nextInt();
						System.out.println("------------------------------------------");

						// 내용 수정
						if (selectSearch == 1) {
							System.out.print("[수정할 내용] > ");
							String updateTitle = sc.next();
							accounts.get(i).setTitle(updateTitle);
							System.out.println("------------------------------------------");
							System.out.println("[수정이 완료되었습니다]");
						}

						// 메모 수정
						else if (selectSearch == 2) {
							System.out.print("[수정할 메모] > ");
							String updateContent = sc.next();
							accounts.get(i).setContent(updateContent);
							System.out.println("------------------------------------------");
							System.out.println("[수정이 완료되었습니다]");
						}

						// 날짜 수정
						else if (selectSearch == 3) {
							String updateTime;
							while (true) { // 날짜를 20201122 의 형태로 입력하지 않았을 시 돌아오는 루프
								System.out.print("[수정할 날짜] > ");
								String tmpTime = sc.next();
								if (checkNum(tmpTime, 8)) {
									updateTime = addPoint(tmpTime);
									break;
								}
							}
							accounts.get(i).setAdate(updateTime);
							System.out.println("------------------------------------------");
							System.out.println("[수정이 완료되었습니다]");
						}

						// 메뉴로 돌아가기 -> 탈출
						else if (selectSearch == 4) {
							System.out.println("------------------------------------------");
							System.out.println("[메뉴로 돌아갑니다]");
							break;
						}
						
						// 선택지에 없는 숫자 입력 시
						else {
							System.out.println("------------------------------------------");
							System.out.println("[메뉴의 숫자 중에 골라주십시오]");
						}
					} catch (InputMismatchException e) {
						System.out.println("------------------------------------------");
						System.out.println("[숫자로 입력해주십시오]");
						sc.nextLine();
					}
				}
			}
		}
		
		// 검색 결과를 찾지 못했을 경우
		if (find == false) {
			System.out.println("------------------------------------------");
			System.out.println("[검색 결과를 찾을 수 없습니다]");
		}

	}
	
	@Override
	public void monthTotal() { // 총합
		while(true) { // 연도와 월을 옳지 않은 양식으로 입력했을 시를 위한 루프
		System.out.println("===============[ 월별 통계 ]=================");
		System.out.println("[총 지출을 확인하고 싶은 연도와 월을 입력해주십시오]");
		System.out.println("------------------------------------------");
		System.out.print("[연도] : ");
		String inputYear = sc.next();
		if(checkNum(inputYear, 4) == false) {
			sc.nextLine(); // 스캐너 비우기
			continue; // 루프문 재시작
		}

		System.out.print("[월] : ");
		String inputMonth = sc.next();
		if(checkNum(inputMonth, 2) == false) {
			sc.nextLine(); // 스캐너 비우기
			continue;// 루프문 재시작
		}
		
		total(inputYear, inputMonth); // 총합을 계산해주는 함수

		break; // 탈출
		}
	}
	
	@Override
	public void sysOff() { // 종료
		file.save(); // 파일 저장
		System.out.println("------------------------------------------");
		System.out.println("[파일을 저장하고 시스템을 종료합니다]");

	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//함수
	//////////////////////////////////////////////////////////////////////////////////////////////////

	// 내용을 통해 검색헤주는 함수 -> index 값 return
	public int searchTitle(String title) {
		// 검색에 실패했을 경우를 위한 -1
		int index = -1;

		// 검사 후 index 값 담기
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getTitle().contains(title)) {
				index = i;
			}
		}
		return index;
	}

	// 메모를 통해 검색헤주는 함수 -> index 값 return
	public int searchContent(String content) {
		// 검색에 실패했을 경우를 위한 -1
		int index = -1;

		// 검사 후 index 값 담기
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getContent().contains(content)) {
				index = i;
			}
		}
		return index;
	}

	// 날짜를 통해 검색헤주는 함수 -> index 값 return
	public int searchDate(String date) {
		// 검색에 실패했을 경우를 위한 -1
		int index = -1;

		// 검사 후 index 값 담기
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAdate().contains(date)) {
				index = i;
			}
		}
		return index;
	}

	// 정보를 출력해주는 함수 -> overload 를 통해 여러 형태로 준비
	public void print(int index) {
		
		System.out.println("-----------------[ "+ accounts.get(index).getIoKind() +" ]-----------------");
		System.out.println("[내용] > [" + accounts.get(index).getTitle() + "]");
		System.out.println("[금액] > " + accounts.get(index).getMoney() + "원");
		System.out.println("[메모] > " + accounts.get(index).getContent());
		System.out.println("[날짜] > " + accounts.get(index).getAdate());
		System.out.println("-----------------------------------------");
	}
	
	public void print(AccountBookDto dto) {
		System.out.println("-----------------[ "+ dto.getIoKind() +" ]-----------------");
		System.out.println("[내용] > [" + dto.getTitle() + "]");
		System.out.println("[금액] > " + dto.getMoney() + "원");
		System.out.println("[메모] > " + dto.getContent());
		System.out.println("[날짜] > " + dto.getAdate());
		System.out.println("-----------------------------------------");
	}
	
	// 파일을 불러오는 함수
	public void loadFile() {
		file.load();
	}
	
	// 문자열과 글자 수 제한을 넣으면 올바른 글자 길이에 전부 숫자로만 이루어져 있는지 검사하는 함수 -> true/false return
	public boolean checkNum(String str, int limit) {
		boolean flag = true;
		
		for (int i = 0; i < str.length(); i++) {
			// 문자열 길이 검사
			if (str.length() == limit) {
				// 숫자 검사
				if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				}
				else {
					System.out.println("[숫자로만 입력해주십시오]");
					flag = false;
					break;
				}
			}
			else {
				System.out.println("[" + limit +"개의 숫자로 입력해주십시오]");
				flag = false;
				break;
			}
		}	
		return flag;
	}
	
	// 20201122 형태의 데이터를 2020 11 22 로 나누어서 return 하는 함수
	public String[] splitDate(String str) {
		String[] split = {str.substring(0, 4), str.substring(4, 6), str.substring(6)};
		return split;
	}
	
	// 20201122 형태의 데이터를 2020-11-22 형태로 return 하는 함수
	public String addPoint(String str) {
		String[] split = splitDate(str);
		String sum = split[0] + "-" + split[1] + "-" + split[2];
		
		return sum;
		
	}
	
	// 2020-11-22 형태의 데이터를 20201122 형태로 return 하는 함수
	public int delPoint(String str) {	
		String[] split = str.split("-");	
		int sum = Integer.parseInt(split[0] + split[1] + split[2]);
		
		return sum;
	}
	
	// 날짜 내림차 순으로 List 를 정렬하는 함수
	public void UptoDown() {
		for (int i = 0; i < accounts.size() - 1; i++) {
			for (int j = i + 1; j < accounts.size(); j++) {
				int iDate = delPoint(accounts.get(i).getAdate());
				int jDate = delPoint(accounts.get(j).getAdate());
				
				if(iDate < jDate) {
					AccountBookDto dto = accounts.get(i);
					accounts.set(i, accounts.get(j));
					accounts.set(j, dto);
				}	
			}
		}
	}
	
	// 날짜 오름차 순으로 List 를 정렬하는 함수
	public void DowntoUp() {
		for (int i = 0; i < accounts.size() - 1; i++) {
			for (int j = i + 1; j < accounts.size(); j++) {
				int iDate = delPoint(accounts.get(i).getAdate());
				int jDate = delPoint(accounts.get(j).getAdate());
				
				if(iDate > jDate) {
					AccountBookDto dto = accounts.get(i);
					accounts.set(i, accounts.get(j));
					accounts.set(j, dto);
				}
			}
		}
	}
	// 금액 내림차 순으로 List 를 정렬하는 함수
	public void moneyUp() {
		for (int i = 0; i < accounts.size() - 1; i++) {
			for (int j = i + 1; j < accounts.size(); j++) {
				if(accounts.get(i).getMoney() < accounts.get(j).getMoney()) {
					AccountBookDto dto = accounts.get(i);
					accounts.set(i, accounts.get(j));
					accounts.set(j, dto);
				}
			}
		}
	}
	
	// 금액 오름차 순으로 List 를 정렬하는 함수
	public void moneyDown() {
		for (int i = 0; i < accounts.size() - 1; i++) {
			for (int j = i + 1; j < accounts.size(); j++) {
				if(accounts.get(i).getMoney() > accounts.get(j).getMoney()) {
					AccountBookDto dto = accounts.get(i);
					accounts.set(i, accounts.get(j));
					accounts.set(j, dto);
				}
			}
		}
	}
	
	// 연도와 월을 입력하면 총 지출을 출력해주는 함수
	public void total(String year, String month) {
		int sum = 0;
		boolean find = false; // 검색 결과가 없을 경우의 깃발
		for (int i = 0; i < accounts.size(); i++) {
			String[] split = accounts.get(i).getAdate().split("-");
			// 입금인지 출금인지에 따라 + / -
			if(split[0].equals(year) && split[1].equals(month)) {
				if(accounts.get(i).getIoKind().equals("입금")) {
				sum = sum + accounts.get(i).getMoney();
				}
				if(accounts.get(i).getIoKind().equals("출금")) {
					sum = sum - accounts.get(i).getMoney();
					}
				find = true;
			}	
		}
		if(find == false) {
			System.out.println("------------------------------------------");
			System.out.println("[검색 결과를 찾을 수 없습니다]");
			return;
		}
		
		System.out.println("===============[ 월별 통계 ]=================");
		if(sum > 0) {
			System.out.println("[총 금액] : +" + sum + "원");
		}
		else {
			System.out.println("[총 금액] : " + sum + "원");
		}
		
		System.out.println("==========================================");
	}

}
