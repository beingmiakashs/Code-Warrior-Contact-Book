package com.du.codewarriorcontact.dataprovider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.util.Log;

import com.du.codewarriorcontact.MainActivity;
import com.du.codewarriorcontact.R;
import com.du.codewarriorcontact.model.Contact;

public class MessageHistoryDataProvider {
	
	private Activity activity;
	
	public ArrayList<Contact> displayContacts() {
		
		activity = MainActivity.activity;
		Log.d("data provider","call");
        
    	ArrayList<Contact> cList = new ArrayList<Contact>() ;
    	ContentResolver cr = activity.getContentResolver();
          Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                  null, null, null, null);
          Log.d("data provider","call "+cur.getCount());
          if (cur.getCount() > 0) {
              while (cur.moveToNext()) {
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    
                    if (Integer.parseInt(cur.getString(
                          cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                       Cursor pCur = cr.query(
                                 ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                 null,
                                 ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                                 new String[]{id}, null);
                       String phoneNo = null;
                       while (pCur.moveToNext()) {
                           phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                
                       }
                       Contact c = new Contact() ;
               		   c.firstName = name ;
               		   c.phoneNumber = phoneNo ;
               		   
               		   Bitmap photo = retrieveContactPhoto(Long.parseLong(id));
               		   if(photo!=null)
               			   c.photo=photo;
               		   else
               			   c.photo=BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_launcher);
               		   
               		   cList.add(c) ;
                       pCur.close();
                  }
                   
              }
          }
          return cList ;
      }
    
    
    private Bitmap retrieveContactPhoto(Long id) {
    	 
        Bitmap photo = null;
 
        try {
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(activity.getContentResolver(),
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,id));
            	
            if (inputStream != null) {
            	
                photo = BitmapFactory.decodeStream(inputStream);
            }
            else
            {
            	return null;
            }
 
            assert inputStream != null;
            inputStream.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		return photo;
 
    }

}
