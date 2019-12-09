package com.example.board.vo;

public enum EnumLoginType {
	
	NORMAL(0),
	KAKAO(1);
	
	int flag;
	
	EnumLoginType(int flag) {
	    this.flag = flag;
	}
	
	public int getFlag() {
	    return flag;
	}
}
