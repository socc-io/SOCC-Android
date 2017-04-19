package com.socc.android.soccapp.study;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.socc.android.soccapp.R;
import com.socc.android.soccapp.utills.customview.EventDecorator;
import com.socc.android.soccapp.utills.customview.MySelectorDecorator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ksang on 2017-04-01.
 */
public class StudyFragment extends Fragment implements StudyContract.View {
    private StudyContract.Presenter mPresenter;
    private LinearLayout mStudyView;

    @BindView(R.id.calendarView)
    MaterialCalendarView mMaterCalendarView;

    public StudyFragment() {
        // 프래그먼트 생성자.
    }

    public static StudyFragment newInstance() {
        return new StudyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_study, container, false);
        ButterKnife.bind(this, root);

        mMaterCalendarView.setCurrentDate(new Date());
        mMaterCalendarView.setSelected(true);
        ArrayList<CalendarDay> days =new ArrayList<CalendarDay>();
        days.add(CalendarDay.from(2017, 3, 21));
        days.add(CalendarDay.from(2017, 5, 3));
        days.add(CalendarDay.from(2017, 7, 3));

        mMaterCalendarView.addDecorators(
                new MySelectorDecorator(this.getActivity()),
                new EventDecorator(Color.BLUE,days)
        );

        OnDateSelectedListener listener = new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                CalendarDay day= widget.getSelectedDate();

                Log.i("widget",date.toString());
            }
        };
        mMaterCalendarView.setOnDateChangedListener(listener);
        mMaterCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);
        mMaterCalendarView.setSelectionColor(R.drawable.selector);

        String textDate = "2017-04-22";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = format.parse(textDate);
            //mMaterCalendarView.setDateSelected(date,true);
            mMaterCalendarView.setSelectedDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.mPresenter.loadStudys();
        setHasOptionsMenu(false);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(StudyContract.Presenter presenter) {
        if(presenter !=null){
            Log.i("음 셋","프래젠또");
            mPresenter =presenter;
        }
    }

    @Override
    public void showStudy(ArrayList<Study> studies) {
        Log.i("스터디 왔쪄","ㅇㅇ");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void attemptSignUp() {

    }

}
