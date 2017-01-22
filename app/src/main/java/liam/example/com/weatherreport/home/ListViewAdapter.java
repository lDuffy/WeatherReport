package liam.example.com.weatherreport.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.dao.WeatherListItem;
import liam.example.com.weatherreport.utils.DateTimeUtils;

public class ListViewAdapter extends ArrayAdapter<WeatherListItem> {
    public ListViewAdapter(Context context, List<WeatherListItem> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        WeatherListItem weatherListItem = getItem(position);
        if (null == convertView) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        DateTime dateTime = new DateTime(Long.valueOf(weatherListItem.getDt() * DateTimeUtils.SECOND), DateTimeZone.UTC);
        TextView time = (TextView) convertView.findViewById(R.id.time_of_day);
        TextView temp = (TextView) convertView.findViewById(R.id.temp);
        time.setText(DateTimeUtils.getTime(dateTime.toLocalDateTime()));
        temp.setText(weatherListItem.getTempInCelcius());
        return convertView;
    }
}