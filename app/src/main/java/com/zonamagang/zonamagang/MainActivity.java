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
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;


public class MainActivity extends AppCompatActivity {
    TextView x;
    EditText mLoginEmail;
    EditText mLoginPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Backendless.initApp(this,Constants.APP_ID,Constants.APP_SECRET,Constants.APP_VERSION);
        setContentView(R.layout.activity_main);
        this.layoutItems();
        x = (TextView) findViewById(R.id.buatakun);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent register = new Intent(getApplicationContext(),register1.class);
                startActivity(register);

            }
        });

    }

    public void doLogin(View view){
        setContentView(R.layout.loading_screen);
        String email = mLoginEmail.getText().toString();
        String pass = mLoginPass.getText().toString();
        Backendless.UserService.login( email , pass, new AsyncCallback<BackendlessUser>()
        {
            public void handleResponse( BackendlessUser user )
            {
                // user has been logged in
                String user_level = user.getProperty("user_level").toString();
                if(user_level.equals("2")){
                    Intent homeIndustri = new Intent(MainActivity.this,HomeIndustri.class);
                    startActivity(homeIndustri);
                }
                else if(user_level.equals("1")){
                    Intent homeIndustri = new Intent(MainActivity.this,HomeIndustri.class);
                    startActivity(homeIndustri);
                }
            }

            public void handleFault( BackendlessFault fault )
            {
                // login failed, to get the error code call fault.getCode()
                Toast.makeText(MainActivity.this,"Login Failed. "+fault.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void layoutItems(){
        mLoginEmail = (EditText)findViewById(R.id.login_email);
        mLoginPass = (EditText)findViewById(R.id.login_password);
    }

}
