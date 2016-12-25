package com.zonamagang.zonamagang.Model;

import java.util.HashMap;
import java.util.List;

public class industri
{

  public String alamat;
  public HashMap<String,Boolean> bidang;
  public Boolean is_verified;
  public String jobdesc;
  public String kota;
  public String kualifikasi;
  public String kuota;
  public String nama;
  public String no_telp;
  public String profil;
  public String provinsi;
  public String email;
  public String logo;
  public HashMap<String,Boolean> user;

  public industri(){

  }

  public String getAlamat() {
    return alamat;
  }

  public void setAlamat(String alamat) {
    this.alamat = alamat;
  }

  public Boolean getIs_verified() {
    return is_verified;
  }

  public void setIs_verified(Boolean is_verified) {
    this.is_verified = is_verified;
  }

  public String getJobdesc() {
    return jobdesc;
  }

  public void setJobdesc(String jobdesc) {
    this.jobdesc = jobdesc;
  }

  public String getKota() {
    return kota;
  }

  public void setKota(String kota) {
    this.kota = kota;
  }

  public String getKualifikasi() {
    return kualifikasi;
  }

  public void setKualifikasi(String kualifikasi) {
    this.kualifikasi = kualifikasi;
  }

  public String getKuota() {
    return kuota;
  }

  public void setKuota(String kuota) {
    this.kuota = kuota;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public String getNo_telp() {
    return no_telp;
  }

  public void setNo_telp(String no_telp) {
    this.no_telp = no_telp;
  }

  public String getProfil() {
    return profil;
  }

  public void setProfil(String profil) {
    this.profil = profil;
  }

  public String getProvinsi() {
    return provinsi;
  }

  public void setProvinsi(String provinsi) {
    this.provinsi = provinsi;
  }

  public HashMap<String, Boolean> getBidang() {
    return bidang;
  }

  public void setBidang(HashMap<String, Boolean> bidang) {
    this.bidang = bidang;
  }

  public HashMap<String, Boolean> getUser() {
    return user;
  }

  public void setUser(HashMap<String, Boolean> user) {
    this.user = user;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }
}