package com.web_banking_application.banking.entities;

	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;

	@Entity
	@Table(name = "users")
	public class users {
		public users() {
			super();
		}
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long userId;
		@Column(name = "firstName")
		private String first_Name;
		@Column(name = "lastName")
		private String last_Name;
		@Column(name = "email", nullable = false, unique = true)
		private String email;
		@Column(name = "password")
		private String password;
		public users(long userId, String first_Name, String last_Name, String email, String password, long mobile) {
			super();
			this.userId = userId;
			this.first_Name = first_Name;
			this.last_Name = last_Name;
			this.email = email;
			this.password = password;
			this.mobile = mobile;
		}
		@Column(name = "mobileNumber",unique = true )
		private long mobile;
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
		public String getFirst_Name() {
			return first_Name;
		}
		public void setFirst_Name(String first_Name) {
			this.first_Name = first_Name;
		}
		public String getLast_Name() {
			return last_Name;
		}
		public void setLast_Name(String last_Name) {
			this.last_Name = last_Name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public long getMobile() {
			return mobile;
		}
		public void setMobile(long mobile) {
			this.mobile = mobile;
		}
		
	}
