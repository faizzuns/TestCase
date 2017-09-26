package com.example.user.testcase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.testcase.data.Member;
import com.example.user.testcase.data.Member_Table;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.Select;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edt_user) EditText edtUser;
    @BindView(R.id.edt_pass) EditText edtPass;
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.btn_regis) Button btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FlowManager.init(new FlowConfig.Builder(this).build());
        ButterKnife.bind(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUser.getText().toString();
                String pass = edtPass.getText().toString();

                checkLogin(username,pass);
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void checkLogin(String username, String pass){
        Member member = new Select()
                .from(Member.class)
                .where(Member_Table.username.is(username))
                .querySingle();

        if (member == null){
            Toast.makeText(this, "Username or Password doesn't exist", Toast.LENGTH_SHORT).show();
        }else{
            if (member.getPassword().equals(pass)){
                goToMain(member.getId());
            }else{
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void goToMain(long id) {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("idMember",id);
        Log.d("user", "goToMain: "+id);
        startActivity(intent);
        finish();
    }
}
