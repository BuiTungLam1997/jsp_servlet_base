package com.jsp_servlet_base_service_impl;

import com.jsp_servlet_base_dao.INewsDAO;
import com.jsp_servlet_base_model.NewsModel;
import com.jsp_servlet_base_paging.Pageble;
import com.jsp_servlet_base_service.INewsService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsService implements INewsService {

    @Inject
    private INewsDAO newsDAO;

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        return newsDAO.findByCategoryId(categoryId);
    }

    @Override
    public NewsModel save(NewsModel newsModel) {
//        Long newId = newsDAO.save(newsModel);
//        System.out.println(newId);
//        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        String strDate = formatter.format(new Timestamp(System.currentTimeMillis()));
//        Timestamp date;
//        try {
//            date = (Timestamp) formatter.parse(strDate);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
        newsModel.setCreateDate(new Timestamp(System.currentTimeMillis()));
        newsModel.setCreateBy("");
        Long newId = newsDAO.save(newsModel);
        return newsDAO.findOne(newId);
    }

    @Override
    public NewsModel update(NewsModel updateNews) {
        NewsModel oldNews = newsDAO.findOne(updateNews.getId());
        updateNews.setCreateDate(oldNews.getCreateDate());
        updateNews.setCreateBy(oldNews.getCreateBy());
        updateNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        updateNews.setModifiedBy("");
        newsDAO.update(updateNews);
        return newsDAO.findOne(updateNews.getId());
    }

    @Override
    public List<NewsModel> findAll(Pageble pageble) {
        return newsDAO.findAll(pageble);
    }

    @Override
    public int getTotalItem() {
        return newsDAO.getTotalItem();
    }

    @Override
    public void delete(long[] ids) {
        for (long id : ids) {
            //1 delete comment trước
            //2 delete news
            newsDAO.delete(id);
        }
    }
}