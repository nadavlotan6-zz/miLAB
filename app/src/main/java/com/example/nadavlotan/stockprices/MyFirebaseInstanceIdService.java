//package com.example.nadavlotan.stockprices;
//
//import android.util.Log;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
//
//    private static final String TAG = "MyFirebaseIIDService";
//    private static final String SERVER = "http://10.0.2.2:8080/";
//    private static final String USERNAME = "nadavlotan";
//
//    private RequestQueue _queue;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        _queue = Volley.newRequestQueue(this);
//    }
//
//
//    /**
//     * Called if InstanceID token is updated. This may occur if the security of
//     * the previous token had been compromised. Note that this is called when the InstanceID token
//     * is initially generated so this is where you would retrieve the token.
//     */
//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
//        sendRegistrationToServer(refreshedToken);
//    }
//
//    private void sendRegistrationToServer(String token) {
//        JSONObject requestObject = new JSONObject();
//        try {
//            requestObject.put("myToken", token);
//        } catch (JSONException e) {
//        }
//
//        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, SERVER + USERNAME + "/token", requestObject,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.i(TAG, "Token saved");
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, "Failed to save token - " + error);
//                    }
//                });
//
//        _queue.add(req);
//    }
//}