package com.example.tema_4;

import java.io.Serializable;

public class RezultateTest implements Serializable {
    private String rezultat;
    private int numarAnticorpi;
    private String vaccinat;
    private String tipTestare;

    public RezultateTest(String rezultat, int numarAnticorpi, String vaccinat, String tipTestare) {
        this.rezultat = rezultat;
        this.numarAnticorpi = numarAnticorpi;
        this.vaccinat = vaccinat;
        this.tipTestare = tipTestare;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public int getNumarAnticorpi() {
        return numarAnticorpi;
    }

    public void setNumarAnticorpi(int numarAnticorpi) {
        this.numarAnticorpi = numarAnticorpi;
    }

    public String getVaccinat() {
        return vaccinat;
    }

    public void setVaccinat(String vaccinat) {
        this.vaccinat = vaccinat;
    }

    public String getTipTestare() {
        return tipTestare;
    }

    public void setTipTestare(String tipTestare) {
        this.tipTestare = tipTestare;
    }

    @Override
    public String toString() {
        return "RezultateTest{" +
                "rezultat='" + rezultat + '\'' +
                ", numarAnticorpi=" + numarAnticorpi +
                ", vaccinat='" + vaccinat + '\'' +
                ", tipTestare='" + tipTestare + '\'' +
                '}';
    }
}
