package com.direct.success.markdirect.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.managers.RegisterUserAPI;

public class RegisterActivity extends AppCompatActivity {
    private Spinner cmbOpciones;
    private EditText email;
    private EditText password;
    private EditText repeatPassword;
    private EditText age;
    private Button registerButton;
    private String sexo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final String[] datos =
                new String[]{"Hombre","Mujer"};

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, datos);

        email = (EditText) findViewById(R.id.activity_register___email);
        password = (EditText) findViewById(R.id.activity_register___password);
        repeatPassword = (EditText) findViewById(R.id.activity_register___password_repeat);
        age = (EditText) findViewById(R.id.activity_register___age);
        registerButton = (Button) findViewById(R.id.activity_register___registerButton);
        cmbOpciones = (Spinner)findViewById(R.id.CmbOpciones);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString() != repeatPassword.getText().toString()) {
                    //// TODO: arreglar esto
                    Snackbar.make(registerButton, "Las contraseñas no son iguales", Snackbar.LENGTH_LONG).show();
                    RegisterUserAPI registerUserAPI = new RegisterUserAPI();
                    registerUserAPI.sendPost(getBaseContext(), email.getText().toString(), password.getText().toString(),age.getText().toString(),sexo, "MANUAL");
                    registerUserAPI.setListener(new RegisterUserAPI.RegisterUserAPINewTokenListener() {
                        @Override
                        public void onNewToken(String token) {
                            SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putString("Token", token);
                            editor.putString("Tipo", "MANUAL");
                            editor.commit();
                            Intent i = new Intent(RegisterActivity.this, GeneralActivity.class);
                            startActivity(i);

                        }
                    });
                }
            }
        });

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        cmbOpciones.setAdapter(adaptador);

        cmbOpciones.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent,
                                               android.view.View v, int position, long id) {
                        //lblMensaje.setText("Seleccionado: " +
                          //      parent.getItemAtPosition(position));
                        sexo=parent.getItemAtPosition(position).toString();
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        //lblMensaje.setText("");
                    }
                });

    }

    public void quitKeyboard(View v){
        InputMethodManager imm =
                (InputMethodManager) v.getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
