package com.zhiruitech.supercamera;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class VideoPlayList extends Activity {
	  private static final String TAG = "VideoPlayList";
	  
	  private ExpandableListView mListView;
	  
	  private ContentResolver mContentResolver;
	  
	  private List mList = new ArrayList();
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	     
	        mContentResolver = getContentResolver();
	        
	        setContentView(R.layout.activity_video_list);
	        
	        mListView = (ExpandableListView)findViewById(R.id.elvVideoList);
	        
	    }
	 
	  public void onResume(){
		  super.onResume();
		  
		  initVideoList();
	  }
	  
	  private void initVideoList(){
		  String path = Util.getSuperCameraVideoPath();
		  String str1 = " _data like '" + path + "%' ";
		  Cursor cursor = mContentResolver.
				  query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, 
						  new String[]{ "duration", "datetaken", "_data" }, str1, null, "datetaken DESC");
		  if(cursor != null){
			  while(!cursor.moveToNext()){
				  String videoPath = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
				  String videodata = cursor.getString(cursor.getColumnIndexOrThrow("datetaken"));
				  String videoduration = cursor.getString(cursor.getColumnIndexOrThrow("duration"));
				  Log.i(TAG, "zhangwuba ---- videoPath = " + videoPath);
				  Log.i(TAG, "zhangwuba ---- videodata = " + videodata);
				  Log.i(TAG, "zhangwuba ---- videoduration = " + videoduration);
			  }
		  }
	  }
	 
	 
	 class MyAdapter extends SimpleExpandableListAdapter{

		public MyAdapter(Context context,
				List<? extends Map<String, ?>> groupData,
				int expandedGroupLayout, int collapsedGroupLayout,
				String[] groupFrom, int[] groupTo,
				List<? extends List<? extends Map<String, ?>>> childData,
				int childLayout, int lastChildLayout, String[] childFrom,
				int[] childTo) {
			super(context, groupData, expandedGroupLayout, collapsedGroupLayout, groupFrom,
					groupTo, childData, childLayout, lastChildLayout, childFrom, childTo);
			// TODO Auto-generated constructor stub
		}

		
		 
	 }

}
