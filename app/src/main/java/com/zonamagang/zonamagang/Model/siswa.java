package com.zonamagang.zonamagang.Model;

import java.util.HashMap;

public class siswa {

    public HashMap<String,Boolean> users;
    public String alamat;
    public HashMap<String,Boolean> bidang;
    public String nama;
    public String nisn;
    public String sekolah;
    public String tempat_lahir;
    public String tgl_lahir;
    public String foto;

    public siswa(){

    }

    public HashMap<String, Boolean> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, Boolean> users) {
        this.users = users;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public HashMap<String, Boolean> getBidang() {
        return bidang;
    }

    public void setBidang(HashMap<String, Boolean> bidang) {
        this.bidang = bidang;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getSekolah() {
        return sekolah;
    }

    public void setSekolah(String sekolah) {
        this.sekolah = sekolah;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}