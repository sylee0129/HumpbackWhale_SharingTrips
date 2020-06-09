package com.graduate.a2020_graduateproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.graduate.a2020_graduateproject.databinding.CalendarListBinding;
import com.graduate.a2020_graduateproject.ui.adapter.CalendarAdapter;
import com.graduate.a2020_graduateproject.ui.viewmodel.CalendarListViewModel;

import java.util.ArrayList;

public class MyCalendarTripActivity extends AppCompatActivity {
    private CalendarListBinding binding;
    private CalendarAdapter calendarAdapter;
    private String selected_room_id="";
    //public static String room_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Intent intent = getIntent();
        //kakao_id = intent.getExtras().getLong("kakao_id");
        selected_room_id = intent.getExtras().getString("selected_room_id");
        //selected_room_name = intent.getExtras().getString("selected_room_name");

        CalendarListViewModel.room_id = selected_room_id;

        binding = DataBindingUtil.setContentView(this, R.layout.activity_calendar);
        binding.setVariable(BR.model, new ViewModelProvider(this).get(CalendarListViewModel.class));
        binding.setLifecycleOwner(this);

        binding.getModel().initCalendarList();

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
        calendarAdapter = new CalendarAdapter();
        binding.pagerCalendar.setLayoutManager(manager);
        binding.pagerCalendar.setAdapter(calendarAdapter);
        observe();



    }

    private void observe() {
        binding.getModel().mCalendarList.observe(this, new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                calendarAdapter.submitList(objects);
                if (binding.getModel().mCenterPosition > 0) {
                    binding.pagerCalendar.scrollToPosition(binding.getModel().mCenterPosition);
                }
            }
        });
    }
}