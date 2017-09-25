package com.example.user.testcase.data;

import com.example.user.testcase.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Date;

/**
 * Created by User on 24/09/2017.
 */

@Table( database = MyDatabase.class)
public class Booking extends BaseModel{

    @Column
    @PrimaryKey
    long idBook;
    @Column
    long idMember;
    @Column
    String from;
    @Column
    String tujuan;
    @Column
    String seatClass;
    @Column
    int adult;
    @Column
    int child;
    @Column
    int day;
    @Column
    int month;
    @Column
    int year;


    public Booking(){}

    public Booking(long idBook, long idMember, String from, String tujuan, String seatClass, int adult, int child, int day, int month, int year) {
        this.idBook = idBook;
        this.idMember = idMember;
        this.from = from;
        this.tujuan = tujuan;
        this.seatClass = seatClass;
        this.adult = adult;
        this.child = child;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
    }

    public long getIdMember() {
        return idMember;
    }

    public void setIdMember(long idMember) {
        this.idMember = idMember;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }
}
