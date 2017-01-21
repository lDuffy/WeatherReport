package liam.example.com.weatherreport.dao;

import io.realm.RealmObject;

public class Main extends RealmObject {

    private static final double KELVIN_TO_CELSIUS_SUBTRACT = 273.15;
    private double temp;
    private double pressure;
    private double humidity;
    private double tempMin;
    private double tempMax;

    public double getTemp() {
        return temp;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public String getHumidityString() {
        return String.valueOf(humidity);
    }

    public String getPressureString() {
        return String.valueOf(pressure);
    }


    public int getTempCelcius(){
        return (int)(temp - KELVIN_TO_CELSIUS_SUBTRACT);
    }


    public String getTempString() {
        return String.valueOf(getTempCelcius());
    }
}
