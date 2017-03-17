package com.llf.common.ui.news;

import com.llf.common.api.Apis;
import com.llf.common.entity.NewsEntity;
import java.util.List;

/**
 * Created by llf on 2017/3/15.
 * 新闻Presenter
 */

public class NewsPresenter implements NewsContract.Presenter, NewsModel.OnLoadFirstDataListener {
    private NewsContract.View view;
    private NewsContract.Model model;

    public NewsPresenter(NewsContract.View view) {
        this.view = view;
        this.model = new NewsModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void loadData(int type, int page) {
        String url = getUrl(type, page);
        view.showLoading();
        model.loadData(url, type, this);
    }

    @Override
    public void onSuccess(List<NewsEntity> list) {
        view.stopLoading();
        view.returnData(list);
    }

    @Override
    public void onFailure(String str, Exception e) {
        view.stopLoading();
        view.showErrorTip(str);
    }

    private String getUrl(int type, int page) {
        StringBuilder sb=new StringBuilder();
        switch (type){
            case NewsFragment.ONE:
                sb.append(Apis.TOP_URL).append(Apis.TOP_ID);
                break;
            case NewsFragment.TWO:
                sb.append(Apis.COMMON_URL).append(Apis.NBA_ID);
                break;
            case NewsFragment.THREE:
                sb.append(Apis.COMMON_URL).append(Apis.CAR_ID);
                break;
            case NewsFragment.FOUR:
                sb.append(Apis.COMMON_URL).append(Apis.JOKE_ID);
                break;
            default:
                sb.append(Apis.TOP_URL).append(Apis.TOP_ID);
                break;
        }
        sb.append("/").append(page).append(Apis.END_URL);
        return sb.toString();
    }
}
