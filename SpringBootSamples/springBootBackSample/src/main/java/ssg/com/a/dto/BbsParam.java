package ssg.com.a.dto;

import java.io.Serializable;

public class BbsParam implements Serializable{
	
	private String choice;	// 카테고리(제목/내용/작성자)
	private String search;	// 검색어
	private int pageNumber;
	private int seq;

	public BbsParam() {
	}

	public BbsParam(int seq, int pageNumber) {
		super();
		this.seq = seq;
		this.pageNumber = pageNumber;
	}
	
	public BbsParam(String choice, String search, int pageNumber) {
		super();
		this.choice = choice;
		this.search = search;
		this.pageNumber = pageNumber;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	@Override
	public String toString() {
		return "BbsParam [choice=" + choice + ", search=" + search + ", pageNumber=" + pageNumber + "]";
	}	
	
}
