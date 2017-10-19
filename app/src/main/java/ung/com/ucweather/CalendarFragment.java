package ung.com.ucweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.marcohc.robotocalendar.RobotoCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ung on 19/10/2017.
 */

public class CalendarFragment extends Fragment implements RobotoCalendarView.RobotoCalendarListener {

    RobotoCalendarView robotoCalendarView;
    private ArrayList<Day> days;
    private String city;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        getActivity().setTitle("Weather Calendar");
        robotoCalendarView = (RobotoCalendarView) view.findViewById(R.id.robotoCalendarPicker);

        robotoCalendarView.setRobotoCalendarListener(this);
        robotoCalendarView.setShortWeekDays(false);
        robotoCalendarView.showDateTitle(false);
        robotoCalendarView.updateView();

        if (days == null) {
            NetworkManager.getInstance().getWeather(new NetworkManager.ResultHandler<RootObject>() {
                @Override
                public void success(RootObject result) {
                    days = result.getWeather().getDays();
                    city = result.getWeather().getCity();
                }

                @Override
                public void error(String error) {
                    Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return view;
    }

    private void displaySelectedDay(Day selectedDay, Date date) {
        SelectedDayFragment selectedDayFragment = SelectedDayFragment.newInstance(selectedDay, date, city);
        getActivity().getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.fragment_slide_in, R.anim.fragment_slide_out, R.anim.fragment_pop_in, R.anim.fragment_pop_out)
                .replace(R.id.fragment, selectedDayFragment, SelectedDayFragment.class.getSimpleName())
                .addToBackStack(SelectedDayFragment.class.getSimpleName())
                .commit();
    }

    @Override
    public void onDayClick(Calendar daySelectedCalendar) {
        displaySelectedDay(days.get(daySelectedCalendar.get(Calendar.DAY_OF_MONTH) - 1), daySelectedCalendar.getTime());
    }

    @Override
    public void onDayLongClick(Calendar daySelectedCalendar) {
    }

    @Override
    public void onRightButtonClick() {
    }

    @Override
    public void onLeftButtonClick() {
    }
}
