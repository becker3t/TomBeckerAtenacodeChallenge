package exercise.tbecker.aetnacodingexercise.di;

import dagger.Module;
import dagger.Provides;
import exercise.tbecker.aetnacodingexercise.displaydetails.DetailsPresenter;

/**
 * Created by Android on 8/8/2017.
 */

@Module
public class DisplayDetailsModule {

    @Provides
    DetailsPresenter provideDetailsPresenter (){
        return new DetailsPresenter();
    }
}
