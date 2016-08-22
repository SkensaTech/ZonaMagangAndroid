package com.zonamagang.zonamagang.Adapters.Industri;

/**
 * Created by Skensa Tech on 22/8/2016.
 */
public class CustomIndustri {

    public int id_industri;
    public String nama;
    public String alamat;
    public String no_telp;
    public String email;
    public int kuota;
    public String logo;

    public CustomIndustri() {
    }

    public CustomIndustri(int id_industri, String nama, String alamat, String no_telp, String email, int kuota, String logo) {
        this.id_industri = id_industri;
        this.nama = nama;
        this.alamat = alamat;
        this.no_telp = no_telp;
        this.email = email;
        this.kuota = kuota;
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId_industri() {
        return id_industri;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public String getEmail() {
        return email;
    }

    public int getKuota() {
        return kuota;
    }
}
