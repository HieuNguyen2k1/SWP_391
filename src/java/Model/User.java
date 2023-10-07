package Model;

import javax.swing.*;

public class User {
    public int id;
    public String name;
    public String email;
    public String password;
    public String phone;
    public String verify_key;

    public boolean is_verified;
    public String address;
    public boolean is_admin;
    public String dob;
    public boolean gender;

    public User() {

    }
    public User(int id, String name, String email, String password, String phone,String dob,  boolean gender, String address, boolean is_admin, String verify_key, boolean is_verified) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.verify_key = verify_key;
        this.is_verified = is_verified;
        this.address = address;
        this.is_admin = is_admin;
        this.dob = dob;
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", verify_key='" + verify_key + '\'' +
                ", is_verified=" + is_verified +
                ", address='" + address + '\'' +
                ", is_admin=" + is_admin +
                ", dob='" + dob + '\'' +
                ", gender=" + gender +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerify_key() {
        return verify_key;
    }

    public void setVerify_key(String verify_key) {
        this.verify_key = verify_key;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean is_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public boolean isGender() {
        return gender;
    }


    public User(String name, String email, String password, String phone, String verify_key, boolean is_verified) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.verify_key = verify_key;
        this.is_verified = is_verified;
    }
}
