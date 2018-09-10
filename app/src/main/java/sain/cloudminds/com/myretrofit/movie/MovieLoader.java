package sain.cloudminds.com.myretrofit.movie;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import sain.cloudminds.com.myretrofit.http.RetrofitServiceManager;

/**
 * created by sain on 9/10/18
 */
public class MovieLoader {
    private MovieService mMovieService;

    public MovieLoader() {
        mMovieService = RetrofitServiceManager.getInstance().create(MovieService.class);
    }

    public Observable<List<Movie>> getMovie(int start, int count) {
        return  mMovieService.getTop250(start, count)
                .subscribeOn(Schedulers.io())
                .map(new Func1<MovieSubject, List<Movie>>() {
                    @Override
                    public List<Movie> call(MovieSubject movieSubject) {
                        return movieSubject.subjects;
                    }
                }).observeOn(AndroidSchedulers.mainThread());

    }

    public interface MovieService{
        @GET("top250")
        Observable<MovieSubject> getTop250(@Query("start") int start, @Query("count") int count);

        @FormUrlEncoded
        @POST("/x3/weather")
        Observable<String> getWeather(@Field("cityId") String cityId, @Field("key") String key);
    }
}
