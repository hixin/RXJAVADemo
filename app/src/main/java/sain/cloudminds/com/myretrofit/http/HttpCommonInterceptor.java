package sain.cloudminds.com.myretrofit.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * created by sain on 9/10/18
 */
public class HttpCommonInterceptor implements Interceptor{
    private Map<String, String>  mHeaderParamsMap = new HashMap<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();

        //新的请求
        Request.Builder requestBuilder =  oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());

        //添加公共参数,添加到header中
        if(mHeaderParamsMap.size() > 0){
            for(Map.Entry<String,String> params : mHeaderParamsMap.entrySet()){
                requestBuilder.header(params.getKey(),params.getValue());
            }
        }

        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);

    }

    public static class Builder{
        HttpCommonInterceptor mHttpCommonInterceptor;
        public Builder() {
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value){
            mHttpCommonInterceptor.mHeaderParamsMap.put(key,value);
            return this;
        }

        public HttpCommonInterceptor build(){
            return mHttpCommonInterceptor;
        }
    }
}
