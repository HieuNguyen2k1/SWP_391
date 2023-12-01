/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class medicine {
    private int id;
    private String name;
    private String lieuluong;
    private int id_prescription;

    public medicine(int id, String name, String lieuluong, int id_prescription) {
        this.id = id;
        this.name = name;
        this.lieuluong = lieuluong;
        this.id_prescription = id_prescription;
    }

    public medicine() {
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

    public String getLieuluong() {
        return lieuluong;
    }

    public void setLieuluong(String lieuluong) {
        this.lieuluong = lieuluong;
    }

    public int getId_prescription() {
        return id_prescription;
    }

    public void setId_prescription(int id_prescription) {
        this.id_prescription = id_prescription;
    }
    
}
