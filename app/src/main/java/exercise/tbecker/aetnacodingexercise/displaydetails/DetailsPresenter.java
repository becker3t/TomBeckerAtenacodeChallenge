package exercise.tbecker.aetnacodingexercise.displaydetails;

import android.os.Bundle;

import exercise.tbecker.aetnacodingexercise.displaylist.ListActivity;

/**
 * Created by Android on 8/8/2017.
 */

public class DetailsPresenter implements DetailsContract.Presenter {

    private DetailsContract.View view;

    @Override
    public void getData(Bundle b) {
        if(b != null) {
//            view.displayData(b.getString(ListActivity.IMG_URL_TAG), b.getString(ListActivity.TITLE_TAG),
//                    b.getString(ListActivity.WIDTH_TAG), b.getString(ListActivity.HEIGHT_TAG));
            view.displayData(b.getString(ListActivity.IMG_URL_TAG), b.getString(ListActivity.TITLE_TAG));
        }
        else {
            view.ErrorDisplayingData();
        }
    }

    @Override
    public void addView(DetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
