package com.sample.mainactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayAdapter adapter;  //전공과목 adapter
    private Spinner spinner;   //전공과목 spinner
    private FirebaseAuth mFirebaseAuth; //파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; //실시간 데이터베이스
    private EditText mEtPwd, mEtId; //회원가입 입력필드
    private Button mBtnRegister; //회원가입 버튼
    private RadioButton male,female; //성별 라디오버튼
    int i;
    private String item;  //선택된 전공과목 저장 할 변수


    //전공과목 선택
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        item = spinner.getSelectedItem().toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //학과
        spinner = (Spinner) findViewById(R.id.majorSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Android_Login");

        mEtPwd = findViewById(R.id.passwordText);
        mEtId = findViewById(R.id.idText);
        mBtnRegister = findViewById(R.id.registerButton);
        male = findViewById(R.id.genderMan);
        female = findViewById(R.id.genderWoman);

        //데이터베이스에 값 추가
//        mDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()){
//                    i = (int) snapshot.getChildrenCount();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        //가입하기 버튼을 클릭했을 때 이벤트
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    //회원가입 처리시작
                    String strPwd = mEtPwd.getText().toString();
                    String strId = mEtId.getText().toString();
                    String strmale = male.getText().toString();
                    String strfemale = female.getText().toString();


                    //FireBase Auth진행
                    mFirebaseAuth.createUserWithEmailAndPassword(strId,strPwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {


                            if(task.isSuccessful()){

                                //Firebase에 UserAccount데이터 저장
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
                                UserAccount account  = new UserAccount();
                                account.setIdToekn(firebaseUser.getUid());
                                account.setUserId(firebaseUser.getEmail());
                                account.setPassword(strPwd);
                                account.setMajor(item);



                                //남자,여자 성별 구분해서 데이터베이스에 저장하기
                                if(male.isChecked()){
                                    account.setGender(strmale);
//                                    mDatabaseRef.child(String.valueOf(i+1)).setValue(account);
                                }else if (female.isChecked()){
                                    account.setGender(strfemale);
//                                    mDatabaseRef.child(String.valueOf(i+1)).setValue(account);
                                }




                                //setValue = database()에 insert (삽입) 행위
                                mDatabaseRef.child("userAccount").child(firebaseUser.getUid()).setValue(account);

                                Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(registerIntent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
         catch (Exception e){

             Toast.makeText(RegisterActivity.this, "회원정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }


//        Button registerButton = findViewById(R.id.registerButton);
//        registerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendRequest();
//            }
//        });
//
//
//        if(AppHelper.requestQueue != null) {
//            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
//        }
//    }
//
//    public void sendRequest() {
//        String url = "http://192.168.26.76:8080/get";
//        StringRequest request = new StringRequest(
//                Request.Method.GET,
//                url,
//                new Response.Listener<String>() { //응답을 잘 받았을 때 이 메소드가 자동으로 호출
//                    @Override
//                    public void onResponse(String response) {
//                        println("응답 -> " + response);
//                    }
//                },
//                new Response.ErrorListener() { //에러 발생시 호출될 리스너 객체
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        println("에러 -> " + error.getMessage());
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String,String>();
//
//                return params;
//            }
//        };
//        request.setShouldCache(false); //이전 결과 있어도 새로 요청하여 응답을 보여준다.
//        AppHelper.requestQueue = Volley.newRequestQueue(this); // requestQueue 초기화 필수
//        AppHelper.requestQueue.add(request);
//        println("요청 보냄.");
//
//    }
//
//    public void println(String data) {
//        Log.e("test", data);
//    }

            };

        });
    };

}