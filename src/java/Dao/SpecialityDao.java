package Dao;

import Contact.ContactDB;
import Model.Speciality;
import Model.Speciality_doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SpecialityDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ArrayList<Speciality_doctor> getAllSpeciality() {
        ArrayList<Speciality_doctor> list = new ArrayList<>();

        String sql = " SELECT doctors.id , specialities.speciality_name FROM doctors JOIN speciality_doctor ON doctors.id = speciality_doctor.doctor_id\n" +
"				JOIN specialities ON speciality_doctor.speciality_id = specialities.speciality_id;";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(new Speciality_doctor(resultSet.getInt("id"), resultSet.getString("speciality_name")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean createSpecialityDoctor(int id_doctor, int id_speciality) {
        String sql = "insert into speciality_doctor(doctor_id,speciality_id) values(?,?)";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id_doctor);
            preparedStatement.setInt(2, id_speciality);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSpeciality(int id) {
        String sql = "DELETE FROM speciality WHERE id = ?";
        try {
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createSpeciality(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
