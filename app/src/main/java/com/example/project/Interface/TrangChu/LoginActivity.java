package com.example.project.Interface.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.Database.DBHelper;
import com.example.project.Interface.MainActivityChucNang;
import com.example.project.R;

public class LoginActivity extends AppCompatActivity {
    DBHelper databaseHelper;
    EditText edtNameDN , edtPassDN;
    Button btnDangNhap,btnDangKyDN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseHelper = new DBHelper(this);
        anhXa();

        btnDangKyDN.setOnClickListener(clickButtonRegister());
        btnDangNhap.setOnClickListener(clickButtonLogin());
    }
    public void anhXa(){
        edtNameDN = (EditText) findViewById(R.id.edtNameDN);
        edtPassDN = (EditText) findViewById(R.id.etdPassDN);
        btnDangNhap=(Button) findViewById(R.id.btnDangNhap);
        btnDangKyDN =(Button) findViewById(R.id.btnDangKyDN);
    }

    @NonNull
    private View.OnClickListener clickButtonLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtNameDN.getText().toString();
                String password = edtPassDN.getText().toString();
                Boolean checkAccountUser = databaseHelper.checkAccount(username, password);
                if (checkAccount(username, password) && checkAccountUser) {
                    Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivityChucNang.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @NonNull
    private View.OnClickListener clickButtonRegister() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        };
    }

    boolean checkAccount(String username, String password){
        String userName = edtNameDN.getText().toString();
        String passWord= edtPassDN.getText().toString();
        if(username.isEmpty()){
            edtNameDN.setError("Vui lòng nhập tên người dùng");
            return false;
        }
        if(password.isEmpty()){
            edtPassDN.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        return true;
    };
}