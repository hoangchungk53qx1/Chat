package com.bhsoft.conversation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bhsoft.conversation.R;
import com.bhsoft.conversation.model.Account;
import com.bhsoft.conversation.utils.Media;
import com.bhsoft.conversation.utils.MediaUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUsername,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initview();
     //   MediaUtils.getInstance().play(R.raw.nhac_mo_dau);

    }

    private void initview() {
        edtUsername = findViewById(R.id.edtUsernamelogin);
        edtPassword = findViewById(R.id.edtPasswordlogin);
    }


    public void onClickSign(View view) {
        startActivity(new Intent(this,SignupActivity.class));
    }

    public void onClickLogin(View view) {
        if(TextUtils.isEmpty(edtUsername.getText()))
        {
            Toast.makeText(this, "Bạn chưa nhập tài khoản", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(edtPassword.getText()))
        {

            Toast.makeText(this, "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseDatabase.getInstance().getReference()
                .child("Account")
                .child(edtUsername.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists())
                        {
                            Toast.makeText(LoginActivity.this, "Tài khoản chưa tồn tại !", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Account account = dataSnapshot.getValue(Account.class);
                        if(!account.getPassword().equals(edtPassword.getText().toString()))
                        {
                            Toast.makeText(LoginActivity.this, "Mật khẩu ko đúng", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,HouseActivity.class);
                        intent.putExtra("Account",account);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    }