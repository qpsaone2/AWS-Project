package com.sample.mainactivity;


//사용자 계정 정보 모델 클래스
public class UserAccount {

    private String UserId;  //Firebase Uid (고유 토큰정보)
    private String password; //이메일 아이디
    private String idToekn; //비밀번호

    public UserAccount() { }


    public String getIdToekn() {
        return idToekn;
    }

    public void setIdToekn(String idToekn) {
        this.idToekn = idToekn;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
