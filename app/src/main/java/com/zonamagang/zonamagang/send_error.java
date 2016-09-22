package com.zonamagang.zonamagang;

import android.content.Intent;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zonamagang.zonamagang.Model.tb_error;

import java.util.Date;

/**
 * Created by RPL on 22/09/2016.
 */
public class send_error {

    public void send_error(String error){
        tb_error tabel_error = new tb_error();
        String user_id;
        user_id = step_3_siswa.user_id;
        tabel_error.setUser_id(user_id);
        tabel_error.setError(error);

        Backendless.Persistence.save(tabel_error, new AsyncCallback<tb_error>() {
            @Override
            public void handleResponse(tb_error response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }
}
