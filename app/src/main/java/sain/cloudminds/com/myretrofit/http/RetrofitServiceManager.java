package sain.cloudminds.com.myretrofit.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sain.cloudminds.com.myretrofit.ApiConfig;

/**
 * created by sain on 9/10/18
 */
public class RetrofitServiceManager {
    private static final int DEFAULT_CONNECT_TIME_OUT = 5;
    private static final int DEFAULT_WR_TIME_OUT = 10;

    private Retrofit mRetrofit;

    private RetrofitServiceManager() {
        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_WR_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_WR_TIME_OUT, TimeUnit.SECONDS);

        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("platform","android")
                .addHeaderParams("userToken","1234343434dfdfd3434")
                .addHeaderParams("userId","123445")
                .build();
        builder.addInterceptor(commonInterceptor);


        // 创建Retrofit
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .build();
    }

    private static class SingletonHolder {
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }
}
