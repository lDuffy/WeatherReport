
## Synopsis

Test application that pulls a 5 day weather feed from the open weahter api: https://openweathermap.org/api/.

Feed saved locally to a realm database for offline access. When user opens the app after feed has been saved the cached feed will load in while while waiting for the network response.

Added a default retry policy of 3 attempts on the newrork call with exponential back-off using RxJava. Also added a refresh button in the toolbar.

App consists of a MainActivity with a ViewPager and ViewPageIndicator. ViewPager contains a screen for every day of the forecast.

Archecture Is mainly MVP.

<img src="https://cloud.githubusercontent.com/assets/4758595/22187230/676af0ce-e0fa-11e6-91bb-813fec877901.png" align="middle" width="250">



## Libraries

* Retrofit 2 : networking.
* RxJava : reactive programming.
* Mockito/Junit : testing.
* ButterKnife : view injection.
* Dagger2 : dependency injection.
* Gson : json serialization.
* JodaTime : date time parser.
* ViewPagerIndicator : view page indicator.


## Installation

Clone project. Open in Android Studio. build.

Note: also added a debug-build.apk in app/apk dir.

## API Reference

* 5 day weather api : Pulls a 5 day forecast based on users location includes weather data every 3 hour.

## Tests

Uses Mockito and Junit4 for unit testing. Created test dagger components/modules to mock injected code.

## Further Work
Given more time would like to include kotlin to reduce boiler plate code. I would Like to incluse Espresso for UI tests.

