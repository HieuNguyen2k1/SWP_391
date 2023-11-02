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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author HP
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
                    resultSet.getString("time"),
                    resultSet.getString("title"),
                    resultSet.getString("scriptShort"),
                    resultSet.getString("scriptFull"),
                    resultSet.getString("image")));
        }
        return newsArrayList;
    }

    public ArrayList<News> searchNews(String name) throws ClassNotFoundException, SQLException {
        ArrayList<News> newsArrayList = new ArrayList<>();
        String sql = "SELECT * FROM news WHERE [Time] LIKE ? OR  Title LIKE ? OR ScriptShort LIKE ?";
        connection = new ContactDB().makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + name + "%");
        preparedStatement.setString(2, "%" + name + "%");
        preparedStatement.setString(3, "%" + name + "%");
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newsArrayList.add(new News(
                    resultSet.getInt("News_ID"),
                    resultSet.getString("time"),
                    resultSet.getString("title"),
                    resultSet.getString("scriptShort"),
                    resultSet.getString("scriptFull"),
                    resultSet.getString("image")));
        }
        return newsArrayList;
    }

    public News getNewsByID(String nid) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM news where News_ID = ?";
        try {
            connection = new ContactDB().makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nid);
            resultSet = preparedStatement.executeQuery();
            News news = null;
            while (resultSet.next()) {
                news = new News(
                        resultSet.getInt("News_ID"),
                        resultSet.getString("time"),
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

    public boolean createNews(String time, String title, String scriptshort, String scriptfull, String image) {

        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        
        String sql = "Insert into news(time, title, scriptshort, scriptfull, image) values(?, ?, ?, ?, ?)";

        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, formattedDate);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, scriptshort);
            preparedStatement.setString(4, scriptfull);
            preparedStatement.setString(5, image);
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

    public boolean UpdateNewsNoImg(int id, String time, String title, String scriptshort, String scriptfull) {
        
        try {
            String sql = "UPDATE news set Time = ?, Title = ?, ScriptShort = ?, ScriptFull = ? where News_ID = ? ;";
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, time);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, scriptshort);
            preparedStatement.setString(4, scriptfull);
            preparedStatement.setInt(5, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpdateNewsImg(int id, String time, String title, String scriptshort, String scriptfull, String image) {
        
        try {
            String sql = "UPDATE news set Time = ?, Title = ?, ScriptShort = ?, ScriptFull = ?, Image = ? where News_ID = ? ;";
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, time);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, scriptshort);
            preparedStatement.setString(4, scriptfull);
            preparedStatement.setString(5, image);
            preparedStatement.setInt(6, id);
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
