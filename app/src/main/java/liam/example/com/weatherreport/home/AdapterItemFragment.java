package liam.example.com.weatherreport.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.dao.Day;

public class AdapterItemFragment extends Fragment {
    public static final String EXTRA_DAY = "EXTRA_DAY";
    Day day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adapter_item, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle.containsKey(EXTRA_DAY)){
            day=(Day)bundle.getSerializable(EXTRA_DAY);
        }
        Log.d("swag", "onCreate: " + day);
    }

    public static Fragment newInstance(Serializable day) {
        AdapterItemFragment adapterItemFragment = new AdapterItemFragment();
        Bundle bundle = new Bundle(1);
        bundle.putSerializable(EXTRA_DAY, day);
        adapterItemFragment.setArguments(bundle);
        return adapterItemFragment;

    }
}
