package com.zonamagang.zonamagang.Model;

import java.util.List;

public class magang
{

  public String akhir_magang;
  public String awal_magang;
  public List<String> industri;
  public List<String> siswa;

  public magang(){

  }

  public String getAkhir_magang() {
    return akhir_magang;
  }

  public void setAkhir_magang(String akhir_magang) {
    this.akhir_magang = akhir_magang;
  }

  public String getAwal_magang() {
    return awal_magang;
  }

  public void setAwal_magang(String awal_magang) {
    this.awal_magang = awal_magang;
  }

  public List<String> getIndustri() {
    return industri;
  }

  public void setIndustri(List<String> industri) {
    this.industri = industri;
  }

  public List<String> getSiswa() {
    return siswa;
  }

  public void setSiswa(List<String> siswa) {
    this.siswa = siswa;
  }
}