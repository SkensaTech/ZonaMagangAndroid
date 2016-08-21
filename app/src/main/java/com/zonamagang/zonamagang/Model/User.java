package com.zonamagang.zonamagang.Model;

/**
 * Created by Skensa Tech on 20/8/2016.
 */
public class User {

    public int id_user;
    public int user_level;
    public int intstatus_aktif;
    public String email;

    public User(){

    }

    public int getId_user() {
        return id_user;
    }

    public int getUser_level() {
        return user_level;
    }

    public int getIntstatus_aktif() {
        return intstatus_aktif;
    }

    public String getEmail() {
        return email;
    }
}
