package liam.example.com.weatherreport.navigation;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import liam.example.com.weatherreport.R;
import liam.example.com.weatherreport.home.MainFragment;

public class AppLauncher implements Launcher {
    AppCompatActivity context;
    public AppLauncher(AppCompatActivity context){
        this.context = context;
    }
    private void showFragment(Fragment fragment) {
        context.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void openHome() {
        showFragment(MainFragment.newInstance());
    }
}
