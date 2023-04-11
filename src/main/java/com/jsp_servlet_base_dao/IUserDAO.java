package com.jsp_servlet_base_dao;

import com.jsp_servlet_base_model.NewsModel;
import com.jsp_servlet_base_paging.Pageble;

import java.util.List;

public interface INewsDAO extends GenericDAO<NewsModel>{
    List<NewsModel> findByCategoryId(Long categoryId);
    Long save(NewsModel newsModel);
    NewsModel findOne(Long id);
    void update(NewsModel newsModel);
    List<NewsModel> findAll(Pageble pageble);
    int getTotalItem();
    void delete(long id);
}
