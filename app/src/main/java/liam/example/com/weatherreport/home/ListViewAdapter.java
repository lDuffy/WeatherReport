package liam.example.com.weatherreport.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.dao.WeatherListItem;
import liam.example.com.weatherreport.utils.DateTimeUtils;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ListViewHolder> {

    private final List<WeatherListItem> items;
    private final Context context;
    public ListViewAdapter(Context context, List<WeatherListItem> items) {
       this.items=items;
        this.context=context;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        WeatherListItem weatherListItem = items.get(position);
        DateTime dateTime = new DateTime(Long.valueOf(weatherListItem.getDt() * DateTimeUtils.SECOND), DateTimeZone.UTC);

        holder.time.setText(DateTimeUtils.getTime(dateTime.toLocalDateTime()));
        holder.temp.setText(weatherListItem.getTempInCelcius());

    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.time_of_day) TextView time;
        @BindView(R.id.temp) TextView temp;

        ListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}