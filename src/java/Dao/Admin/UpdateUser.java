package Dao.Admin;

import Contact.ContactDB;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UpdateUser {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    //=============ADMIN DAO=============
    public List<User> getPatientsList() {
        List<User> list = new ArrayList<>();
        String query = "SELECT * from patients";
        try {
            conn =  ContactDB.makeConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("phone"),
                                rs.getString("dob"),
                                rs.getBoolean("gender"),
                                rs.getString("address"),
                                rs.getBoolean("is_admin"),
                                rs.getString("verify_key"),
                                rs.getBoolean("is_verified")
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getPatientById(int id) {
        User user = new User() ;
        String query = "SELECT * from patients where id = ?";
        try {
            conn = new ContactDB().makeConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getBoolean(7), rs.getString(8), rs.getBoolean(9), rs.getString(10), rs.getBoolean(11));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public void deleteUser(int id) {
        String query = "delete from patients\n"
                + "where id = ?";
        try {
            conn = new ContactDB().makeConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    //
//    public boolean addUser(String userName, String password, String fullName, String email, String tel, String hash) {
//        String query = "INSERT INTO NGUOIDUNG VALUES (?, ?, ?,?,?,?,?,?)";
//
//        try {
//            conn = new DBContext().getConnection();
//            ps = conn.prepareStatement(query);
//            ps.setString(1, userName);
//            ps.setString(2, password);
//            ps.setString(3, fullName);
//            ps.setString(4, email);
//            ps.setString(5, tel);
//            ps.setBoolean(6, false);
//            ps.setString(7,hash);
//            ps.setString(8, "User");
//            ps.executeUpdate();
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//
//
    public void updatePatients(int id, String name,String email,String password, String phone, String dob, String address, boolean gender) {
        String query = "update patients set name = ?,email = ?,password = ?, phone = ?,address = ?,dob = ?,gender = ? where id = ?";
        try {
            conn = ContactDB.makeConnection();
            assert conn != null;
            ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, phone);
            ps.setString(5, address);
            ps.setString(6, dob);
            ps.setBoolean(7, gender);
            ps.setInt(8,id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<User> seachUser(String fullname){
        String query = "select * from patients, doctor where name like ?";
        ArrayList<User> result = new ArrayList<>();

        try {
            conn = new ContactDB().makeConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + fullname + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                result.add(new User(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getString("verify_key"),
                        rs.getBoolean("is_verified")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        UpdateUser dao = new UpdateUser();
//        System.out.println(dao.changePassword("344aa626-3199-4f33-bef0-241f6ee93aca", "minh"));
    }
}
