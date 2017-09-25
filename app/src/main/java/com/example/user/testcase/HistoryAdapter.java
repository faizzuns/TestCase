package com.example.user.testcase;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.testcase.data.Booking;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 25/09/2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    ArrayList<Booking> listBooking;

    public HistoryAdapter(ArrayList<Booking> listBooking) {
        this.listBooking = listBooking;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.txt_jalan) TextView txtJalan;
        @BindView(R.id.txt_tgl) TextView txtDeparture;
        @BindView(R.id.txt_seat) TextView txtSeat;
        @BindView(R.id.txt_adult) TextView txtAdult;
        @BindView(R.id.txt_child) TextView txtChild;

        public ViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_history,parent,false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        final Booking booking = listBooking.get(position);

        holder.txtJalan.setText(booking.getFrom() + " - "+ booking.getTujuan());
        holder.txtSeat.setText(booking.getSeatClass());
        holder.txtAdult.setText("Adult : "+booking.getAdult());
        holder.txtChild.setText("Child : "+booking.getChild());
        String date = booking.getDay()+" "+ getMonth(booking.getMonth())+" "+booking.getYear();
        holder.txtDeparture.setText(date);


    }

    @Override
    public int getItemCount() {
        return listBooking.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public void refreshData(ArrayList<Booking> listBooking){
        this.listBooking = listBooking;
        notifyDataSetChanged();
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
}
