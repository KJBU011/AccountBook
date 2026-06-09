package com.acc.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.acc.dao.AccountBookDao;
import com.acc.dto.AccountBookDto;

public class FileProc {
	// 파일 생성
	File AccountBook = new File("C:/tmp/AccountBook.txt");
	
	// 저장
	public void save() {
		try { // 예외 처리
			AccountBookDao ab = AccountBookDao.getInstance(); // singleton 호출
			// write 준비
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(AccountBook)));
			
			// 반복문을 통해 write
			for (AccountBookDto dto : ab.accounts) {
				String title = dto.getTitle();
				String content = dto.getContent();
				int money = dto.getMoney();
				String ioKind = dto.getIoKind();
				String date = dto.getAdate();
				
				// read 시 데이터 분할을 위해 token 삽입
				pw.println(ioKind + "@" + money + "@" + title + "@" + content
						+ "@" + date);
			}
			// 파일 닫기
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	// 불러오기
	public void load() {
		try { // 예외처리
			AccountBookDao ab = AccountBookDao.getInstance(); // singleton 호출
			// read 준비
			BufferedReader bw = new BufferedReader(new FileReader(AccountBook));
			
			// read 시 조건문에 진입하기 위한 ""
			String str = "";

			// 반복문을 통해 token 으로 데이터를 나누고 새 리스트 생성
			while ((str = bw.readLine()) != null) {
				String[] split = str.split("@");
				ab.accounts.add(new AccountBookDto(split[0], Integer.parseInt(split[1]), split[2], split[3], split[4]));
			}

			// 파일 닫기
			bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
