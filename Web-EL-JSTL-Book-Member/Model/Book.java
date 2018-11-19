package com.scsa.model.vo;

public class Book {
	private String booknum;
	private String title;
	private String sort;
	private String country;
	private String date;
	private String publisher;
	private String writer;
	private int price;
	private String desc;
	public String getBooknum() {
		return booknum;
	}
	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Book(String booknum, String title, String sort, String country, String date, String publisher, String writer,
			int price, String desc) {
		this.booknum = booknum;
		this.title = title;
		this.sort = sort;
		this.country = country;
		this.date = date;
		this.publisher = publisher;
		this.writer = writer;
		this.price = price;
		this.desc = desc;
	}
	
	public Book() {
	}
	@Override
	public String toString() {
		return "Book [booknum=" + booknum + ", title=" + title + ", sort=" + sort + ", country=" + country + ", date="
				+ date + ", publisher=" + publisher + ", writer=" + writer + ", price=" + price + ", desc=" + desc
				+ "]";
	}
	
}
