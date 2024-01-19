package com.tjoeun.bookshop;

import java.util.Date;
import java.util.Objects;

//	클래스는 객체를 정의해 놓은 것 => 설계도
//	클래스에는 객체의 속성과 기능이 정의되어 있다.
//	클래스의 용도는 객체를 생성하는데 사용.
//	객체(인스턴스) : 유형의 사물 + 무형의 논리와 개념
//	=> 가지고 있는 기능과 속성에 따라 다른 용도를 가짐.
//	=> 프로그래밍에서는 클래스에 정의된 대로 메모리에 생성되는 것을 뜻한다.
//	=> 객체의 구성요소에는 속성과 기능이 있다.

//	VO(Value Object) 클래스 : 1건의 데이터와 데이터를 처리하는 메소드를 모아 놓은 클래스이다.
//	VO 클래스 DTO(Data Transfer Object) 클래스라고도 부른다.
//	bean : 처리할 데이터를 기억하는 변수와 변수에 데이터를 입력 / 출력 할 수 있는 getters &
//	setters 메소드만 구성된 클래스를 말한다.

//	도서정보 1건을 기억하는 클래스 => 책 학권의 정보 기억(책제목, 저자, 발행일, 출판사...)
//	도서정보 => 도서명 / 저자 / 출판사 / 출판일 / 가격
public class BookVO {
	
//	데이터를 기억할 멤버변수(필드) 선언한다 => 클래스 내부에 선언되는 필드는 모든 메소드에서
//	사용할 수 있다.
//	필드(멤버변수) / 특성 / 상태 => 클래스의 속성
//	메소드 / 함수 / 행위 => 클래스의 기능
//	일반 변수를 선언하면 변수에 어떤값이 들어있는지 알수가 없어서(쓰레기값) 초기화를 해야 사용 가능
//	=> 클래스에서 선언하는 필드(멤버변수)는 별도로 초기화를 하지 않아도 숫자는 0, 문자는 공백,
//	boolean은 false 클래스로 만든 객체(참조 변수)는 null로 자동 초기화 된다.
	
	private String title;	// 도서명
	private String author;	// 저자
	private String publisher;	// 출판사
	private Date writeDate;	// 출판일
	private int price;	// 가격
	
//	생성자를 선언한다.
//	생성자 이름은 반드시 클래스 이름과 같아야 한다.
//	생성자를 선언하지 않으면 컴파일러가 자동으로 아무런 일을 하지 않는 생성자(기본 생성자)를
//	만들어 주고, 사용자가 생성자를 선언하면 기본 생성자를 자동으로 만들어 주진 않는다.
//	생성자는 리턴타입을 사용하지 않고 생성자 내부에서 return도 사용하지 않는다.
//	생성자는 객체가 생성될 때 자동으로 실행되고, 메소드를 사용할 수 있는 상태로 만들어준다.
//	생성자는 필드의 초기화도 시킬 수 있다.
	
//	기본 생성자
	public BookVO() {
		System.out.println("기본 생성자가 실행됩니다.");
	}

	public BookVO(String title, String author, String publisher, 
			Date writeDate, int price) {
//		super()는 생략해도 자바 컴파일러가 자동을 붙여준다.
//		super();	// 부모 클래스의 기본 생성자를 호출한다.
//		this(); //	현재 클래스의 기본 생성자를 호출한다.
//		super()와 this()는 반드시 생성자의 첫 문장으로 적어야 한다.
//		=> 동시에 사용할 수 없다.
//		super는 부모클래스를 뜻하고, this는 현재 클래스를 의미한다.
		this.title = title;
		this.author = author;
		this.publisher = publisher;
//		매개변수로 넘어온 writeDate에 저장된 날짜 데이터에서 년은 1900을 월은 1을 빼서 다시
//		저장한다.
		writeDate.setYear(writeDate.getYear() - 1900);
		writeDate.setMonth(writeDate.getMonth() - 1);
		this.writeDate = writeDate;
		this.price = price;
	}

	
//	getters & setters 메소드를 선언한다.
//	getters & setters 메소드는 private 권한으로 선언된 필드를 클래스 외부에서 접근할 수 
//	있도록 예외규정을 만든다.
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Date getWriteDate() {
		return writeDate;
	}
	
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

//	클래스 객체에 저장된 데이터를 보고싶다면 toString() 메소드를 Override(재정의) 해야한다.
//	@Override 어노데이션 : 이 메소든가 부모 클래스에서 상속받은 메소드를 Override한 메소드
//	임을 의미한다.
//	@Override 어노테이션이 붙어있는 메소드는 메소드 이름을 수젛애서 상속받은
//	메소드 이름과 다르게 하면 에러를 발생시킨다.
	@Override
	public String toString() {
		return "BookVO [title=" + title + ", author=" + author + ", publisher=" + publisher + ", writeDate=" + writeDate
				+ ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, price, publisher, title, writeDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		return Objects.equals(author, other.author) && price == other.price
				&& Objects.equals(publisher, other.publisher) && Objects.equals(title, other.title)
				&& Objects.equals(writeDate, other.writeDate);
	}
	
	

	
	
	
	
	

}












