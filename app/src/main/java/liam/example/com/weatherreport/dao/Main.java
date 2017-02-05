package liam.example.com.weatherreport.dao;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Main implements Serializable{

    public static final double KELVIN_TO_CELSIUS_SUBTRACT = 273.15;
    public double pressure;
    public double humidity;
    public double temp;
    @SerializedName("temp_min")
    public double tempMin;
    @SerializedName("temp_max")
    public double tempMax;

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


    public int getTempCelcius() {
        return (int) (temp - KELVIN_TO_CELSIUS_SUBTRACT);
    }


    public String getTempString() {
        return String.valueOf(getTempCelcius());
    }
}
