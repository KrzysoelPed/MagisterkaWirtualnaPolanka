package com.kp.wirtualnapolanka;

public class Osoba {

    String id;
    String prowadzacy;

    public Osoba(String id) {
        this.id = id;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProwadzacy() {
        return prowadzacy;
    }

    public void setProwadzacy(String prowadzacy) {
        this.prowadzacy = prowadzacy;
    }
}
