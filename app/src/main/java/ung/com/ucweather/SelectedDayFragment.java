package ung.com.ucweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ung on 19/10/2017.
 */

public class SelectedDayFragment extends Fragment {

    static Day selectedDay;
    static Date selectedDate;
    static String selectedCity;
    TextView date, temp;
    ImageView weather;

    public static SelectedDayFragment newInstance(Day day, Date date, String city) {

        Bundle args = new Bundle();

        SelectedDayFragment fragment = new SelectedDayFragment();
        fragment.setArguments(args);
        selectedDay = day;
        selectedDate = date;
        selectedCity = city;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_day, container, false);
        date = (TextView) view.findViewById(R.id.selected_date);
        temp = (TextView) view.findViewById(R.id.selected_temp);
        weather = (ImageView) view.findViewById(R.id.selected_weather);

        getActivity().setTitle(selectedCity);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        String formatedDate = sdf.format(selectedDate);
        date.setText(formatedDate);
        temp.setText(selectedDay.getTemperature() + " °C");
        if (selectedDay.getMeteo().equals("sunny")) {
            weather.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.sunny));
        } else if (selectedDay.getMeteo().equals("cloudy")) {
            weather.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.cloudy));
        } else if (selectedDay.getMeteo().equals("snowy")) {
            weather.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.snowy));
        }
        return view;
    }
}
