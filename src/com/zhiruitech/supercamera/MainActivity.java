/**
 * superCamera MainActivity
 * Create by zhangwuba 2014-1-2
 * 
 * 
 */

package com.zhiruitech.supercamera;



import com.zhiruitech.supercamera.R;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;


public class MainActivity extends Activity {
	private static final String TAG = "SuperCamera.MainActivity";
	
	
	
	private Handler mHandler = new Handler();
	
    private TextView mVideoPlay;
    private TextView mVideoRecorderStart;
    private Button mVideoRecorderStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        mVideoPlay = (TextView)findViewById(R.id.tvPlayback);
        mVideoRecorderStart = (TextView)findViewById(R.id.tvTakeVideo);
        mVideoRecorderStop = (Button)findViewById(R.id.stopRecoder);
        
        mVideoPlay.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, VideoPlayList.class);
				MainActivity.this.startActivity(intent);
			}
		});
        
        mVideoRecorderStart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent service = new Intent(MainActivity.this,MyFloatViewServices.class);  
				MainActivity.this.startService(service);
			}
		});
        
        mVideoRecorderStop.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MainActivity.this.stopService(new Intent(MainActivity.this, MyFloatViewServices.class));
			}
		});
        
        //Intent service = new Intent(this,MyFloatViewServices.class);  
        //this.startService(service);
    }

   public void onBackPressed(){
	   super.onBackPressed();
	   //stopService(new Intent(this, MyFloatViewServices.class));
   }
   
  
 
    
}
