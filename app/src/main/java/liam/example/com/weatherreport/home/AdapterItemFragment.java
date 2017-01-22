package liam.example.com.weatherreport.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.dao.Day;

public class AdapterItemFragment extends Fragment {
    public static final String EXTRA_DAY = "EXTRA_DAY";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adapter_item, container, false);
    }

    public static Fragment newInstance(Day day) {
        AdapterItemFragment f = new AdapterItemFragment();
        Bundle bdl = new Bundle(1);
        f.setArguments(bdl);
        return f;

    }
}
