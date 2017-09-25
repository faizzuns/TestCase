package com.example.user.testcase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.testcase.data.Member;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.regis_nama)
    EditText edtNama;
    @BindView(R.id.regis_user) EditText edtUsername;
    @BindView(R.id.regis_email) EditText edtEmail;
    @BindView(R.id.regis_tlp) EditText edtTlp;
    @BindView(R.id.regis_pass) EditText edtPass;
    @BindView(R.id.regis_confirm) EditText edtConfirm;
    @BindView(R.id.register)
    Button btnRegis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegistrasi();
            }
        });

    }

    private void checkRegistrasi() {

        String nama = edtNama.getText().toString();
        String usermname = edtUsername.getText().toString();
        String email = edtEmail.getText().toString();
        String telepon = edtTlp.getText().toString();
        String pass = edtPass.getText().toString();
        String passConfirm = edtConfirm.getText().toString();

        if (TextUtils.isEmpty(edtNama.getText()) | TextUtils.isEmpty(edtUsername.getText()) | TextUtils.isEmpty(edtEmail.getText()) | TextUtils.isEmpty(edtTlp.getText()) | TextUtils.isEmpty(edtPass.getText()) | TextUtils.isEmpty(edtConfirm.getText())){
            Toast.makeText(this, "Form must completed", Toast.LENGTH_SHORT).show();
        }else if (!isEmailValid(email)){
            Toast.makeText(this, "email doesn't exist", Toast.LENGTH_SHORT).show();
        }else if (!pass.equals(passConfirm)){
            Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
        }else{
            long id = System.currentTimeMillis();

            Member member = new Member(id,nama,usermname,telepon,pass,email);
            member.save();

            finish();
            Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        }

    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();}
}
