package com.polly.program.ui.main.video;

import com.polly.program.base.BasePresenter;
import com.polly.program.base.IBaseView;
import com.polly.program.bean.response.VideoResponse;

import java.util.List;

/**
 * @author linfeizheng
 * @date 2017/3/11 14:04
 */

public interface VideoContract {

    interface View extends IBaseView {
        void showData(List<VideoResponse.Video> responses);
    }

    interface Presenter extends BasePresenter {

        void getVideo(String source);

    }

}
