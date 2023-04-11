package com.jsp_servlet_base_dao_impl;

import com.jsp_servlet_base_dao.INewsDAO;
import com.jsp_servlet_base_mapper.NewsMapper;
import com.jsp_servlet_base_model.NewsModel;
import com.jsp_servlet_base_paging.Pageble;

import java.sql.*;
import java.util.List;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

    @Override
    public List<NewsModel> findByCategoryId(Long categoryId) {
        String sql = "select * from news where categoryid = ?";
        return query(sql, new NewsMapper(), categoryId);
    }

    @Override
    public Long save(NewsModel newsModel) {
        //String sql = "insert into news (title,content,categoryid) values (?,?,?)";//cau sql
        StringBuilder sql = new StringBuilder("insert into news (title,content,");
        sql.append(" thumbnail,shortdescription,categoryid,createddate,createdby)");
        sql.append("values (?,?,?,?,?,?,?)");
        return insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(), newsModel.getThumbnail(),
                newsModel.getShortDescription(),newsModel.getCategoryId(), newsModel.getCreateDate(), newsModel.getCreateBy());
    }

    @Override
    public NewsModel findOne(Long id) {
        String sql = "select * from news where id = ?";
        List<NewsModel> news = query(sql, new NewsMapper(), id);
        return news.isEmpty() ? null : news.get(0);
    }

    @Override
    public void update(NewsModel updateNews) {
        StringBuilder sql = new StringBuilder("update news set title =? ,thumbnail = ?,");//cau sql
        sql.append(" shortdescription = ? , content = ? ,categoryid=? ,");
        sql.append(" createddate = ? , createdby = ? , modifieddate = ? ,modifiedby = ?  where id = ? ");
        update(sql.toString(), updateNews.getTitle(), updateNews.getThumbnail(), updateNews.getShortDescription(),
                updateNews.getContent(), updateNews.getCategoryId(),
                updateNews.getCreateDate(), updateNews.getCreateBy(), updateNews.getModifiedDate(),
                updateNews.getModifiedBy(), updateNews.getId());
    }

    //viet tiep ham update va delete,ham select one (jdbs phan 5 6:21)
    //
    @Override
    public List<NewsModel> findAll(Pageble pageble) {

        // String sql = "select * from news limit ?,?";
        StringBuilder sql = new StringBuilder("select * from news");
        if (pageble.getSorter() != null) {
            sql.append(" order by " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
        }
        if (pageble.getOffset() != null && pageble.getLimit() != null) {
            sql.append(" limit " + pageble.getOffset() + "," + pageble.getLimit() + "");
        }
        return query(sql.toString(), new NewsMapper());
    }

    @Override
    public int getTotalItem() {
        String sql = "select count(*) from news";
        return count(sql);
    }

    @Override
    public void delete(long id) {
        String sql = "delete from news where id = ?";
        update(sql, id);

    }
}
//        Long id = null;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        try {
//            connection = getConnetion();//mo ket noi
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//truyen sql vao doi tuong prepa
//            preparedStatement.setString(1, newsModel.getTitle());//truyen tham so vao de luu xuong database
//            preparedStatement.setString(2, newsModel.getContent());
//            preparedStatement.setLong(3, newsModel.getCategoryId());
//            preparedStatement.executeUpdate();//truyen ham thuc thi cau sql la luu database
//            resultSet = preparedStatement.getGeneratedKeys();
//            if (resultSet.next()) {
//                id = resultSet.getLong(1);//tra ve id cua bai viet
//            }
//            connection.commit();//tat ca tac vu 1 transection xong phai commit de thay doi du lieu duoi database , ko co commit du lieu ko duoc luu xuong database
//            return id;
//        } catch (SQLException e) {
//            if (connection != null) {
//                try {
//                    connection.rollback();//khi 1 tac vu loi thi huy bo tat ca tac vu con lai ,thuc hien lai tu dau
//                } catch (SQLException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//            throw new RuntimeException(e);
//        } finally {
//            try {//dong tat ca
//                if (connection != null) {
//                    connection.close();
//                }
//                if (preparedStatement != null) {
//                    preparedStatement.close();
//                }
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//            } catch (SQLException e) {
//                return null;
//            }
//        }
//    }
//  }
