package com.bhsoft.conversation.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bhsoft.conversation.R;
import com.bhsoft.conversation.model.Account;
import com.bhsoft.conversation.utils.MediaUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupActivity extends AppCompatActivity {
    private EditText edtPassword, edtRePassword, edtUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initview();
   //     MediaUtils.getInstance().play(R.raw.nhac_mo_dau);
    }
    private void initview() {
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        edtRePassword = findViewById(R.id.edtRePassword);
    }
    public void onClickSignup(View view) {
        if (TextUtils.isEmpty(edtUsername.getText())) {

            Toast.makeText(this, "Bạn chưa nhập tài khoản", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(edtPassword.getText())) {
            Toast.makeText(this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(edtRePassword.getText())) {
            Toast.makeText(this, "Bạn chưa nhập lại mật khẩu ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!edtPassword.getText().toString().equals(edtRePassword.getText().toString())) {
            Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            edtRePassword.setText("");
            return;
        }
        FirebaseDatabase.getInstance().getReference()
                .child("Account")
                .child(edtUsername.getText().toString())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(SignupActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        signUpAccount();

                    }

                    private void signUpAccount() {
                        Account account = new Account();
                        account.setUserName(edtUsername.getText().toString());
                        account.setPassword(edtPassword.getText().toString());
                        FirebaseDatabase.getInstance().getReference()
                                .child("Account")
                                .child(edtUsername.getText().toString())
                                .setValue(account, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                        if (databaseError != null){
                                            Toast.makeText(SignupActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        Toast.makeText(SignupActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                        edtUsername.setText("");
                                        edtPassword.setText("");
                                        edtRePassword.setText("");
                                    }
                                });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}
