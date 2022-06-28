package com.kp.wirtualnapolanka;

import android.widget.Spinner;

public class Pomieszczenie {

    String pomieszczenie;
    String poziom;
    String pomieszczenie_typ;
    String opiekun;
    String konsultacje;
    String obecnosc;
    String user;
    String kontakt;
    String zamek;


    public Pomieszczenie(String pomieszczenie, String poziom, String pomieszczenie_typ,String opiekun, String konsultacje, String obecnosc, String user, String kontakt, String zamek) {
        this.pomieszczenie = pomieszczenie;
        this.poziom = poziom;
        this.pomieszczenie_typ = pomieszczenie_typ;
        this.opiekun = opiekun;
        this.konsultacje = konsultacje;
        this.obecnosc = obecnosc;
        this.user = user;
        this.kontakt = kontakt;
        this.zamek = zamek;
    }

    public String getUser() {
        return user;
    }

    public String getZamek() {
        return zamek;
    }

    public void setZamek(String zamek) {
        this.zamek = zamek;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getObecnosc() {
        return obecnosc;
    }

    public void setObecnosc(String obecnosc) {
        this.obecnosc = obecnosc;
    }

    public String getKonsultacje() {
        return konsultacje;
    }

    public void setKonsultacje(String konsultacje) {
        this.konsultacje = konsultacje;
    }

    public void setPomieszczenie(String pomieszczenie) {
        this.pomieszczenie = pomieszczenie;
    }

    public void setPoziom(String poziom) {
        this.poziom = poziom;
    }

    public void setPomieszczenie_typ(String pomieszczenie_typ) {
        this.pomieszczenie_typ = pomieszczenie_typ;
    }

    public void setOpiekun(String opiekun) {
        this.opiekun = opiekun;
    }

    public String getPomieszczenie() {
        return pomieszczenie;
    }

    public String getPoziom() {
        return poziom;
    }

    public String getPomieszczenie_typ() {
        return pomieszczenie_typ;
    }

    public String getOpiekun() {
        return opiekun;
    }
}
