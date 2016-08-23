package com.zonamagang.zonamagang.Adapters.Siswa;

/**
 * Created by Rizal on 8/23/2016.
 */
public class notifikasi_siswa_costum {
    private int id_industri;
    private String nama;
    private String alamat;
    private String no_telp;
    private String email;
    private int kuota;
    private String logo;

    public notifikasi_siswa_costum(int id_industri, String nama, String alamat, String no_telp, String email, int kuota, String logo) {
        this.id_industri = id_industri;
        this.nama = nama;
        this.alamat = alamat;
        this.no_telp = no_telp;
        this.email = email;
        this.kuota = kuota;
        this.logo = logo;
    }
}
