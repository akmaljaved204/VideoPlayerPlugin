package com.fortsolution.playerplugin;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class VideoPlayer extends CordovaPlugin {
    public static final String ACTION_ADD_CALENDAR_ENTRY = "triggerVideoPlayer";
	public static CallbackContext myCallback;
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) { 
                JSONObject arg_object = args.getJSONObject(0);
				String videoURL= arg_object.getString("videoCURL");				
				Intent intent = new Intent(this.cordova.getActivity(),VideoViewActivity.class);
				intent.putExtra("videourl", videoURL);				
               this.cordova.getActivity().startActivity(intent);
			   myCallback=callbackContext;
              // callbackContext.success();
               return true;
            }
            callbackContext.error("Invalid action");
            return false;
        } catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
            callbackContext.error(e.getMessage());
            return false;
        } 
    }
}