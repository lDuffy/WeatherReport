package liam.example.com.weatherreport.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.dao.Day;

public class AdapterItemFragment extends Fragment {
    public static final String EXTRA_DAY = "EXTRA_DAY";
    @BindView(R.id.temp) TextView temp;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.list) ListView listView;
    private Day day;

    public static AdapterItemFragment newInstance(Day day) {
        AdapterItemFragment adapterItemFragment = new AdapterItemFragment();
        Bundle bundle = new Bundle(1);
        bundle.putSerializable(EXTRA_DAY, day);
        adapterItemFragment.setArguments(bundle);

        return adapterItemFragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adapter_item, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        temp.setText(day.getCurrentTempCelciusString());
        description.setText(day.getCurrentDescription());
//        ListAdapter adapter = new ListViewAdapter(getContext(), day.getItems());
//        listView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle.containsKey(EXTRA_DAY)) {
            day = (Day)bundle.getSerializable(EXTRA_DAY);

        }
    }
}
