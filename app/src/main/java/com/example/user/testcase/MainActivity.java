package com.example.user.testcase;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.example.user.testcase.data.Booking;
import com.example.user.testcase.data.Member;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pager)
    ViewPager pager;

    long idMember;

    static int idBookingFragment;
    static int idHistoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("TestCase");
        ButterKnife.bind(this);
        FlowManager.init(new FlowConfig.Builder(this).build());

        idMember = getIntent().getLongExtra("idMember",0);

        pager.setAdapter(new TabFragmentPagerAdapter(getSupportFragmentManager()));
        if(getIntent() != null && getIntent().getAction() == "notify") {
            Log.d("TEST", "GO TO HISTORY");
            pager.setCurrentItem(1);
        }
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1){
                    HistoryFragment fragment = (HistoryFragment) ((TabFragmentPagerAdapter)pager.getAdapter()).getHistoryFragment();
                    fragment.callBooking();
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1){
                    HistoryFragment fragment = (HistoryFragment) ((TabFragmentPagerAdapter)pager.getAdapter()).getHistoryFragment();
                    fragment.callBooking();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabs.setTabTextColors(getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(android.R.color.white));
        tabs.setupWithViewPager(pager);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_profil :
                Intent intent = new Intent(getApplicationContext(),ProfilActivity.class);
                intent.putExtra("id",idMember);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private DatePickerDialog.OnDateSetListener DateTanggalLahirListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


            Log.d("apaya", "onGetData1: ");

            BookingFragment fragment = (BookingFragment) ((TabFragmentPagerAdapter) pager.getAdapter()).getBookFragment();
            fragment.getDataDeparture(i,i1,i2);
        }
    };

    //untuk metode memanggil calendar
    @Override
    protected Dialog onCreateDialog(int id) {

        Calendar calendar = Calendar.getInstance();

        if (id == 999){
            return new DatePickerDialog(this,
                    DateTanggalLahirListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        }
        return super.onCreateDialog(id);
    }

    public Booking setting(long id,String from, String tujuan,String seatClass, int day,int month,int year, int adult, int child){
        return new Booking(id, idMember, from, tujuan, seatClass,adult,child,day,month,year);
    }

    public long getIdMember() {
        return idMember;
    }

    public void goToHis(){
        pager.setCurrentItem(1);
    }
}
