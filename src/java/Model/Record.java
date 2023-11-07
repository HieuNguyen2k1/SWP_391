/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Record {
    
    private int idRecord;
    private String Record_text;
    private int id_appointment;
    private int id_patient;
    private String imageRecord;
    private String file;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Record() {
    }

    public Record( int idRecord, String Record_text, int id_appointment, String imageRecord, String time, String file, int id_patient) {
        this.idRecord = idRecord;
        this.Record_text = Record_text;
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
        this.imageRecord = imageRecord;
        this.file = file;
        this.time = time;
    }

    public Record(int idRecord, String Record_text, int id_appointment, int id_patient, String imageRecord, String file) {
        this.idRecord = idRecord;
        this.Record_text = Record_text;
        this.id_appointment = id_appointment;
        this.id_patient = id_patient;
        this.imageRecord = imageRecord;
        this.file = file;
    }

    public int getIdRecord() {
        return idRecord;
    }

    public void setIdRecord(int idRecord) {
        this.idRecord = idRecord;
    }

    public String getRecord_text() {
        return Record_text;
    }

    public void setRecord_text(String Record_text) {
        this.Record_text = Record_text;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getImageRecord() {
        return imageRecord;
    }

    public void setImageRecord(String imageRecord) {
        this.imageRecord = imageRecord;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
    
}
