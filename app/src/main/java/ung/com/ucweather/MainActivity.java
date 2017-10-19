package ung.com.ucweather;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayCalendar();
    }

    private void displayCalendar() {
        CalendarFragment calendarFragment = new CalendarFragment();
        FragmentTransaction trans = this.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, calendarFragment);
        trans.commit();
    }
}
