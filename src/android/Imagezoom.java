package com.fortsolution.playerplugin;
 
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class Imagezoom extends CordovaPlugin {
    public static final String ACTION_TRIGGER_ZOOM = "triggerZoom";
	public static CallbackContext myCallback;
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (ACTION_TRIGGER_ZOOM.equals(action)) { 
                JSONObject arg_object = args.getJSONObject(0);
				String imageUrl= arg_object.getString("imageUrl");
                Intent intentZoom = new Intent(this.cordova.getActivity(),ImageZoomActivity.class);
				intentZoom.putExtra("imageUrl",imageUrl);
               this.cordova.getActivity().startActivity(intentZoom);
			   myCallback=callbackContext;
               //callbackContext.success("Hello result from success calback ");
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