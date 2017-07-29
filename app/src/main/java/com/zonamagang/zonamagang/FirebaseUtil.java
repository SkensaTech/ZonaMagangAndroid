package com.zonamagang.zonamagang;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mmmm on 19-Dec-16.
 */

public class FirebaseUtil {

    public static DatabaseReference getBaseRef(){
        return FirebaseDatabase.getInstance().getReference();
    }

    public static DatabaseReference getIndustriRef(){
        return getBaseRef().child("industri");
    }

    public static String getCurrentUserId(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            return user.getUid();
        }
        return null;
    }

    public static DatabaseReference getCurrentUserRef(){
        String uid = getCurrentUserId();
        if(uid != null){
            return getBaseRef().child("Users").child(getCurrentUserId());
        }
        return null;
    }

    public static DatabaseReference getChildrenIndustriRef(String Users_level_id){
        return getBaseRef().child("industri").child(Users_level_id);
    }

    public static DatabaseReference getChildrenSiswaRef(String Users_level_id){
        return getBaseRef().child("siswa").child(Users_level_id);
    }

}
