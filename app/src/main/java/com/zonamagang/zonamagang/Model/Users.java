package com.zonamagang.zonamagang.Model;

/**
 * Created by Herlambang on 8/25/2016.
 */
public class Users {
    public String Users_level;
    public String email;
    public String status_aktif;
    public String Users_level_id;

    public Users(){

    }

    public String getUsers_level() {
        return Users_level;
    }

    public void setUsers_level(String users_level) {
        Users_level = users_level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus_aktif() {
        return status_aktif;
    }

    public void setStatus_aktif(String status_aktif) {
        this.status_aktif = status_aktif;
    }

    public String getUsers_level_id() {
        return Users_level_id;
    }

    public void setUsers_level_id(String users_level_id) {
        Users_level_id = users_level_id;
    }
}
