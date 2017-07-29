package com.zonamagang.zonamagang.Adapters.Siswa;

/**
 * Created by Skensa Tech on 25/8/2016.
 */
public class HomeIndustriSiswaCustom {

    public int id_siswa;
    public String nama_sekolah;
    public String nama_siswa;
    public String bidang;
    public String foto;

    public HomeIndustriSiswaCustom() {
    }

    public HomeIndustriSiswaCustom(int id_siswa, String nama_sekolah, String nama_siswa, String bidang, String foto) {
        this.id_siswa = id_siswa;
        this.nama_sekolah = nama_sekolah;
        this.nama_siswa = nama_siswa;
        this.bidang = bidang;
        this.foto = foto;
    }

    public int getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(int id_siswa) {
        this.id_siswa = id_siswa;
    }

    public String getNama_sekolah() {
        return nama_sekolah;
    }

    public void setNama_sekolah(String nama_sekolah) {
        this.nama_sekolah = nama_sekolah;
    }

    public String getNama_siswa() {
        return nama_siswa;
    }

    public void setNama_siswa(String nama_siswa) {
        this.nama_siswa = nama_siswa;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
