package com.example.user.testcase.data;

import com.example.user.testcase.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by User on 24/09/2017.
 */

@Table( database = MyDatabase.class)
public class Member extends BaseModel {

    @Column
    @PrimaryKey
    long id;
    @Column
    String namaLengkap;
    @Column
    String username;
    @Column
    String email;
    @Column
    String noTlp;
    @Column
    String password;

    public Member(){}

    public Member(long id, String namaLengkap, String username, String noTlp, String password,String email) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.noTlp = noTlp;
        this.password = password;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNoTlp() {
        return noTlp;
    }

    public void setNoTlp(String noTlp) {
        this.noTlp = noTlp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
