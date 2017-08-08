package exercise.tbecker.aetnacodingexercise.di;

import dagger.Component;
import exercise.tbecker.aetnacodingexercise.displaydetails.DetailsActivity;
import exercise.tbecker.aetnacodingexercise.displaylist.ListActivity;

/**
 * Created by Android on 8/7/2017.
 */

@Component(modules = {DisplayListModule.class, DisplayDetailsModule.class})
public interface DisplayListComponent {
    void inject(ListActivity listActivity);

    void inject(DetailsActivity detailsActivity);
}
