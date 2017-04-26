package com.polly.program.ui.main.meizi;

import com.polly.program.base.BasePresenter;
import com.polly.program.base.IBaseView;
import com.polly.program.bean.response.GankIoResponse;

import java.util.List;

/**
 * @author linfeizheng
 * @date 2017/3/11 14:04
 */

public interface MeiziContract {

    interface View extends IBaseView {
        void showData(List<GankIoResponse> imgUrls);
    }

    interface Presenter extends BasePresenter {

        void getData(int page);

    }

}
