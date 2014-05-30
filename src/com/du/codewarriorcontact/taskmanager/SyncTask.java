package com.du.codewarriorcontact.taskmanager;
import com.du.codewarriorcontact.contactspool.PhoneContactsPool;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class SyncTask extends AsyncTask<Void, Void, Void> {
	ProgressDialog pd ;
	Context ctx ;
	public SyncTask(Context ctx){
		this.ctx = ctx ;
	}
     protected Void doInBackground(Void ... params) {
    	 PhoneContactsPool pool = new PhoneContactsPool(ctx) ;
    	 pool.SyncAllContact();
    	 return null ;
     }

     protected void onProgressUpdate(Integer... progress) {
    	 
         
     }
     protected void onPreExecute(){
    	 pd = new ProgressDialog(ctx) ;
    	 pd.setProgress(0);
    	 pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	 pd.show();
     }
     
     protected void onPostExecute(Long result) {
         pd.hide();
         pd.dismiss();
     }
 }