package com.zonamagang.zonamagang.Model;

/**
 * Created by RPL on 22/09/2016.
 */
public class tb_error {
    String error;
    String user_id;
    String objectId;

    public String getError() {
        return error;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

}
