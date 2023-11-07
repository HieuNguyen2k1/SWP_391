/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Contact.ContactDB;
import Model.Record;
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
public class RecorDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    
     public boolean createRecord(String time, String chuandoan, int id_patient, int id_oppoinment, String image, String file) {

        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = currentDate.format(formatter);
        
        String sql = "Insert into medical_records(record_text, image, time, [file], patient_id,id_record_appointment) values(?, ?, ?, ?, ?,?)";

        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, chuandoan);
            preparedStatement.setString(2, image);
            preparedStatement.setString(3, formattedDate);
            preparedStatement.setString(4, file);
            preparedStatement.setInt(5, id_patient);
            preparedStatement.setInt(6, id_oppoinment);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
     
      public ArrayList<Record> getAllRecord() throws ClassNotFoundException, SQLException {

        ArrayList<Record> newsArrayList = new ArrayList<>();
        String sql = "select * from medical_records order by id desc;";
        connection = new ContactDB().makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            newsArrayList.add(new Record(
                    resultSet.getInt("id"),
                    resultSet.getString("record_text"),
                   resultSet.getInt("id_record_appointment"),
                    resultSet.getString("image"),
                    resultSet.getString("time"),
                    resultSet.getString("[file]"),
                    resultSet.getInt("patient_id")
            ));
        }
        return newsArrayList;
    }
}
