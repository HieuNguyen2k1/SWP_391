package Dao;
import Contact.ContactDB;
import Model.Doctor;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthDao {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    /*public ArrayList<Speciality> getAllSpeciality(){
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
    }*/
    /*public boolean createUser(String name, String email, String password, String phone, String verify_key, String address, String dob, boolean gender) throws ClassNotFoundException {
        String sql = "insert into patients(name, email, password, phone, dob, gender, address, is_admin, verify_key, is_verified) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, dob);
            preparedStatement.setBoolean(6, gender);
            preparedStatement.setString(7, address);
            preparedStatement.setBoolean(8, false);
            preparedStatement.setString(9, verify_key);
            preparedStatement.setBoolean(10, false);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }*/
    public boolean createPatients(String name, String email, String password,String phone, String dob, boolean gender, String address, String verify_key) throws ClassNotFoundException {
        String sql = "insert into patients(name, email, password, phone,dob, gender, address,verify_key, is_verified) values(?, ?, ?, ?, ?,?,?,?, ?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, dob);
            preparedStatement.setBoolean(6, gender);
            preparedStatement.setString(7, address);
            preparedStatement.setString(8, verify_key);
            preparedStatement.setBoolean(9, true);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
/*
    public boolean createDoctor(String name,String email, String password, String degree, int experience, int speciality_id,String image, String phone, String dob, boolean gender, String address){
        String sql = "insert into doctors(name,email, password,degree,experience,speciality_id,image,phone, dob, gender,address ) values(?, ?, ?, ?, ?,?,?, ?, ?, ?, ?)";
        try {
            this.connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, degree);
            preparedStatement.setInt(5, experience);
            preparedStatement.setInt(6, speciality_id);
            preparedStatement.setString(7, image);
            preparedStatement.setString(8, phone);
            preparedStatement.setString(9, dob);
            preparedStatement.setBoolean(10, gender);
            preparedStatement.setString(11, address);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
*/

    /*public boolean emailExist(String email) throws SQLException, ClassNotFoundException {
        String sql = "select count(id) from patients where email = ?";
        this.connection = ContactDB.makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, email);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int status = resultSet.getInt(1);
            if (status == 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }*/

    public User checkLoginUser(String email, String password){
        String query = "select * from patients where email = ? and password = ?";
        try{
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getString(8), resultSet.getBoolean(9), resultSet.getString(10), resultSet.getBoolean(11));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public Doctor checkLoginDoctor(String email, String password){
        String query = "select * from doctors where email = ? and password = ?";
        try{
            connection = ContactDB.makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Doctor(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("degree"),
                        resultSet.getInt("experience"),
                        resultSet.getInt("speciality_id"),
                        resultSet.getString("image"),
                        resultSet.getString("phone"),
                        resultSet.getString("dob"),
                        resultSet.getBoolean("gender"),
                        resultSet.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    /*public User checkLoginAdmin(String email, String password){
        String query = "select * from users where email = ? and password = ?";
        try{
            connection = new ContactDB().makeConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7), resultSet.getString(8), resultSet.getBoolean(9), resultSet.getString(10), resultSet.getBoolean(11));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }*/
    /*public ArrayList<Doctor> getAllDoctor() throws ClassNotFoundException, SQLException {
        ArrayList<Doctor> doctorArrayList = new ArrayList<>();
        String sql = "select doctors.*, speciality.name as speciality_name from doctors JOIN speciality ON doctors.speciality_id = speciality.id;";
        connection = new ContactDB().makeConnection();
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            doctorArrayList.add(new Doctor(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("degree"),
                    resultSet.getInt("experience"),
                    resultSet.getString("speciality_name"),
                    resultSet.getString("image"),
                    resultSet.getString("phone"),
                    resultSet.getString("dob"),
                    resultSet.getBoolean("gender"),
                    resultSet.getString("address")));
        }
        return doctorArrayList;
    }*/



}
