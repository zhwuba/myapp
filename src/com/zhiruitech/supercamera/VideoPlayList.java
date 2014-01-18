package com.zhiruitech.supercamera;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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
	  
	  private List<HashMap<String, String>> mGroupdate = new ArrayList<HashMap<String, String>>();
	  private List<List<HashMap<String, String>>> mChild = new ArrayList<List<HashMap<String, String>>>();
	
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
		  String str1 = " _data LIKE '" + path + "super-"+ "%' ";
		  Cursor cursor = mContentResolver.
				  query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, 
						  new String[]{ "duration", "datetaken", "_data" }, str1, null, "datetaken DESC");
		  Log.i(TAG, "zhangwuba ---- initVideoList str1 = " + str1);
		  String groupdate = "";
		  if(cursor != null){
			  //cursor.moveToFirst();
			  while(cursor.moveToNext()){
				  String videoPath = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
				  long videodate = cursor.getLong(cursor.getColumnIndexOrThrow("datetaken"));
				  int videoduration = cursor.getInt(cursor.getColumnIndexOrThrow("duration"));
				  Log.i(TAG, "zhangwuba ---- videoPath = " + videoPath);
				  Log.i(TAG, "zhangwuba ---- videodata = " + videodate);
				  Log.i(TAG, "zhangwuba ---- videoduration = " + videoduration);
				  
				  String date = new Timestamp(videodate).toString().substring(0, 10);
				  String videotime = new Timestamp(videodate).toString().substring(11, 19);;
				  String duretion = Util.videoDuretionFrmat(videoduration);
				  
				  Log.i(TAG, "zhangwuba ---- date = " + date);
				  Log.i(TAG, "zhangwuba ---- date = " + videotime);
				  Log.i(TAG, "zhangwuba ---- duretion = " + duretion);
				  if(groupdate == null || groupdate.equals("") || !groupdate.equals(date)){
					  HashMap<String, String> groupHashMap = new HashMap<String, String>();
					  groupHashMap.put("groupDate", date);
					  groupdate = date;
					  
					  mGroupdate.add(groupHashMap);
				  }
				  
				  HashMap<String, String> childHashMap = new HashMap<String, String>();
				  childHashMap.put("videoTime", videotime);
				  childHashMap.put("videoDuration", duretion);
				  childHashMap.put("videoPath", videoPath);
				  
				  List<HashMap<String, String>> childList = new ArrayList<HashMap<String, String>>();
				  childList.add(childHashMap);
				  mChild.add(childList);
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
