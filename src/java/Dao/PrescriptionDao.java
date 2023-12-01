/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Contact.ContactDB;
import Model.Data;
import Model.Prescription;
import Model.medicine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class PrescriptionDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public boolean CreatePrescription(int id_pt, int id_app, int id_doc, int ngaydung) {
        String sql = "insert into prescription(id_patient_prescription,id_app_prescription, id_doctor_prescription,ngaydung ) values(?, ?, ?, ?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_pt);
            preparedStatement.setInt(2, id_app);
            preparedStatement.setInt(3, id_doc);
            preparedStatement.setInt(4, ngaydung);

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

//    public String getIdPrescriptiona(int id_app, int id_pt) throws ClassNotFoundException, SQLException {
//
//        String sql = "select id_prescription  from prescription where id_patient_prescription=? and id_app_prescription=?;";
//        connection = new ContactDB().makeConnection();
//        preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setInt(1, id_pt);
//        preparedStatement.setInt(2, id_app);
//        resultSet = preparedStatement.executeQuery();
//        String id_pre = null;
//        while (resultSet.next()) {
//            id_pre = resultSet.getString(1);
//            return id_pre;
//        }
//        return id_pre;
//    }
    public Prescription getIdPrescription(int id_app, int id_pt) {
        String query = "select * from [prescription] where id_patient_prescription=? and id_app_prescription=?;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_pt);
            preparedStatement.setInt(2, id_app);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Prescription user = new Prescription(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public int getIdPre(int id) throws SQLException, ClassNotFoundException {
        String sql = "select [id_prescription] from prescription where id_app_prescription=?;";
        int id_pre = 0;
        connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        try {
            if (resultSet.next()) {
                id_pre = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return id_pre;
    }

    public Prescription getDetail(int id) throws SQLException, ClassNotFoundException {
        String sql = "select prescription.* from prescription where prescription.id_prescription = ?;";
        connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
            //[id_prescription],[id_patient_prescription],[id_app_prescription],[id_doctor_prescription],[ngaydung]
            Prescription prescription = new Prescription(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getInt(4),resultSet.getInt(5));
            
             return prescription;
        }
        return null;

    }

}
