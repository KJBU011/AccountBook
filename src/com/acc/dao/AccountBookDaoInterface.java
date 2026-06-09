package com.acc.dao;

public interface AccountBookDaoInterface {
	
	// 검색
	public void search();
	
	// 출력
	public void allPrint();
	
	// 추가
	public void insert();
	
	// 삭제
	public void delete();
	
	// 수정
	public void update();
	
	// 총합
	public void monthTotal();
	
	// 종료
	public void sysOff();

}
