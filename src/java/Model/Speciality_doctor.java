/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Speciality_doctor {
    int id;
    int doctor_id;
    int special_id;
    String name_special;

    public String getName_special() {
        return name_special;
    }

    public void setName_special(String name_special) {
        this.name_special = name_special;
    }

   public Speciality_doctor() {
       
    }
   
   public Speciality_doctor( int doctor_id, String name_special) {
        
        this.doctor_id = doctor_id;
        this.name_special = name_special;
    } 
    public Speciality_doctor(int id, int doctor_id, int special_id) {
        this.id = id;
        this.doctor_id = doctor_id;
        this.special_id = special_id;
    }
    
public Speciality_doctor( int doctor_id, int special_id) {
        
        this.doctor_id = doctor_id;
        this.special_id = special_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getSpecial_id() {
        return special_id;
    }

    public void setSpecial_id(int special_id) {
        this.special_id = special_id;
    }
    
    @Override
    public String toString(){
        return "Speciality_doctor : " + id +" doctor_id " + doctor_id + "speciality_id" + special_id;
    }
}
