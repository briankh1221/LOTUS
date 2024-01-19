package com.tjoeun.bookshop;

import java.util.Date;

public class BookShopMain {
	public static void main(String[] args) {
		
//		클래스이름 객체(변수)이름 = new 생성자();
//		객체를 생성하는 것(인스턴스 생성)은 생성자가 하는 것이 아니고, new 연산자가 생성하는 것
//		생성자는 필드의 값을 초기화하는데 사용 하는 것이다.
		
		BookVO vo = new BookVO();
//		System.out.println(vo.title); 
//		=> vo 클래스의 필드들이 private으로 선언 => 외부 클래스에 접근이 불가능하다.
//		System.out.println(vo);
//		
//		BookVO vo2 = new BookVO();
//		System.out.println(vo2);
		
//		클래스로 만든 객체를 출력하면 자동으로 toString() 메소드가 실행된다.
//		System.out.println(vo.toString());
		
//		도서정보를 만든다.
//		출판일은 날짜 데이터를 만들어 BookVO 클래스의 writeDate 필드에 넣어주면
//		다른 곳에서 사용할 일이 없다.
//		이럴 경우 익명으로 객체를 만들어 사용하면 편리하다.
		BookVO book1 = new BookVO("java", "둘리", "출판사", 
				new Date(2023, 7, 1), 15000);
		System.out.println("book1 " + book1);
		
//		클래스에서 private으로 선언된 변수는 클래스 외부에서 접근할 수 없으므로
//		에러가 발생된다.
//		System.out.println(book1.title); // 에러
//		private 필드에 접근하기 위해 getters & setters 메소드를 만들어 사용한다.
		System.out.println(book1.getTitle());
		book1.setTitle("자바");
		System.out.println("book1: " + book1);
		
//		Date date = new Date(2002, 12, 31);
//		BookVO book2 = new BookVO("커피", "고길동", "tj출판사", date, 10000);
//		System.out.println(book2);
		BookVO book2 = new BookVO("Java", "둘리", "tj출판사"
				, new Date(2022, 12, 31), 10000);
		BookVO book3 = new BookVO("Java", "고길동", "tj출판사"
				, new Date(2020, 2, 20), 10000);
		BookVO book4 = new BookVO("Java", "마이콜", "tj출판사"
				, new Date(2023, 7, 19), 10000);
		BookVO book5 = new BookVO("Java", "도우너", "tj출판사"
				, new Date(2022, 12, 31), 10000);
		BookVO book6 = new BookVO("Java", "또치", "tj출판사"
				, new Date(2022, 12, 31), 10000);
		BookVO book7 = new BookVO("Java", "또치", "tj출판사"
				, new Date(2022, 12, 31), 10000);
		
		System.out.println("book1: " + book1);
		System.out.println("book5: " + book2);
		
//		"=="를 사용해서 같은가 비교할 수 있는 데이터는 기본 자료형 8가지와 null만 가능
//		따라서, 클래스로 만든 객체는 "=="로 비교할 수 없다.
//		String은 단일 데이터가 저장되는 클래스라서 equals() 메소드만 사용하면
//		저장된 내용이 같은가 다른가를 비교할 수 있지만, String을 제외한 
//		사용자 정의 클래스의 객체는 단일 데이터가 아니기 때문에 별도의 설정을 하지 않으면
//		equals() 메소드로 비교할 수 없다.
		if(book6.getAuthor().equals(book7.getAuthor())) {
			System.out.println("같다");
		} else {
			System.out.println("다르다");
		}
		
//		별도의 설정을 하지 않으면 아래의 결과는 "다르다"가 출력된다.
//		클래스 객체를 비교할 때는 객체를 구성하는 모든 필드의 내용이 같은가
//		비교할 수 있도록 equals() 메소드를 내용이 동일한 객체는 같은 hashcode를
//		가질수 있도록 hashcode() 메소드를 Override 시켜 사용하면 "같다" 가 출력된다
		if(book6.equals(book7)) {
			System.out.println("같다");
		} else {
			System.out.println("다르다");
		}
//	여러권의 책 정보를 기억하는 클래스의 객체를 생성한다.
		BookList bookList = new BookList();
		
//	책 정보를 BookList 클래스의 bookList 배열에 저장하는 메소드를 실행한다.
		bookList.addBook(book1);
		bookList.addBook(book2);
		bookList.addBook(book3);
		bookList.addBook(book4);
		
		System.out.println(bookList);
		
	}
}








