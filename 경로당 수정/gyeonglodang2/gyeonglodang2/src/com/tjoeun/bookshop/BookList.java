package com.tjoeun.bookshop;

import java.util.ArrayList;

public class BookList {
//	도서정보를 기억할 ArrayList를 만든다.
	ArrayList<BookVO> bookList = new ArrayList<BookVO>();
	
//	생성자를 선언하지 않으면 자바 컴파일러가 아무런 일도 하지 않는 기본 생성자를 만들어 준다.
	public BookList() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<BookVO> getBookList() {
		return bookList;
	}

	public void setBookList(ArrayList<BookVO> bookList) {
		this.bookList = bookList;
	}

	@Override
	public String toString() {
		return "BookList [bookList=" + bookList + "]";
	}
	
//	BookList 클래스의 bookList ArrayList에 매개변수로 넘겨받은
//	도서정보를 저장하는 메소드
	public void addBook(BookVO book) {
		bookList.add(book);
	}
	

	

}
