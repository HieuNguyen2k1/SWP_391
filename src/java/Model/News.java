/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class News {
    private int Newsid;
    private String time;
    private String title;
    private String scriptShort;
    private String scriptFull;
    private String image;

    public News() {
    }

    public News(int Newsid, String time, String title, String scriptShort, String scriptFull, String image) {
        this.Newsid = Newsid;
        this.time = time;
        this.title = title;
        this.scriptShort = scriptShort;
        this.scriptFull = scriptFull;
        this.image = image;
    }

    public int getNewsid() {
        return Newsid;
    }

    public void setNewsid(int Newsid) {
        this.Newsid = Newsid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScriptShort() {
        return scriptShort;
    }

    public void setScriptShort(String scriptShort) {
        this.scriptShort = scriptShort;
    }

    public String getScriptFull() {
        return scriptFull;
    }

    public void setScriptFull(String scriptFull) {
        this.scriptFull = scriptFull;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "News{" + "Newsid=" + Newsid + ", time=" + time + ", title=" + title + ", scriptShort=" + scriptShort + ", scriptFull=" + scriptFull + ", image=" + image + '}';
    }

}
