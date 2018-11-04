package com.example.springreactdemo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User
{
	@Id
	private long id;
	private String userName;
	private String firstName;
	private String lastName;
	private int age;
	private String emailAddress;

	public User()
	{
		super();
	}

	public User(long id, String userName, String firstName, String lastName, int age, String emailAddress)
	{
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.emailAddress = emailAddress;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}
}
