package com.du.codewarriorcontact.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import com.du.codewarriorcontact.Utils;
import com.du.codewarriorcontact.model.Contact;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;

public class CWDAO {

	private DatabaseHelper databaseHelper = null;
	private RuntimeExceptionDao<FacebookContacts, Integer> facebookContactsDAO = null;
	private RuntimeExceptionDao<GmailContacts, Integer> gmailContactsDAO = null;
	private RuntimeExceptionDao<PhoneContacts, Integer> phoneContactsDAO = null;
	private RuntimeExceptionDao<SingleContacts, Integer> singleContactsDAO = null;
	//getter and setter
	public DatabaseHelper getDatabaseHelper(){
		return databaseHelper;
	}
	public void setDatabaseHelper(DatabaseHelper dbh){
		this.databaseHelper = dbh;
	}
	public DatabaseHelper getHelper(Activity ac) {
	    if (databaseHelper == null) {
	        databaseHelper = OpenHelperManager.getHelper(ac, DatabaseHelper.class);
	    }
	    return databaseHelper;
	}
	public RuntimeExceptionDao<FacebookContacts, Integer> getFacebookContactsDAO() {
		return facebookContactsDAO;
	}
	public void setFacebookContactsDAO(RuntimeExceptionDao<FacebookContacts, Integer> facebookContactsDAO) {
		this.facebookContactsDAO = facebookContactsDAO;
	}
	public RuntimeExceptionDao<GmailContacts, Integer> getGmailContactsDAO() {
		return gmailContactsDAO;
	}
	public void setGmailContactsDAO(RuntimeExceptionDao<GmailContacts, Integer> gmailContactsDAO) {
		this.gmailContactsDAO = gmailContactsDAO;
	}
	public RuntimeExceptionDao<PhoneContacts, Integer> getPhoneContactsDAO() {
		return phoneContactsDAO;
	}
	public void setPhoneContactsDAO(RuntimeExceptionDao<PhoneContacts, Integer> phoneContactsDAO) {
		this.phoneContactsDAO = phoneContactsDAO;
	}
	public RuntimeExceptionDao<SingleContacts, Integer> getSingleContactsDAO() {
		return singleContactsDAO;
	}
	public void setSingleContactsDAO(RuntimeExceptionDao<SingleContacts, Integer> singleContactsDAO) {
		this.singleContactsDAO = singleContactsDAO;
	}
	
	//singleton pattern
	public CWDAO(){}
	private static CWDAO cwdao = null;
	public static CWDAO getCWdao(){
		if(cwdao == null){
			cwdao = new CWDAO();
		}
		return cwdao;
	}
	
	public void init(Activity ac){
		databaseHelper = getHelper(ac);
		setFacebookContactsDAO(databaseHelper.getFacebookContactsDAO());
		setGmailContactsDAO(databaseHelper.getGmailContactsDAO());
		setPhoneContactsDAO(databaseHelper.getPhoneContactsDAO());
		setSingleContactsDAO(databaseHelper.getSingleContactsDAO());
	}
	public void close(){
		if (databaseHelper != null) {
	        OpenHelperManager.releaseHelper();
	        databaseHelper = null;
	    }
	}
	
	public List<FacebookContacts> getFacebookContacts() {
		return getFacebookContactsDAO().queryForAll();
	}
	
	public Contact getContactDetailsOfId(long id) throws Exception {
		Contact ct = new Contact();
		QueryBuilder<SingleContacts, Integer> qb = getSingleContactsDAO().queryBuilder();
		qb.where().eq("row_id", id);
		SingleContacts sc = qb.query().get(0);
		if(sc == null) return null;
		
		String cid = sc.getCid();
		QueryBuilder<PhoneContacts, Integer> qb2 = getPhoneContactsDAO().queryBuilder();
		qb2.where().eq("cid", cid);
		PhoneContacts pc = qb2.query().get(0);
		if(pc == null) return null;
		
		ct.company = pc.getCompany();
		ct.email = pc.getEmail();
		ct.firstName = pc.getFirstName();
		ct.lastName = pc.getLastName();
		ct.jobTitle = pc.getJobTitle();
		ct.phoneNumberHome = pc.getPhoneNumberHome();
		ct.phoneNumberMobile = pc.getPhoneNumberMobile();
		ct.phoneNumberWork = pc.getPhoneNumberWork();
		ct.phoneNumberOther = pc.getPhoneNumberOther();
		
		String uri = pc.getPictureUrl();
		return ct;
	}

	public List<Contact> getContactsList(Activity ac) throws SQLException {
		List<Contact> ret = new ArrayList<Contact>();
		
		List<SingleContacts> scList = getSingleContactsDAO().queryForAll();
		for (SingleContacts sc : scList ) {
			String cid = sc.getCid();
			String fid = sc.getFid();
			String gid = sc.getGid();
			
			QueryBuilder<PhoneContacts, Integer> qb = getPhoneContactsDAO().queryBuilder();
			qb.where().eq("cid", cid);
			PhoneContacts pc = qb.query().get(0);
			//will integrate facebook and gmail later.
			ret.add(new Contact(
					Integer.toString(pc.getRowId()),
					pc.getFirstName(),
					pc.getLastName(),
					pc.getPhoneNumberMobile(),
					pc.getPhoneNumberHome(),
					pc.getPhoneNumberWork(),
					pc.getPhoneNumberOther(),
					pc.getNote(),
					pc.getAddress(),
					pc.getEmail(),
					pc.getCompany(),
					pc.getJobTitle(),
					
					Utils.retrieveContactPhoto(ac,Long.parseLong(pc.getPictureUrl()) ) ));
		}	
		return ret;
	}
}
