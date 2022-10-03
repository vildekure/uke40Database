package com.example.database;

public class Kontakt {
    public long _ID;
    public String navn;
    public String tlf;

    public Kontakt(){
    }

    public Kontakt(String navn, String tlf) {
        this.navn = navn;
        this.tlf = tlf;
    }

    public Kontakt(long _ID, String navn, String tlf) {
        this._ID = _ID;
        this.navn = navn;
        this.tlf = tlf;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
}



