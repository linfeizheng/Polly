package com.polly.program.ui.moive;

import com.polly.program.base.BasePresenter;
import com.polly.program.base.IBaseView;
import com.polly.program.bean.response.MovieDetailResponse;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public interface MovieDetailContract {

    interface View extends IBaseView {
        public void showData(MovieDetailResponse response);
    }

    interface Presenter extends BasePresenter {

        public void getMovieDetail(String id);

    }

}
