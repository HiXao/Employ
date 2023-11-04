package com.example.project.Interface.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Database.DBHelper;
import com.example.project.R;

public class RegisterActivity extends AppCompatActivity {

    private Button btnDangKy;
    private EditText edtNameDk, edtPassDk, edtPassDK1;
    private TextView txtHadAccount;
    private DBHelper dbHelper;
    private String username, password, confirmPass;
    private int id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbHelper = new DBHelper(this);
        findIdView();
        btnDangKy.setOnClickListener(clickToRegister());
    }

    private void findIdView() {
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        edtNameDk = (EditText) findViewById(R.id.edtNameDK);
        edtPassDk = (EditText) findViewById(R.id.edtPassDK);
        edtPassDK1 = (EditText) findViewById(R.id.edtPassDk1);
        txtHadAccount = (TextView) findViewById(R.id.txtHadAccount);
    }
    @NonNull
    private View.OnClickListener clickToRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtNameDk.getText().toString();
                password = edtPassDk.getText().toString();
                confirmPass = edtPassDK1.getText().toString();
                Boolean checkAccountRegister = checkNameUser(username) && checkPassword(password, confirmPass);
                if(id != -1 && checkAccountRegister){
                    Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
                else{
                    dbHelper.insertUser(username, password);
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        };
    }

    boolean checkNameUser(String userName){
        if(userName.isEmpty()){
            edtNameDk.setError("Vui lòng không để trống");
            return false;
        }
        if(userName.length()<3){
            edtNameDk.setError("Vui lòng lớn hơn 3 kí tự");
            return false;
        }
        return true;
    }
    boolean checkPassword(String passWord, String confirmPassword){
        if(passWord.isEmpty()){
            edtPassDk.setError("Vui lòng không để trống");
            return false;
        }
        if(passWord.length()<6){
            edtPassDk.setError("Nhập mật khẩu lớn hơn 6 kí tự");
            return false;
        }
        if(!passWord.equals(confirmPassword)){
            Toast.makeText(this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}