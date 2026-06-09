package com.acc.dto;

import java.util.Calendar;

public class AccountBookDto {
	
	private String ioKind;
	private int money;
	private String title;
	private String content;
	private String adate;
	
	public AccountBookDto() {
	}

	// 날짜를 실시간 입력 시 사용
//	public AccountBookDto(String ioKind, int money, String title, String content) {
//		
//		Calendar cal = Calendar.getInstance();
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH);
//		int day = cal.get(Calendar.DATE);
//		
//		this.ioKind = ioKind;
//		this.money = money;
//		this.title = title;
//		this.content = content;
//		this.adate = year + "." + (month + 1) + "." + day;
//	}
	
	public AccountBookDto(String ioKind, int money, String title, String content, String adate) {
		this.ioKind = ioKind;
		this.money = money;
		this.title = title;
		this.content = content;
		this.adate = adate;
	}

	public String getIoKind() {
		return ioKind;
	}

	public void setIoKind(String ioKind) {
		this.ioKind = ioKind;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAdate() {
		return adate;
	}

	public void setAdate(String adate) {
		this.adate = adate;
	}
	
	
	

}
