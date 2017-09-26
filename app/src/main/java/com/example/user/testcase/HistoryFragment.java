package com.example.user.testcase;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.testcase.data.Booking;
import com.example.user.testcase.data.Booking_Table;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    @BindView(R.id.rv_history)
    RecyclerView rvHistory;

    ArrayList<Booking> listBooking = new ArrayList<>();
    HistoryAdapter adapter;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_history, container, false);

        ButterKnife.bind(this,v);

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rvHistory.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rvHistory.setLayoutManager(llm);

        adapter = new HistoryAdapter(listBooking);
        rvHistory.setAdapter(adapter);
        callBooking();
        Log.d("TEST", "HISTORY ACTIVITY CREATED");
    }

    public void callBooking() {

        long idMember = ((MainActivity)getActivity()).getIdMember();
        listBooking = new ArrayList<>();

        List<Booking> searchBooking = new Select()
                .from(Booking.class)
                .where(Booking_Table.idMember.is(idMember))
                .queryList();

        if (searchBooking.size() != 0){
            for (Booking book : searchBooking){
                listBooking.add(book);
            }
        }

        Collections.reverse(listBooking);
        adapter.refreshData(listBooking);

    }

    @Override
    public void onResume() {
        Log.d("cekfrag", "frag2");
        super.onResume();
    }
}
