package com.zonamagang.zonamagang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zonamagang.zonamagang.Model.tb_industri;
import com.zonamagang.zonamagang.Model.tb_siswa;

import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    TextView x;
    EditText mLoginEmail;
    EditText mLoginPass;
    String nama, logo, email, id_user_string;
    String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Backendless.initApp(this, Constants.APP_ID, Constants.APP_SECRET, Constants.APP_VERSION);
        setContentView(R.layout.activity_main);
        this.layoutItems();
        x = (TextView) findViewById(R.id.buatakun);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent register = new Intent(getApplicationContext(), register1.class);
                startActivity(register);
            }
        });
    }

    public void doLogin(View view) {
        setContentView(R.layout.loading_screen);

        email = mLoginEmail.getText().toString();
        if (email == null){
            Toast.makeText(this,"Isikan email anda",Toast.LENGTH_SHORT).show();
        }
        String pass = mLoginPass.getText().toString();
        if (pass == null){
            Toast.makeText(this,"Isikan password anda",Toast.LENGTH_SHORT).show();
        }

        Backendless.UserService.login(email, pass, new AsyncCallback<BackendlessUser>() {
            public void handleResponse(BackendlessUser user) {
                // user has been logged in
                String user_level = user.getProperty("user_level").toString();
                id_user_string = user.getProperty("id_user").toString();
                if (user_level.equals("2")) {

                    String whereClause = "id_user = " + id_user_string;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(whereClause);
                    Backendless.Persistence.of(tb_industri.class).find(dataQuery,
                            new AsyncCallback<BackendlessCollection<tb_industri>>() {
                                @Override
                                public void handleResponse(BackendlessCollection<tb_industri> foundContacts) {
                                    List<tb_industri> firstPage = foundContacts.getCurrentPage();

                                    Iterator<tb_industri> iterator = firstPage.iterator();

                                    while (iterator.hasNext()) {
                                        tb_industri industriInfo = iterator.next();
                                        nama = industriInfo.getNama();
                                        logo = industriInfo.getLogo();

                                        Intent homeIndustri = new Intent(MainActivity.this, HomeIndustri.class);
                                        homeIndustri.putExtra("email", email);
                                        homeIndustri.putExtra("nama", nama);
                                        homeIndustri.putExtra("logo", logo);
                                        startActivity(homeIndustri);
                                    }
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    // an error has occurred, the error code can be retrieved with fault.getCode()
                                    Toast.makeText(MainActivity.this, "Failed to get industri info, " + fault.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });

                } else if (user_level.equals("3")) {

                    String whereClause = "id_user = " + id_user_string;
                    BackendlessDataQuery dataQuery = new BackendlessDataQuery();
                    dataQuery.setWhereClause(whereClause);
                    Backendless.Persistence.of(tb_siswa.class).find(dataQuery,
                            new AsyncCallback<BackendlessCollection<tb_siswa>>() {
                                @Override
                                public void handleResponse(BackendlessCollection<tb_siswa> foundContacts) {
                                    List<tb_siswa> firstPage = foundContacts.getCurrentPage();

                                    Iterator<tb_siswa> iterator = firstPage.iterator();

                                    while (iterator.hasNext()) {
                                        tb_siswa siswaInfo = iterator.next();
                                        nama = siswaInfo.getNama();
                                        foto = siswaInfo.getFoto();

                                        Intent homeSiswa = new Intent(MainActivity.this, home_siswa_1.class);
                                        homeSiswa.putExtra("email", email);
                                        homeSiswa.putExtra("nama", nama);
                                        homeSiswa.putExtra("foto", foto);
                                        startActivity(homeSiswa);
                                    }
                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    // an error has occurred, the error code can be retrieved with fault.getCode()
                                    Toast.makeText(MainActivity.this, "Failed to get industri info, " + fault.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }

            public void handleFault(BackendlessFault fault) {
                // login failed, to get the error code call fault.getCode()
                Toast.makeText(MainActivity.this, "Login Failed. " + fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void layoutItems() {
        mLoginEmail = (EditText) findViewById(R.id.login_email);
        mLoginPass = (EditText) findViewById(R.id.login_password);
    }

}
