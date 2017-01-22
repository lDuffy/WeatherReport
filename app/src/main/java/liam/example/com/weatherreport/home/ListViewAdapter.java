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

import butterknife.BindView;
import butterknife.ButterKnife;
import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.dao.WeatherListItem;
import liam.example.com.weatherreport.utils.DateTimeUtils;

public class ListViewAdapter extends ArrayAdapter<WeatherListItem> {
    public ListViewAdapter(Context context, List<WeatherListItem> items) {
        super(context, 0, items);

    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        WeatherListItem weatherListItem = getItem(position);

        ViewHolder holder;
        if (null != view) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        DateTime dateTime = new DateTime(Long.valueOf(weatherListItem.getDt() * DateTimeUtils.SECOND), DateTimeZone.UTC);

        holder.time.setText(DateTimeUtils.getTime(dateTime.toLocalDateTime()));
        holder.temp.setText(weatherListItem.getTempInCelcius());
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.time_of_day) TextView time;
        @BindView(R.id.temp) TextView temp;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}