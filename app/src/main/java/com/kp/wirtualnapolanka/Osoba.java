package com.kp.wirtualnapolanka;

public class Osoba {

    String id;
    String osoba;

    public Osoba(String id, String osoba) {
        this.id = id;
        this.osoba = osoba;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOsoba() {
        return osoba;
    }

    public void setOsoba(String osoba) {
        this.osoba = osoba;
    }
}
