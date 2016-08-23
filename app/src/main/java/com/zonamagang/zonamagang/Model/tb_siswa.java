package com.zonamagang.zonamagang.Model;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;

public class tb_siswa {
    private java.util.Date updated;
    private String foto;
    private java.util.Date created;
    private String tempat_lahir;
    private Integer id_bidang;
    private String nisn;
    private String objectId;
    private Integer id_sekolah;
    private String nama;
    private String tgl_lahir;
    private String ownerId;
    private String alamat;
    private Integer id_siswa;
    private Integer id_user;

    public java.util.Date getUpdated() {
        return updated;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public Integer getId_bidang() {
        return id_bidang;
    }

    public void setId_bidang(Integer id_bidang) {
        this.id_bidang = id_bidang;
    }

    public String getNisn() {
        return nisn;
    }

    public void setNisn(String nisn) {
        this.nisn = nisn;
    }

    public String getObjectId() {
        return objectId;
    }

    public Integer getId_sekolah() {
        return id_sekolah;
    }

    public void setId_sekolah(Integer id_sekolah) {
        this.id_sekolah = id_sekolah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getId_siswa() {
        return id_siswa;
    }

    public void setId_siswa(Integer id_siswa) {
        this.id_siswa = id_siswa;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }


    public tb_siswa save() {
        return Backendless.Data.of(tb_siswa.class).save(this);
    }

    public Future<tb_siswa> saveAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<tb_siswa> future = new Future<tb_siswa>();
            Backendless.Data.of(tb_siswa.class).save(this, future);

            return future;
        }
    }

    public void saveAsync(AsyncCallback<tb_siswa> callback) {
        Backendless.Data.of(tb_siswa.class).save(this, callback);
    }

    public Long remove() {
        return Backendless.Data.of(tb_siswa.class).remove(this);
    }

    public Future<Long> removeAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<Long> future = new Future<Long>();
            Backendless.Data.of(tb_siswa.class).remove(this, future);

            return future;
        }
    }

    public void removeAsync(AsyncCallback<Long> callback) {
        Backendless.Data.of(tb_siswa.class).remove(this, callback);
    }

    public static tb_siswa findById(String id) {
        return Backendless.Data.of(tb_siswa.class).findById(id);
    }

    public static Future<tb_siswa> findByIdAsync(String id) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<tb_siswa> future = new Future<tb_siswa>();
            Backendless.Data.of(tb_siswa.class).findById(id, future);

            return future;
        }
    }

    public static void findByIdAsync(String id, AsyncCallback<tb_siswa> callback) {
        Backendless.Data.of(tb_siswa.class).findById(id, callback);
    }

    public static tb_siswa findFirst() {
        return Backendless.Data.of(tb_siswa.class).findFirst();
    }

    public static Future<tb_siswa> findFirstAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<tb_siswa> future = new Future<tb_siswa>();
            Backendless.Data.of(tb_siswa.class).findFirst(future);

            return future;
        }
    }

    public static void findFirstAsync(AsyncCallback<tb_siswa> callback) {
        Backendless.Data.of(tb_siswa.class).findFirst(callback);
    }

    public static tb_siswa findLast() {
        return Backendless.Data.of(tb_siswa.class).findLast();
    }

    public static Future<tb_siswa> findLastAsync() {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<tb_siswa> future = new Future<tb_siswa>();
            Backendless.Data.of(tb_siswa.class).findLast(future);

            return future;
        }
    }

    public static void findLastAsync(AsyncCallback<tb_siswa> callback) {
        Backendless.Data.of(tb_siswa.class).findLast(callback);
    }

    public static BackendlessCollection<tb_siswa> find(BackendlessDataQuery query) {
        return Backendless.Data.of(tb_siswa.class).find(query);
    }

    public static Future<BackendlessCollection<tb_siswa>> findAsync(BackendlessDataQuery query) {
        if (Backendless.isAndroid()) {
            throw new UnsupportedOperationException("Using this method is restricted in Android");
        } else {
            Future<BackendlessCollection<tb_siswa>> future = new Future<BackendlessCollection<tb_siswa>>();
            Backendless.Data.of(tb_siswa.class).find(query, future);

            return future;
        }
    }

    public static void findAsync(BackendlessDataQuery query, AsyncCallback<BackendlessCollection<tb_siswa>> callback) {
        Backendless.Data.of(tb_siswa.class).find(query, callback);
    }
}