package exercise.tbecker.aetnacodingexercise;

import android.app.Application;
import android.support.annotation.NonNull;

import exercise.tbecker.aetnacodingexercise.di.DaggerDisplayListComponent;
import exercise.tbecker.aetnacodingexercise.di.DisplayListComponent;
import exercise.tbecker.aetnacodingexercise.di.DisplayListModule;

/**
 * Created by user on 8/8/17.
 */

public class App extends Application {
    private DisplayListComponent displayListComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        displayListComponent = DaggerDisplayListComponent.builder()
                .displayListModule(new DisplayListModule(getBaseUrl()))
                .build();
    }

    @NonNull
    private String getBaseUrl() {
        return getString(R.string.base_url);
    }

    public DisplayListComponent getDisplayListComponent() {
        return displayListComponent;
    }
}
