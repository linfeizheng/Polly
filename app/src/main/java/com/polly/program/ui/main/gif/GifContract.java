package com.polly.program.ui.main.gif;

import com.polly.program.base.BasePresenter;
import com.polly.program.base.IBaseView;
import com.polly.program.bean.response.response.GankIoResponse;
import com.polly.program.bean.response.response.JuejinResponse;

import java.util.List;

/**
 * @author linfeizheng
 * @date 2017/3/11 14:04
 */

public interface GifContract {

    interface View extends IBaseView {
        public void showData(List<GankIoResponse> imgUrls);
    }

    interface Presenter extends BasePresenter {

        public void getData(int page);

    }

}
