package exercise.tbecker.aetnacodingexercise.di;

import dagger.Module;
import dagger.Provides;
import exercise.tbecker.aetnacodingexercise.displaylist.ListPresenter;
import exercise.tbecker.aetnacodingexercise.network.MyRetrofitService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Android on 8/7/2017.
 */

@Module
public class DisplayListModule {

    private String baseUrl;

    public DisplayListModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    @Provides
    MyRetrofitService provideMyRetrofitService(Retrofit retrofit) {
        return retrofit.create(MyRetrofitService.class);
    }

    @Provides
    ListPresenter provideListPresenter(MyRetrofitService myRetrofitService) {
        return new ListPresenter(myRetrofitService);
    }
}
