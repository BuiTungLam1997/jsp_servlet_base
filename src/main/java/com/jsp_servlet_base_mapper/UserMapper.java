package com.jsp_servlet_base_mapper;

import com.jsp_servlet_base_model.NewsModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsMapper implements RowMapper<NewsModel> {
    @Override
    public NewsModel mapRow(ResultSet resultSets) {
        NewsModel news = new NewsModel();
        try {
            news.setId(resultSets.getLong("id"));
            news.setTitle(resultSets.getString("title"));
            news.setContent(resultSets.getString("content"));
            news.setThumbnail(resultSets.getString("thumbnail"));
            news.setCategoryId(resultSets.getLong("categoryid"));
            news.setShortDescription(resultSets.getString("shortdescription"));
            news.setCreateDate(resultSets.getTimestamp("createddate"));
            news.setCreateBy(resultSets.getString("createdby"));
            if(resultSets.getTimestamp("modifieddate")!=null){
                news.setModifiedDate(resultSets.getTimestamp("modifieddate"));
            }
            if(resultSets.getString("modifiedby")!=null){
                news.setModifiedBy(resultSets.getString("modifiedby"));
            }
            return news;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
