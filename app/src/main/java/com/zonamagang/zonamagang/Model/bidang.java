package com.zonamagang.zonamagang.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Skensa Tech on 20/8/2016.
 */
public class bidang {

    public String nama;
    public HashMap<String,Object> parent_bidang;

    public bidang(){

    }

    public String getNama(){
        return nama;
    }

    public HashMap<String,Object> getParent_bidang(){
        return this.parent_bidang;
    }


}
