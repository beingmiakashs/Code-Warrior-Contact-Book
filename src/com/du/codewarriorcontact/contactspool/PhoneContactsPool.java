package com.du.codewarriorcontact.contactspool;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;

import com.du.codewarriorcontact.Utils;
import com.du.codewarriorcontact.database.CWDAO;
import com.du.codewarriorcontact.database.PhoneContacts;
import com.du.codewarriorcontact.database.SingleContacts;
import com.du.codewarriorcontact.model.Contact;

public class PhoneContactsPool implements IContactsPool{
	private ArrayList<PhoneContacts> contactList ;
	private Context ctx ;
	
	public PhoneContactsPool(Context ctx){
		this.ctx = ctx ;
		this.contactList = new ArrayList<PhoneContacts>() ;
	}
	
	
	public ArrayList<PhoneContacts> getAllContacts(){
		return this.contactList ;
	}
	
//	public void EditContact(PhoneContacts PhoneContacts){
//		String DisplayName = PhoneContacts.name;
//		String MobileNumber = PhoneContacts.phoneNumber;
//		String HomeNumber = PhoneContacts.phoneNumberHome;
//		String WorkNumber = PhoneContacts.phoneNumberWork;
//		String emailID = PhoneContacts.email;
//		String company = PhoneContacts.company;
//		String jobTitle = PhoneContacts.jobTitle;
//		
//		int id = 1;
//        String firstname = PhoneContacts.name.split(" ")[0];
//        String lastname = PhoneContacts.name.split(" ")[1];
//        
//        //String photo_uri = "android.resource://com.my.package/drawable/default_photo";
//
//        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
//
//        // Name
//        Builder builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
//        builder.withSelection(ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "=?", new String[]{String.valueOf(id), ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE});
//        builder.withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, lastname);
//        builder.withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, firstname);
//        ops.add(builder.build());
//
//        // Number
//        builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
//        builder.withSelection(ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "=?"+ " AND " + ContactsContract.CommonDataKinds.Organization.TYPE + "=?", new String[]{String.valueOf(id), ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, String.valueOf(ContactsContract.CommonDataKinds.Phone.TYPE_HOME)});
//        builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, PhoneContacts.phoneNumber);
//        ops.add(builder.build());
//
//
//        // Picture
////        try
////        {
////            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(photo_uri));
////            ByteArrayOutputStream image = new ByteArrayOutputStream();
////            bitmap.compress(Bitmap.CompressFormat.JPEG , 100, image);
////
////            builder = ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI);
////            builder.withSelection(ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "=?", new String[]{String.valueOf(id), ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE});
////            builder.withValue(ContactsContract.CommonDataKinds.Photo.PHOTO, image.toByteArray());
////            ops.add(builder.build());
////        }
////        catch (Exception e)
////        {
////            e.printStackTrace();
////        }
//
//        // Update
//        try
//        {
//            ctx.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//	}
//	
//	
//	
//	public Uri AddContact(PhoneContacts PhoneContacts){
//		String DisplayName = PhoneContacts.name;
//		String MobileNumber = PhoneContacts.phoneNumber;
//		String HomeNumber = PhoneContacts.phoneNumberHome;
//		String WorkNumber = PhoneContacts.phoneNumberWork;
//		String emailID = PhoneContacts.email;
//		String company = PhoneContacts.company;
//		String jobTitle = PhoneContacts.jobTitle;
//		
//		ArrayList < ContentProviderOperation > ops = new ArrayList < ContentProviderOperation > ();
//		
//		 ops.add(ContentProviderOperation.newInsert(
//		 ContactsContract.RawContacts.CONTENT_URI)
//		     .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
//		     .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
//		     .build());
//
//		 //------------------------------------------------------ Names
//		 if (DisplayName != null) {
//		     ops.add(ContentProviderOperation.newInsert(
//		     ContactsContract.Data.CONTENT_URI)
//		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//		         .withValue(ContactsContract.Data.MIMETYPE,
//		     ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
//		         .withValue(
//		     ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
//		     DisplayName).build());
//		 }
//
//		 //------------------------------------------------------ Mobile Number                     
//		 if (MobileNumber != null) {
//		     ops.add(ContentProviderOperation.
//		     newInsert(ContactsContract.Data.CONTENT_URI)
//		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//		         .withValue(ContactsContract.Data.MIMETYPE,
//		     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//		         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
//		         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
//		     ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
//		         .build());
//		 }
//
//		 //------------------------------------------------------ Home Numbers
//		 if (HomeNumber != null) {
//		     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//		         .withValue(ContactsContract.Data.MIMETYPE,
//		     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//		         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, HomeNumber)
//		         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
//		     ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
//		         .build());
//		 }
//
//		 //------------------------------------------------------ Work Numbers
//		 if (WorkNumber != null) {
//		     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//		         .withValue(ContactsContract.Data.MIMETYPE,
//		     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
//		         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, WorkNumber)
//		         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
//		     ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
//		         .build());
//		 }
//
//		 //------------------------------------------------------ Email
//		 if (emailID != null) {
//		     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//		         .withValue(ContactsContract.Data.MIMETYPE,
//		     ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
//		         .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
//		         .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
//		         .build());
//		 }
//
//		 //------------------------------------------------------ Organization
//		 if (!company.equals("") && !jobTitle.equals("")) {
//		     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
//		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
//		         .withValue(ContactsContract.Data.MIMETYPE,
//		     ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
//		         .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
//		         .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
//		         .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
//		         .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
//		         .build());
//		 }
//
//		 // Asking the PhoneContacts provider to create a new PhoneContacts                 
//		 try {
//		     ContentProviderResult [] res = (ctx.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops));
//		     return res[0].uri ; 
//		     //Log.e("COUNT", res.length + "") ;
////		     for(ContentProviderResult rr : res){
////		    	 Log.e(rr.uri.toString(), "NORMAL MSG 22") ;
////		     }
//		 } catch (Exception e) {
//		     e.printStackTrace();
//		     //Toast.makeText(myContext, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//		 }
//		 return null ;
//////		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
//////        int rawContactInsertIndex = ops.size();
//////
//////        ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI).withValue(RawContacts.ACCOUNT_TYPE, null).withValue(RawContacts.ACCOUNT_NAME, null).build());
//////        try {
//////			ctx.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
//////		} catch (RemoteException e) {
//////			// TODO Auto-generated catch block
//////			e.printStackTrace();
//////		} catch (OperationApplicationException e) {
//////			// TODO Auto-generated catch block
//////			e.printStackTrace();
//////		}
////        
////        //ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference(Data.RAW_CONTACT_ID, rawContactInsertIndex).withValue(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
////        //        .withValue(StructuredName.DISPLAY_NAME, personalVcard.getFirstName()).build());
////		
////		ContentValues values = new ContentValues();
////        values.put(Data.RAW_CONTACT_ID, 001);
////        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
////        values.put(Phone.NUMBER, PhoneContacts.phoneNumber);
////        values.put(Phone.TYPE, Phone.TYPE_CUSTOM);
////        values.put(Phone.LABEL, "Nirav");
////        //values.put(Phone.PHONETIC_NAME, p
////        Uri dataUri = ctx.getContentResolver().insert(android.provider.ContactsContract.Data.CONTENT_URI, values);
//	}
	
	public void DeleteContact(Uri uri){
		ContentResolver cr = ctx.getContentResolver() ;
		cr.delete(uri, null, null) ;
	}
	
	@Override
	public void SyncAllContact() {
		ContentResolver cr = ctx.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                  String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                  String firstName = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
                  String lastName = cur.getString(cur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
                  String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                  String phone = "";
                  if (Integer.parseInt(cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                     Cursor pCur = cr.query(
                               ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                               null,
                               ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                               new String[]{id}, null);
                     while (pCur.moveToNext()) {
                         String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                         phone += phoneNo + " ";
                     }
                     pCur.close();
                  }
                  Cursor emailCur = cr.query( 
                		  ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
                		  null,
                		  ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", 
                		  new String[]{id}, null);
                  
                  String fullEmail = "";
        		 while (emailCur.moveToNext()) { 
        		     // This would allow you get several email addresses
        		            // if the email addresses were stored in an array
        		     String email = emailCur.getString(
        		                      emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
        		      String emailType = emailCur.getString(
        		                      emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE)); 
        		      fullEmail += email + " ";
        		  } 
        		  emailCur.close();
        		  
        		  String orgWhere = Contacts.ContactMethods.PERSON_ID + " = ?"; 
        		  String[] orgWhereParams = new String[]{id}; 
        		  Cursor orgCur = cr.query(Contacts.Organizations.CONTENT_URI, 
        		               null, orgWhere, orgWhereParams, null);
        		  String orgName = "";
        		  String jobTitle = "";
        		  if (orgCur.moveToFirst()) { 
        		   orgName = orgCur.getString(
        		                    orgCur.getColumnIndex(Contacts.Organizations.COMPANY));
//        		   jobTitle = orgCur.getString(
//        		                    orgCur.getColumnIndex(Contacts.));
        		  } 
        		  orgCur.close();
        		  Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id));
        		  Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        		  
                  PhoneContacts pc = new PhoneContacts(id, firstName, lastName, fullEmail, phone, orgName, jobTitle, id);
                  CWDAO.getCWdao().getPhoneContactsDAO().createOrUpdate(pc);
                  CWDAO.getCWdao().getSingleContactsDAO().createOrUpdate(new 
                		  SingleContacts(pc.getCid(), "", ""));
            }
        }
	}
	
//	public PhoneContacts GetByUri(Uri uri){
//		ContentResolver cr = ctx.getContentResolver() ;
//		//Cursor cc = cr.query(uri, null, null, "", null) ; //(uri, null, null) ;
//		
//	}
	
	

}
