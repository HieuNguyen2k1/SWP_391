/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class DoctorSpeciality {
    private int id;
    private int id_doctor;
    private int id_speciality;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_speciality() {
        return id_speciality;
    }

    public void setId_speciality(int id_speciality) {
        this.id_speciality = id_speciality;
    }

    public DoctorSpeciality(int id, int id_doctor, int id_speciality) {
        this.id = id;
        this.id_doctor = id_doctor;
        this.id_speciality = id_speciality;
    }
    
    public String toString(){
        return "Speciality :{"+"id" + id + "doctor_id" +id_doctor +"speciality_id"+ id_speciality+"}" ;
    }
    
}
