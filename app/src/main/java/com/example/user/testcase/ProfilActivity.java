package com.example.user.testcase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.user.testcase.data.Member;
import com.example.user.testcase.data.Member_Table;
import com.raizlabs.android.dbflow.sql.language.Select;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfilActivity extends AppCompatActivity {

    @BindView(R.id.nama_profil)
    TextView txtNama;
    @BindView(R.id.email_profil) TextView txtEmail;
    @BindView(R.id.nomor_profil) TextView txtNomor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Profile");
        ButterKnife.bind(this);

        long id = getIntent().getLongExtra("id",0);

        Member member = new Select()
                .from(Member.class)
                .where(Member_Table.id.is(id))
                .querySingle();

        txtNama.setText(member.getNamaLengkap());
        txtEmail.setText(member.getEmail());
        txtNomor.setText(member.getNoTlp());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            goToHome();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToHome() {
        finish();
    }
}
