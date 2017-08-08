package exercise.tbecker.aetnacodingexercise.network;

import exercise.tbecker.aetnacodingexercise.models.ResponseItem;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android on 8/7/2017.
 */

public interface MyRetrofitService {

    @GET("photos_public.gne")
    Observable<ResponseItem> getData(@Query("nojsoncallback") int noJsonCallback,
                                     @Query("tagmode") String tagMode,
                                     @Query("format") String format,
                                     @Query("tags") String input);
}
