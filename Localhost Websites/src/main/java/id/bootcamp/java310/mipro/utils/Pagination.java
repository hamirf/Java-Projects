package id.bootcamp.java310.mipro.utils;

public class Pagination<T> {
	
	private int total_data;
	private int page;
	private int item_per_page;
	private T data;
	
	public Pagination() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Pagination(int total_data, int page, int item_per_page, T data) {
		super();
		this.total_data = total_data;
		this.page = page;
		this.item_per_page = item_per_page;
		this.data = data;
	}

	public int getTotal_data() {
		return total_data;
	}
	public void setTotal_data(int total_data) {
		this.total_data = total_data;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getItem_per_page() {
		return item_per_page;
	}
	public void setItem_per_page(int item_per_page) {
		this.item_per_page = item_per_page;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
	
}	
