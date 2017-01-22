package liam.example.com.weatherreport.navigation;

import android.support.v7.app.AppCompatActivity;

public class AppLauncher implements Launcher {
    AppCompatActivity context;
    public AppLauncher(AppCompatActivity context){
        this.context = context;
    }


    @Override
    public void openHome() {

    }
}
