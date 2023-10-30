/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Contact.ContactDB;
import Model.News;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class NewsDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ArrayList<News> getAllNews() throws ClassNotFoundException, SQLException {
        ArrayList<News> newsArrayList = new ArrayList<>();
        String sql = "select * from news order by News_ID desc;";
        connection = new ContactDB().makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newsArrayList.add(new News(
                    resultSet.getInt("News_ID"),
                    resultSet.getInt("day"),
                    resultSet.getString("month"),
                    resultSet.getString("title"),
                    resultSet.getString("scriptShort"),
                    resultSet.getString("scriptFull"),
                    resultSet.getString("image")));
        }
        return newsArrayList;
    }

    public ArrayList<News> searchNews(String name) throws ClassNotFoundException, SQLException {
        ArrayList<News> newsArrayList = new ArrayList<>();
        String sql = "SELECT * FROM News WHERE [Day] LIKE ? OR [Month] LIKE ? OR Title LIKE ? OR ScriptShort LIKE ?";
        connection = new ContactDB().makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + name + "%");
        preparedStatement.setString(2, "%" + name + "%");
        preparedStatement.setString(3, "%" + name + "%");
        preparedStatement.setString(4, "%" + name + "%");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newsArrayList.add(new News(
                    resultSet.getInt("News_ID"),
                    resultSet.getInt("day"),
                    resultSet.getString("month"),
                    resultSet.getString("title"),
                    resultSet.getString("scriptShort"),
                    resultSet.getString("scriptFull"),
                    resultSet.getString("image")));
        }
        return newsArrayList;
    }

    public News getNewsByID(String newsid) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM news where News_ID = ?";
        try {
            connection = new ContactDB().makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newsid);
            resultSet = preparedStatement.executeQuery();
            News news = null;
            while (resultSet.next()) {
                news = new News(
                        resultSet.getInt("News_ID"),
                        resultSet.getInt("day"),
                        resultSet.getString("month"),
                        resultSet.getString("title"),
                        resultSet.getString("scriptShort"),
                        resultSet.getString("scriptFull"),
                        resultSet.getString("image"));
            }
            return news;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createNews(int day, String month, String title, String scriptshort, String scriptfull, String image) {
        String sql = "Insert into news(day, month, title, scriptshort, scriptfull, image) values(?, ?, ?, ?, ?,?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, day);
            preparedStatement.setString(2, month);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, scriptshort);
            preparedStatement.setString(5, scriptfull);
            preparedStatement.setString(6, image);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteNews(int newsid) {
        String sql = "DELETE FROM news WHERE News_ID = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newsid);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateNewsNoImg(int newsid, int day, String month, String title, String scriptshort, String scriptfull) {
        try {
            String sql = "UPDATE news set Day = ?, Month = ?, Title = ?, ScriptShort = ?, ScriptFull = ? where News_ID = ? ;";
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, day);
            preparedStatement.setString(2, month);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, scriptshort);
            preparedStatement.setString(5, scriptfull);
            preparedStatement.setInt(6, newsid);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateNewsImg(int newsid, int day, String month, String title, String scriptshort, String scriptfull, String image) {
        try {
            String sql = "update news set Day = ?, Month = ?, Title = ?, ScriptShort = ?, ScriptFull = ?, Image = ? where News_ID = ? ;";
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, day);
            preparedStatement.setString(2, month);
            preparedStatement.setString(3, title);
            preparedStatement.setString(4, scriptshort);
            preparedStatement.setString(5, scriptfull);
            preparedStatement.setString(6, image);
            preparedStatement.setInt(7, newsid);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        NewsDao a = new NewsDao();
        a.deleteNews(1);
    }

}
