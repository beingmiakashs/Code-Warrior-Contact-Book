package com.du.codewarriorcontact.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "phone_contacts")
public class PhoneContacts {
	
	@DatabaseField(generatedId = true)
    private int row_id;
	@DatabaseField
    private String cid;
	@DatabaseField
    private String first_name;
	@DatabaseField
    private String last_name;
	@DatabaseField
    private String email;
	@DatabaseField
    private String phone_number;
	@DatabaseField
    private String company;
	@DatabaseField
    private String job_title;
	@DatabaseField
    private String picture_url;
	
	//================================================================================
    // Constructors
    //================================================================================    
    public PhoneContacts() {
        // ORMLite needs a no-arg constructor 
    }
    public PhoneContacts(	String cid,
    						String firstName,
    						String lastName,
    						String email,
    						String phoneNumber,
    						String company,
    						String jobTitle,
    						String pictureUrl) {
    	this.cid = cid;
		this.first_name = firstName;
		this.last_name = lastName;
		this.email = email;
		this.phone_number = phoneNumber;
		this.company = company;
		this.job_title = jobTitle;
		this.picture_url = pictureUrl;
    }
    
    //================================================================================
    // Accessors
    //================================================================================
    public int getRowId() {
        return row_id;
    }
    public void setRowId(int id) {
        this.row_id = id;
    }
    
    public String getCid() {
        return cid;
    }
    public void setCid(String id) {
        this.cid = id;
    }
    
    public String getFirstName() {
        return first_name;
    }
    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }
    
    public String getLastName() {
        return last_name;
    }
    public void setLastName(String lastName) {
        this.last_name = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhoneNumber() {
        return phone_number;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phone_number = phoneNumber;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getJobTitle() {
        return job_title;
    }
    public void setJobTitle(String jobTitle) {
        this.job_title = jobTitle;
    }
    
    public String getPictureUrl() {
        return picture_url;
    }
    public void setPictureUrl(String url) {
        this.picture_url = url;
    }

}
