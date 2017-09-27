package com.example.user.testcase;


import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.testcase.data.Booking;
import com.example.user.testcase.data.Member;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.ALARM_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    @BindView(R.id.spinner_from)
    Spinner spinnerFrom;
    @BindView(R.id.spinner_to) Spinner spinnerTo;
    @BindView(R.id.spinner_class) Spinner spinnerClass;
    @BindView(R.id.txt_departure)
    TextView txtDeparture;
    @BindView(R.id.picker_adult)
    NumberPicker pickerAdult;
    @BindView(R.id.picker_child) NumberPicker pickerChild;
    @BindView(R.id.btn_book)
    Button btnBook;

    ArrayList<String> listFrom,listTo,listSeat;

    int idxFrom = 0;
    int idxTo = 0;
    int idxSeat = 0;
    String departure;
    boolean cek = false;
    int day;
    int month;
    int year;


    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_booking, container, false);

        ButterKnife.bind(this,v);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        UploadSpinnerFrom();

        UploadSpinnerTo();

        UploadSpinnerSeat();

        UploadDataNumberPicker();

        txtDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).showDialog(999);
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (idxFrom == idxTo){
                    Toast.makeText(getContext(), "Asal dan Tujuan tidak boleh sama", Toast.LENGTH_SHORT).show();
                }else if (!cek){
                    Toast.makeText(getContext(), "Tanggal masih kosong", Toast.LENGTH_SHORT).show();
                }else if (pickerAdult.getValue() == 0 && pickerChild.getValue() == 0){
                    Toast.makeText(getContext(), "Quantity masih kosong", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                    long id = System.currentTimeMillis();
                    Booking book = ((MainActivity)getActivity()).setting(id,listFrom.get(idxFrom),listTo.get(idxTo),listSeat.get(idxSeat),day,month,year,pickerAdult.getValue(),pickerChild.getValue());
                    book.save();

                    cek = false;
                    txtDeparture.setText("dd/mm/yyyy");
                    pickerChild.setValue(0);
                    pickerAdult.setValue(0);
                    
                    setNotifications(book);

                    ((MainActivity)getActivity()).goToHis();
                }
            }
        });

    }

    private void setNotifications(Booking booking) {


    }

    private void UploadDataNumberPicker() {

        pickerAdult.setMinValue(0);
        pickerAdult.setMaxValue(10);

        pickerChild.setMinValue(0);
        pickerChild.setMaxValue(10);
    }

    private void UploadSpinnerSeat() {
        listSeat = new ArrayList<>();

        listSeat.add("Ekonomi");
        listSeat.add("Bisnis");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listSeat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerClass.setAdapter(adapter);
        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idxSeat = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void UploadSpinnerTo() {
        listTo = new ArrayList<>();

        listTo.add("Bandung");
        listTo.add("Jakarta");
        listTo.add("Surabaya");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listTo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTo.setAdapter(adapter);
        spinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idxTo = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void UploadSpinnerFrom() {

        listFrom = new ArrayList<>();

        listFrom.add("Bandung");
        listFrom.add("Jakarta");
        listFrom.add("Surabaya");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, listFrom);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idxFrom = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getDataDeparture(int x,int y,int z){
        cek = true;
        Log.d("apaya", "getDataDeparture: "+x+ " " +(y+1)+ " " +z);
        departure = z+" - "+getMonth(y)+" - "+x;
        day = z;
        month = y;
        year = x;
        Log.d("apaya", "getDataDeparture: "+departure);

        txtDeparture.setText(departure);
    }
    private String getMonth(int startMonth) {
        String month;

        switch (startMonth){
            case 0:
                month = "Januari";
                break;
            case 1:
                month = "Februari";
                break;
            case 2:
                month = "Maret";
                break;
            case 3:
                month = "April";
                break;
            case 4:
                month = "Mei";
                break;
            case 5:
                month = "Juni";
                break;
            case 6:
                month = "Juli";
                break;
            case 7:
                month = "Agustus";
                break;
            case 8:
                month = "September";
                break;
            case 9:
                month = "Oktober";
                break;
            case 10:
                month ="November";
                break;
            case 11:
                month ="Desember";
                break;
            default:
                month = "";
                break;
        }

        return month;
    }

    @Override
    public void onResume() {
        Log.d("cekfrag", "frag1");
        super.onResume();
    }
}
