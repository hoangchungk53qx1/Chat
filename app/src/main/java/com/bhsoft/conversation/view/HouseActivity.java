package com.bhsoft.conversation.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bhsoft.conversation.R;
import com.bhsoft.conversation.utils.Media;

public class HouseActivity extends AppCompatActivity {
    private RecyclerView recyclerRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house);
        initview();

    }

    private void initview() {
        recyclerRoom = findViewById(R.id.recyclerRoom);
    }

    public void onClickAddRoom(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View container = LayoutInflater.from(this).inflate(R.layout.dialog_add_room, null);
        builder.setView(container);
        final AlertDialog alertDialog = builder.create();

        final EditText edtUsername = container.findViewById(R.id.edtUsernamedialog);
        Button btnCreate = container.findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtUsername.getText())) {
                    Toast.makeText(HouseActivity.this, "Bạn chưa điền tên tài khoản", Toast.LENGTH_SHORT).show();
                    return;
                }

                ///////
            }
        });

        alertDialog.show();
    }
}
