package com.sample.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

//    private ArrayAdapter adapter;
//    private Spinner spinner;

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtEmail, mEtPwd, mEtId; //회원가입 입력필드
    private Button mBtnRegister; //회원가입 버튼



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


//        spinner = (Spinner) findViewById(R.id.majorSpinner);
//        adapter = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Android_Login");

//        mEtEmail = findViewById(R.id.emailText);
        mEtPwd = findViewById(R.id.passwordText);
        mEtId = findViewById(R.id.idText);
        mBtnRegister = findViewById(R.id.registerButton);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원가입 처리시작
//                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strId = mEtId.getText().toString();


                //FireBase Auth진행
                mFirebaseAuth.createUserWithEmailAndPassword(strId,strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                     if(task.isSuccessful()){
                         FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                         UserAccount account  = new UserAccount();
                         account.setIdToekn(firebaseUser.getUid());
                         account.setUserId(firebaseUser.getEmail());
                         account.setPassword(strPwd);


                         //setValue = database()에 insert (삽입) 행위
                         mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).setValue(account);

                         Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                     } else {
                         Toast.makeText(RegisterActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                     }
                    }
                });

            }
        });

//        Button registerButton = findViewById(R.id.registerButton);
/*        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });


        if(AppHelper.requestQueue != null) {
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public void sendRequest() {
        String url = "http://192.168.26.76:8080/get";
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() { //응답을 잘 받았을 때 이 메소드가 자동으로 호출
                    @Override
                    public void onResponse(String response) {
                        println("응답 -> " + response);
                    }
                },
                new Response.ErrorListener() { //에러 발생시 호출될 리스너 객체
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 -> " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();

                return params;
            }
        };
        request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
        AppHelper.requestQueue = Volley.newRequestQueue(this); // requestQueue 초기화 필수
        AppHelper.requestQueue.add(request);
        println("요청 보냄.");

    }

    public void println(String data) {
        Log.e("test", data);
    }*/




    }

}
