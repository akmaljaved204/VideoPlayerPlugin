package com.fortsolution.playerplugin;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
import com.fortsolution.madadd.R;

public class VideoViewActivity extends Activity implements OnClickListener
{
	
	private ProgressDialog progressDialog = null;
	private VideoView videoview;
	private Button btnCross;
	private String videoURL = "";
	private boolean isComplete=false;
	@Override
	protected void onCreate(Bundle args) {
		super.onCreate(args);
		setContentView(R.layout.videoview_main);
		videoview = (VideoView) findViewById(R.id.VideoView);
		btnCross= (Button) findViewById(R.id.btnCross);
		btnCross.setOnClickListener(this);
		videoURL=getIntent().getStringExtra("videourl");
		showLoading();

		try {
			MediaController mediacontroller = new MediaController(
					VideoViewActivity.this);
			mediacontroller.setAnchorView(videoview);
			Uri video = Uri.parse(videoURL);
			videoview.setMediaController(mediacontroller);
			videoview.setVideoURI(video);

		} catch (Exception e) {
			hideLoading();
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}

		videoview.requestFocus();
		videoview.setOnPreparedListener(new OnPreparedListener() {
			public void onPrepared(MediaPlayer mp) {
				hideLoading();
				videoview.start();
			}
		});
		videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
        {           
            public void onCompletion(MediaPlayer mp) 
            {
            	isComplete=true;
            }           
        });   

	}
	public void onClick(View v) {
		if(v==btnCross){
			finish();
		}
	}	
	
	public void showLoading() {
		runOnUiThread(new Runnable() {
			public void run() {
				progressDialog = ProgressDialog.show(VideoViewActivity.this, "",
						"Loading...", true, true);
				progressDialog.setCancelable(false);
				if (!progressDialog.isShowing()) {
					progressDialog.show();
				}
			}
		});
	}

	public void hideLoading() {
		runOnUiThread(new Runnable() {
			public void run() {
				if(progressDialog!=null){
					if (progressDialog.isShowing()) {
						progressDialog.cancel();
					}
				}
				
			}
		});
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    if(!progressDialog.isShowing()){
	    	 showLoading();
	 	 	videoview.start(); //Or use resume() if it doesn't work. I'm not sure
	    }
	   
	}
	@Override
	protected void onDestroy() {
		if(isComplete){
			VideoPlayer.myCallback.success("true");
		}else{
			VideoPlayer.myCallback.success("false");
		}
		super.onDestroy();
	    
	}
}
