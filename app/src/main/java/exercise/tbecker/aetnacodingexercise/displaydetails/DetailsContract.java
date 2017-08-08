package exercise.tbecker.aetnacodingexercise.displaydetails;

import android.os.Bundle;

/**
 * Created by Android on 8/8/2017.
 */

public interface DetailsContract {

    interface View {
        void displayData(String imgUrl, String title);

        void ErrorDisplayingData();
    }

    interface Presenter {
        void getData(Bundle b);

        void addView(View view);

        void removeView();
    }

}
