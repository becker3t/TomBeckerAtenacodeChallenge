package exercise.tbecker.aetnacodingexercise.displaylist;

import android.os.Bundle;
import android.print.PrintAttributes;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exercise.tbecker.aetnacodingexercise.models.Item;
import exercise.tbecker.aetnacodingexercise.models.MyData;
import exercise.tbecker.aetnacodingexercise.models.ResponseItem;
import exercise.tbecker.aetnacodingexercise.network.MyRetrofitService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Android on 8/7/2017.
 */

public class ListPresenter implements ListContract.Presenter {

    private List<MyData> listMyData;

    private MyRetrofitService service;

    private ListContract.View view;

    public ListPresenter(MyRetrofitService service) {
        this.service = service;
        listMyData = new ArrayList<>();
    }

    public Bundle getDataBundle (int position) {
        Bundle myBundle = new Bundle();
        myBundle.putString(ListActivity.IMG_URL_TAG, listMyData.get(position).getImgUrl());
        myBundle.putString(ListActivity.TITLE_TAG, listMyData.get(position).getTitle());
        myBundle.putInt(ListActivity.WIDTH_TAG, listMyData.get(position).getImgWidth());
        myBundle.putInt(ListActivity.HEIGHT_TAG, listMyData.get(position).getImgHeight());
        return myBundle;
    }

    @Override
    public void populateUserList(String input) {
        if (listMyData.size() > 0) {
            listMyData.clear();
        }

        service.getData(1, "any", "json", input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseItem>() {
                    @Override
                    public void accept(@NonNull ResponseItem responseItem) throws Exception {
                        for (Item i : responseItem.getItems()) {
                            final String title = i.getTitle();
                            final int imgHeight = getImgHeight(i.getDescription());
                            final int imgWidth = getImgWidth(i.getDescription());
                            final String userImgUrl = i.getMedia().getM();

                            listMyData.add(new MyData(title, imgHeight, imgWidth, userImgUrl));
                        }

                        view.showUserList(listMyData);
                    }
                });


//        call.enqueue(new Callback<ResponseItem>() {
//            @Override
//            public void onResponse(retrofit2.Call<ResponseItem> call, retrofit2.Response<ResponseItem> response) {
//
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<ResponseItem> call, Throwable t) {
//                //do stuff on failure
//                view.showNetworkErrorMessage();
//            }
//        });
    }

    private int getImgHeight(String desc) {
//        String regex = "height=\\\"d+\\\"";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(desc);
//        Log.d("HELLO", "getImgHeight: " + matcher.group(1));
        return 1;
    }

    private int getImgWidth(String desc) {
        return 1;
    }

    @Override
    public void addView(ListContract.View view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
