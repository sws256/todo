package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class FriendToDoActivity extends AppCompatActivity {
    MaterialCalendarView materialCalendarView;
    private List<List> friendToDoList;

    public static FriendToDoActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_friend_to_do);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name"); //선택한친구이름가져오기
        String uid = intent.getStringExtra("UID");


        Toolbar myToolbar = (Toolbar) findViewById(R.id.friendtodo_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle(name);



        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //CalendarDay -> String으로 변환
                String stringdate = date.toString().replace("CalendarDay{","").replace("}","");
                String[] strdate = stringdate.split("-");

                String sdate = strdate[0];
                if(strdate[1].length() == 1)
                    sdate += "0";
                sdate += strdate[1];

                if(strdate[2].length() == 1)
                    sdate += "0";
                sdate += strdate[2];


                System.out.println("-----------------------------get");
                getFriendToDoList(uid, Integer.parseInt(sdate));


            }
        });

    }

    private void getFriendToDoList(String friendUID, int sdate) {
        FirebaseDBHelper firebaseDB = new FirebaseDBHelper();
        firebaseDB.loadFriendToDoList(friendUID, sdate);
    }

    public void notifyFriendToDoList(List<List> list) {
//        friendAdapter.setData(list);
        friendToDoList = list;
//        friendAdapter.notifyDataSetChanged();
        System.out.println("----notify friend to do ------------");

        List<LectureInfo> lectureInfos = list.get(0);
        for(LectureInfo info: lectureInfos)
            System.out.println(info.getSubjectName());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.friendtodo_menu, menu); //툴바에 ㅇ메뉴 설정
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_month:
                if(item.getTitle()=="주별"){
                    item.setTitle("월별");
                    materialCalendarView.state().edit().setCalendarDisplayMode(CalendarMode.MONTHS).commit();
                }
                else{
                    item.setTitle("주별");
                    materialCalendarView.state().edit().setCalendarDisplayMode(CalendarMode.WEEKS).commit();
                }
        }

        return super.onOptionsItemSelected(item);
    }
}