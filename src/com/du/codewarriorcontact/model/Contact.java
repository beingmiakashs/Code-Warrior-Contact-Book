package com.du.codewarriorcontact.model;

import android.graphics.Bitmap;
import android.net.Uri;

public class Contact {
	
	public String id;
	public String firstName ;
	public String lastName ;
	public String phoneNumber ; 
	public String phoneNumberWork ;
	public String phoneNumberHome ;
	public String email ;
	public String company ;
	public String jobTitle ;
	public String group;
	public Bitmap photo;
	
	public Uri contentUri ;
	
	public Contact(){};
	
	public Contact(String id,
			String firstName,
			String lastName,
			String phoneNumber,
			String email,
			String company,
			String jobTitle,
			Bitmap photo) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.company = company;
		this.jobTitle = jobTitle;
		this.photo = photo;
	}
}
