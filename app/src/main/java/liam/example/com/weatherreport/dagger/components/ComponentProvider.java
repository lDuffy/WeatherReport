package liam.example.com.weatherreport.dagger.components;

@FunctionalInterface
public interface ComponentProvider<T> {
    T getComponent();
}
