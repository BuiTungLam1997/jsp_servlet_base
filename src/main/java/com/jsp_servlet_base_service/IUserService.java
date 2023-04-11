package com.jsp_servlet_base_service;

import com.jsp_servlet_base_model.NewsModel;
import com.jsp_servlet_base_paging.Pageble;

import java.util.List;

public interface INewsService {
    List<NewsModel> findByCategoryId(Long categoryId);
    NewsModel save(NewsModel newsModel);
    NewsModel update(NewsModel updateNews);
    List<NewsModel> findAll(Pageble pageble);
    int getTotalItem();
    void delete(long[] ids);
}
