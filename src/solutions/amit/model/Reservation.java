package solutions.amit.model;


public class Reservation {
	
	private int reserve_id;
	private String date;
	private String time_from;
	private String time_to;
	private int reserve_table_id;
	private int user_id;
	
	private String name;
	private String email;
	private String phone;
	private int no_of_people;
	private String createdBy;
	private String special_arrangement;
	
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getReserve_table_id() {
		return reserve_table_id;
	}
	public void setReserve_table_id(int reserve_table_id) {
		this.reserve_table_id = reserve_table_id;
	}
	public int getReserve_id() {
		return reserve_id;
	}
	public void setReserve_id(int reserve_id) {
		this.reserve_id = reserve_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime_from() {
		return time_from;
	}
	public void setTime_from(String time_from) {
		this.time_from = time_from;
	}
	public String getTime_to() {
		return time_to;
	}
	public void setTime_to(String time_to) {
		this.time_to = time_to;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getNo_of_people() {
		return no_of_people;
	}
	public void setNo_of_people(int no_of_people) {
		this.no_of_people = no_of_people;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getSpecial_arrangement() {
		return special_arrangement;
	}
	public void setSpecial_arrangement(String special_arrangement) {
		this.special_arrangement = special_arrangement;
	}
	
}
