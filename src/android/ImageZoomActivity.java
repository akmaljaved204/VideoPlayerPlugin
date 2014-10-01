package org.devgirl.calendar;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fortsolution.gnossem.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class  ImageZoomActivity extends Activity implements OnClickListener{
	private ProgressDialog progressDialog = null;
	private RelativeLayout imageLayout;
	private Button brnCross;
	private String imageURL="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageLayout=(RelativeLayout) findViewById(R.id.imageLayout);
		imageURL=getIntent().getStringExtra("imageUrl");
		//imageURL="http://images.gnossem.com/components/com_virtuemart/shop_image/product/GNOSSEM-ANTIPODIUMSS14-018(01).jpg";
		brnCross=(Button) findViewById(R.id.btnCross);
		brnCross.setOnClickListener(this);
		loadImageFromURL();
	}
	
	@Override
	public void onClick(View v) {
		if(v==brnCross){
			finish();
		}
	}

	public void loadImageFromURL() {
		Thread thread= new Thread(new Runnable() {
			@Override
			public void run() {
				try {
						showLoading();
			            URL imageUrl = new URL(imageURL);
			            HttpURLConnection conn = (HttpURLConnection)imageUrl.openConnection();
			            conn.setConnectTimeout(30000);
			            conn.setReadTimeout(30000);
			            conn.setInstanceFollowRedirects(true);
			            InputStream is=conn.getInputStream();
			            final Bitmap bitmap =BitmapFactory.decodeStream(is);
			            runOnUiThread(new Runnable() {			
							@Override
							public void run() {
								RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT);
								ZoomFunctionality img = new ZoomFunctionality(ImageZoomActivity.this);
								img.setImageBitmap(bitmap);
								img.setLayoutParams(params);
								img.setMaxZoom(6f);
								imageLayout.addView(img);
								hideLoading();
							}
						});

				} catch (IOException e) {
					hideLoading();
					e.printStackTrace();
			
				}
				
			}
		});thread.start();
		
	}
	public void showLoading() {
		runOnUiThread(new Runnable() {
			public void run() {
				progressDialog = ProgressDialog.show(ImageZoomActivity.this, "",
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
}
