package liam.example.com.weatherreport.base;

import android.support.v4.app.Fragment;

import liam.example.com.weatherreport.dagger.components.ComponentProvider;

/**
 * Any fragment intending on using dependency injection inherits from this class.
 * InjectedFragment gets the ActivityComponent from getComponent() and and builds
 * its object graph by simply calling getComponent().inject(this)
 */

public abstract class InjectedFragment<T> extends Fragment implements ComponentProvider<T> {

    @Override
    public T getComponent() {
        ComponentProvider<T> componentProvider = (ComponentProvider<T>) getActivity();
        return componentProvider.getComponent();
    }

}
