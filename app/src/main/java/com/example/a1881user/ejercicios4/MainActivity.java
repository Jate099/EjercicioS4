package com.example.a1881user.ejercicios4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv_telefonos;

    EditText et_nombre;
    EditText et_tel;
    Button btn_agregar;

    PhoneAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = findViewById(R.id.et_nombre);
        et_tel = findViewById(R.id.et_tel);
        btn_agregar = findViewById(R.id.btn_agregar);

        lv_telefonos = findViewById(R.id.lv_telefonos);

        customAdapter = new PhoneAdapter(this);
        lv_telefonos.setAdapter(customAdapter);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = et_nombre.getText().toString();
                String telefono = et_tel.getText().toString();

                Phone newPhone = new Phone(nombre, telefono);
                customAdapter.agregarPhone(newPhone);
            }
        });
    }
}