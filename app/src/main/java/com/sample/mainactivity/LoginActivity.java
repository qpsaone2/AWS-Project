package com.sample.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtEmail, mEtPwd, mEtId; //회원가입 입력필드
    private Button mBtnRegister; //회원가입 버튼
    private Button mBtnLogin; //로그인 버튼



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Android_Login");
//              mEtEmail = findViewById(R.id.emailText);
        mEtPwd = findViewById(R.id.passwordText);
        mEtId = findViewById(R.id.idText);
//        mBtnRegister = findViewById(R.id.registerButton);
//        mBtnLogin = findViewById(R.id.loginButton);





        //회원가입 버튼 눌렀을때 이벤트
        TextView registerButton = (TextView) findViewById(R.id.registerButton);
 //       Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });






        //로그인버튼 눌렀을때 이벤트
//        final EditText idText = (EditText) findViewById(R.id.idText);
//        final  EditText passwordText = (EditText) findViewById(R.id.passwordText);
//        Button loginButton = (Button) findViewById(R.id.loginButton);
        TextView loginButton = (TextView) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String strPwd = mEtPwd.getText().toString();
                    String strId = mEtId.getText().toString();


                    mFirebaseAuth.signInWithEmailAndPassword(strId,strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                //로그인 성공
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(loginIntent);
                                Toast.makeText(LoginActivity.this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                finish(); //현재 엑티비티 파괴

                            }else {

                                Toast.makeText(LoginActivity.this, "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }catch (Exception e ){
                    Toast.makeText(LoginActivity.this, "아이디,비밀번호를 입력해 주새요.", Toast.LENGTH_SHORT).show();
                }

//                String userID= idText.getText().toString();
//                String userPassword = passwordText.getText().toString();
//                Response.Listener<String> responseLister = new Response.Listener<String>(){
//
//
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(response);
//                            boolean success = jsonResponse.getBoolean("success");
//                            if(success){
//                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                                builder.setMessage("로그인에 성공했습니다.");
//                                builder.setPositiveButton("확인",null);
//                                builder.create();
//
//                                builder.show();
//                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                                LoginActivity.this.startActivity(intent);
//                                finish();
//                            }
//                            else{
//                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
//                                builder.setMessage("계정을 다시 확인하세요.");
//                                builder.setNegativeButton("다시 시도",null);
//                                builder.create();
//
//                                builder.show();
//                            }
//                        }
//                        catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                };
//                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseLister);
//                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
//                queue.add(loginRequest);
            }
        });

         }


    }

