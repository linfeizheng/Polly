package com.polly.program.api;

import com.orhanobut.logger.Logger;
import com.polly.program.BuildConfig;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    public static final String BASE_URL = BuildConfig.URL_HEADER;

    private static RetrofitService mService;

    public static final String FROM = "android";
    public static final String IMEI = "866656025658982";
    public static String TOKEN;

    public static RetrofitService getInstance() {
        if (mService == null) {
            synchronized (RetrofitManager.class) {
                if (mService == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
//                    builder.addInterceptor(headerInterceptor);
//                    if (BuildConfig.DEBUG) {
//                        LoggingInterceptor logging = new LoggingInterceptor();
//                        builder.addInterceptor(logging);
//                    }
                    if (BuildConfig.DEBUG) {
                        // http请求Log信息拦截器
                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(loggingInterceptor);// 日志记录
                    }
                    builder.connectTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS);
                    OkHttpClient okHttpClient = builder.build();
                    Retrofit retrofit = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(okHttpClient)
                            .baseUrl(BASE_URL)
                            .build();
                    mService = retrofit.create(RetrofitService.class);
                }
            }

        }
        return mService;
    }

    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("from", FROM)
                    .addHeader("imei", IMEI);
            if (TOKEN != null)
                builder.addHeader("token", TOKEN);
            Request request = builder.build();
            return chain.proceed(request);
        }
    };

    static class LoggingInterceptor implements Interceptor {

        private static final Charset UTF8 = Charset.forName("UTF-8");

        @Override
        public Response intercept(Chain chain) throws IOException {
            StringBuffer requestBuffer = new StringBuffer();
            requestBuffer.append("开始请求：" + "\n");
            Request request = chain.request();
            RequestBody requestBody = request.body();
            boolean hasRequestBody = requestBody != null;

            Connection connection = chain.connection();
            Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
            String requestStartMessage = "--> " + request.method() + ' ' + request.url() + ' ' + protocol;
            requestBuffer.append(requestStartMessage + "\n");
            if (hasRequestBody) {
                if (requestBody.contentType() != null) {
                    requestBuffer.append("Content-Type: " + requestBody.contentType() + "\n");
                }
                if (requestBody.contentLength() != -1) {
                    requestBuffer.append("Content-Length: " + requestBody.contentLength() + "\n");
                }
            }
            requestBuffer.append("请求头:" + "\n");
            Headers headers = request.headers();
            for (int i = 0, count = headers.size(); i < count; i++) {
                String name = headers.name(i);
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    requestBuffer.append(name + ": " + headers.value(i) + "\n");
                }
            }
            Buffer buffer1 = new Buffer();
            requestBody.writeTo(buffer1);
            Charset charset1 = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset1 = contentType.charset(UTF8);
            }
            requestBuffer.append("参数：" + buffer1.readString(charset1) + "\n");
            requestBuffer.append("结束请求");
            Logger.i(requestBuffer.toString());

            Response response;
            try {
                response = chain.proceed(request);
            } catch (Exception e) {
                throw e;
            }
            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer2 = source.buffer();

            Charset charset2 = UTF8;
            MediaType contentType2 = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset2 = contentType2.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    return response;
                }
            }
            if (contentLength != 0) {
                Logger.json(buffer2.clone().readString(charset2));
            }
            return response;
        }
    }

}
