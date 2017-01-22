
## Synopsis

This is a test application that pulls a 5 day weather feed from the open weahter api: https://openweathermap.org/api/

Feed is saved locally to a realm database for offline access. When the user opens the app after the feed has been saved

the cached feed will load in while while waiting for the network response. Added a default retry policy of 3 attempts on the

newrork call with exponential backoff using RxJava. Also added a refresh button in the toolbar.

App consists of a MainActivity with a ViewPager and ViewPageIndicator. ViewPager contains a screen for every day of the forecast.

Archecture Is mainly MVP.


## Libraries

* Retrofit 2 : networking.
* Realm : database.
* RxJava : reactive programming.
* Frodo : Rxjava debugger.
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

* 5 day weather api : Pulls a 5 day forecast (Dublin) includes weather data every 3 hour.

## Tests

Uses Mockito and Junit4 for unit testing

