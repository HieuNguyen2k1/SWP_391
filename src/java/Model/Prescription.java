/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Prescription {

    private int id;
    private int id_patient;
    private int id_doctor;
    private int id_app;
    private int ngaydung;

    public Prescription(int id, int id_patient, int id_doctor, int id_app, int ngaydung) {
        this.id = id;
        this.id_patient = id_patient;
        this.id_doctor = id_doctor;
        this.id_app = id_app;
        this.ngaydung = ngaydung;
    }

    public int getNgaydung() {
        return ngaydung;
    }

    public void setNgaydung(int ngaydung) {
        this.ngaydung = ngaydung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public int getId_app() {
        return id_app;
    }

    public void setId_app(int id_app) {
        this.id_app = id_app;
    }

    public Prescription(int id, int id_patient, int id_doctor, int id_app) {
        this.id = id;
        this.id_patient = id_patient;
        this.id_doctor = id_doctor;
        this.id_app = id_app;
    }

    public Prescription() {
    }

    public Prescription(int id, int id_app) {
        this.id = id;
        this.id_app = id_app;
    }
    
    
}
