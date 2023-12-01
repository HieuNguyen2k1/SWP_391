/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Contact.ContactDB;
import Model.Record;
import java.io.InputStream;
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

    public boolean createRecord(String time, String chuandoan, int id_patient, int id_oppoinment, String image, InputStream file, String height, int weight) {

        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm:ss");
        String formattedDate = currentDate.format(formatter);

        String sql = "Insert into medical_records(record_text, image, time, [file], patient_id,id_record_appointment,height,weight) values(?, ?, ?, ?, ?,?,?,?)";

        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, chuandoan);
            preparedStatement.setString(2, image);
            preparedStatement.setString(3, formattedDate);

            preparedStatement.setBlob(4, file);

            preparedStatement.setInt(5, id_patient);
            preparedStatement.setInt(6, id_oppoinment);
            preparedStatement.setString(7, height);
            preparedStatement.setInt(8, weight);

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public ArrayList<Record> getAllAppointmentsOfPatientFinish1(int user_id) {
        ArrayList<Record> records = new ArrayList<>();
        try {
            String sql = "select * from medical_records ;";
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                records.add(new Record(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBytes(6),
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
                ));
            }
            return records;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }

    public Record getRecordById(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from medical_records where id=?;";

        this.connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        System.out.println("===========================");
        while (resultSet.next()) {
            try {
                Record record = new Record(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBytes(6),
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
                );

                return record;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }

    public int getRecordId(int id) throws ClassNotFoundException, SQLException {
        String sql = "select id from medical_records where id_record_appointment=?;";
        int id_record = 0;
        this.connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        System.out.println("===========================");
        while (resultSet.next()) {
            try {
                id_record = resultSet.getInt(1);

                return id_record;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return id_record;
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
                    resultSet.getBytes("[file]"),
                    resultSet.getInt("patient_id"),
                    resultSet.getString("height"),
                    resultSet.getInt("weight")
            ));
        }
        return newsArrayList;
    }

    public Record getRecord(int id_patient, int app_id) throws ClassNotFoundException, SQLException {

        String sql = "select * from medical_records where patient_id=? and id_record_appointment=?";
//        connection = new ContactDB().makeConnection();
//        preparedStatement = connection.prepareStatement(sql);
//        resultSet = preparedStatement.executeQuery();
        this.connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id_patient);
        preparedStatement.setInt(2, app_id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            try {
                Record record = new Record(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getBytes(6),
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
                );

                return record;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }
}
