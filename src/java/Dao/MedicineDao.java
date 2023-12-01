/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Contact.ContactDB;
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
public class MedicineDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    
    public boolean CreateMedicine(String tenthuoc1, String lieuluong1, int id_pre) {
        String sql = "insert into medicine([name_medicine],[medication_dosage], [id_prescription_medicine] ) values(?, ?, ?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tenthuoc1);
            preparedStatement.setString(2, lieuluong1);
            preparedStatement.setInt(3, id_pre);

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public ArrayList<medicine> getMedicineDetail(int id) throws ClassNotFoundException, SQLException {
        String sql = "select * from medicine where id_prescription_medicine = ?;";
      
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, id);

          resultSet =  preparedStatement.executeQuery();
            ArrayList<medicine> medicineList = new ArrayList<>();
            while(resultSet.next()){
                medicineList.add(new medicine(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4)));
            }
             return medicineList;
            
    }
}
