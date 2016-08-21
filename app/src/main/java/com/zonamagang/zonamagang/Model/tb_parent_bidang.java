package com.zonamagang.zonamagang.Model;

/**
 * Created by Skensa Tech on 20/8/2016.
 */
public class tb_parent_bidang {
    public int id_parent_bidang;
    public String nama;

    public tb_parent_bidang(){

    }

    public tb_parent_bidang(int id_parent_bidang, String nama){
        this.id_parent_bidang = id_parent_bidang;
        this.nama = nama;
    }

    public int getId_parent_bidang() {
        return id_parent_bidang;
    }

    public String getNama() {
        return nama;
    }

    public void setId_parent_bidang(int id_parent_bidang) {
        this.id_parent_bidang = id_parent_bidang;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String toString(){
        return nama;
    }
}
