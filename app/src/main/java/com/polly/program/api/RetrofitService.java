package com.polly.program.api;

import com.polly.program.bean.response.BaseResponse;
import com.polly.program.bean.response.response.GankIoResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface RetrofitService {

    @GET("http://gank.io/api/data/{source}/10/{page}")
    Observable<BaseResponse<List<GankIoResponse>>> getData(@Path("source") String source, @Path("page") String page);

}
