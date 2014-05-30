package com.du.codewarriorcontact.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.du.codewarriorcontact.R;
import com.du.codewarriorcontact.model.Contact;

public class ContactDetailsAdapter  extends BaseAdapter{
	private ArrayList<Contact> list = new ArrayList<Contact>() ;
	
	public void add(Contact t){
		list.add(t) ;
	}
	public ContactDetailsAdapter(ArrayList<Contact> listRecord){
		this.list = listRecord ;
		
	}
	@Override
	public int getCount() {
		return list.size() ;
	}

	@Override
	public Object getItem(int index) {
		return (Object)list.get(index) ;
	}

	@Override
	public long getItemId(int index) {
		return index ;
	}
	
	public static class ViewHolder{
		public TextView textTitle ;
		public TextView textDescription ;
	}
	@Override
	public View getView(int index, View view, ViewGroup parent) {
//		ViewHolder holder;
//		if (view == null){
//			LayoutInflater li = LayoutInflater.from(parent.getContext()) ;
//			view = li.inflate(R.layout.list_item, parent, false) ;
//			holder = new ViewHolder() ;
//			holder.textTitle = (TextView)view.findViewById(R.id.text_title) ;
//			holder.textDescription = (TextView)view.findViewById(R.id.text_description) ;
//			
//			view.setTag(holder) ;
//		}else
//			holder = (ViewHolder)view.getTag() ;
//		
//		Contact item = list.get(index) ;
//		Calendar c = Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
//		String strDate = sdf.format(c.getTime());
//		
//		holder.textTime.setText(item.firstName) ;
//		String phoneNo = "" ;
//		if(item.phoneNumberMobile != null && item.phoneNumberMobile.length() > 0)
//			phoneNo = item.phoneNumberMobile ;
//		else if(item.phoneNumberHome != null && item.phoneNumberHome.length() > 0)
//			phoneNo = item.phoneNumberHome ;
//		else if(item.phoneNumberWork != null && item.phoneNumberWork.length() > 0)
//			phoneNo = item.phoneNumberWork;
//		else if(item.phoneNumberOther != null && item.phoneNumberOther.length() > 0)
//			phoneNo = item.phoneNumberOther; 
//		
		return view;
	}

}
