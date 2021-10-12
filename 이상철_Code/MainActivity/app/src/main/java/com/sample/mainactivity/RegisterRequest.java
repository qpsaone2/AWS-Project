package com.sample.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends AppCompatActivity {

/*
    private static final String TAG = "MAIN";
//    private ;
    private EditText idText;
    private EditText passwordText;
    private Button registerButton;
//    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//         = findViewById(R.id.genderMan);
         idText = findViewById(R.id.idText);
         passwordText = findViewById(R.id.passwordText);
//         = findViewById(R.id.majorSpinner);
        registerButton = findViewById(R.id.registerButton);

        queue = Volley.newRequestQueue(this);
        String url = "http://192.168.43.100:8080/add";

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                .setText(response);
                Log.e("test", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", idText.getText().toString());
                params.put("pw", passwordText.getText().toString());
                return params;
            }
        };

        stringRequest.setTag(TAG);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queue.add(stringRequest);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }
*/


}
