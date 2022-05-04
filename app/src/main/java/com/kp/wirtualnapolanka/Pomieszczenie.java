package com.kp.wirtualnapolanka;

import android.widget.Spinner;

public class Pomieszczenie {

    String pomieszczenie;
    String poziom;
    String pomieszczenie_typ;
    String opiekun;


    public Pomieszczenie(String pomieszczenie, String poziom, String pomieszczenie_typ, String opiekun) {
        this.pomieszczenie = pomieszczenie;
        this.poziom = poziom;
        this.pomieszczenie_typ = pomieszczenie_typ;
        this.opiekun = opiekun;
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
