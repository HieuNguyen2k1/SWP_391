package Dao;

import Contact.ContactDB;
import Model.Speciality;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SpecialityDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    public ArrayList<Speciality> getAllSpeciality(){
        ArrayList<Speciality> list = new ArrayList<>();

        String sql = "select * from speciality";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Speciality(resultSet.getInt("id"), resultSet.getString("name")));
            }
            return list;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean createSpeciality(String name){
        String sql = "insert into speciality(name) values(?)";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteSpeciality(int id){
        String sql = "DELETE FROM speciality WHERE id = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
