package com.polly.program.api;

import com.polly.program.bean.response.BaseResponse;
import com.polly.program.bean.response.GankIoResponse;
import com.polly.program.bean.response.VideoResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface RetrofitService {

    @GET("{source}/10/{page}")
    Observable<BaseResponse<List<GankIoResponse>>> getArticle(@Path("source") String source, @Path("page") String page);

    @GET("nc/video/list/{type}/n/0-10.html")
    Observable<Map<String, List<VideoResponse.Video>>> getVideo(@Path("type") String type);

}
