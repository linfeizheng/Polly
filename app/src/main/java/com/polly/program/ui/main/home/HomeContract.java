package com.polly.program.ui.main.home;

import com.polly.program.base.BasePresenter;
import com.polly.program.base.IBaseView;
import com.polly.program.bean.response.response.GankIoResponse;

import java.util.List;

/**
 * @author linfeizheng
 * @date 2017/3/11 14:04
 */

public interface HomeContract {

    interface View extends IBaseView {
        public void showData(List<GankIoResponse> responses);
    }

    interface Presenter extends BasePresenter {

        public void getData(String source, int page);

    }

}
