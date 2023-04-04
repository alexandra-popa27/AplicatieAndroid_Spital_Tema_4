package com.example.tema_4.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "centre")
public class Center implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;
    @ColumnInfo(name = "denumire")
    private String denumire;
    @ColumnInfo(name = "adresa")
    private String adresa;
    @ColumnInfo(name = "judet")
    private String judet;
    @ColumnInfo(name = "telefon")
    private String telefon;

    public Center(long id, String denumire, String adresa, String judet, String telefon) {
        this.id = id;
        this.denumire = denumire;
        this.adresa = adresa;
        this.judet = judet;
        this.telefon = telefon;
    }

    @Ignore
    public Center(String denumire, String adresa, String judet, String telefon) {
        this.denumire = denumire;
        this.adresa = adresa;
        this.judet = judet;
        this.telefon = telefon;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getJudet() {
        return judet;
    }

    public void setJudet(String judet) {
        this.judet = judet;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Center{" +
                "denumire='" + denumire + '\'' +
                ", adresa='" + adresa + '\'' +
                ", judet='" + judet + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
