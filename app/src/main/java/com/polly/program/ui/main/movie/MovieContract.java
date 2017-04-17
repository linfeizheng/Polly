package com.polly.program.ui.main.movie;

import com.polly.program.base.BasePresenter;
import com.polly.program.base.IBaseView;
import com.polly.program.bean.response.SubjectsResponse;

import java.util.List;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public interface MovieContract {

    interface View extends IBaseView {
        void showData(List<SubjectsResponse> responses);
    }

    interface Presenter extends BasePresenter {

        void getMovie();

    }

}
