package com.example.a1881user.ejercicios4;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PhoneAdapter extends BaseAdapter {

    ArrayList<Phone> phones;
    Activity activity;

    public PhoneAdapter(Activity activity){
        this.activity = activity;
        phones = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return phones.size();
    }

    @Override
    public Object getItem(int i) {
        return phones.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();

        View renglon = inflater.inflate(R.layout.renglon, null, false);
        TextView item_nombre = renglon.findViewById(R.id.item_nombre);
        TextView item_tel = renglon.findViewById(R.id.item_tel);
        Button item_llamar = renglon.findViewById(R.id.item_llamar);
        Button item_eliminar = renglon.findViewById(R.id.item_eliminar);

        //linkear el arraylist con la listview
        item_nombre.setText(phones.get(position).getNombre());
        item_tel.setText(phones.get(position).getTelefono());

        item_llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int Request_phone_call= 1;
                Intent llamar = new Intent(Intent.ACTION_CALL);
                llamar.setData(Uri.parse("tel:3116435652"));

                if(android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                    if(ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, Request_phone_call);
                    }else{
                        activity.startActivity(llamar);


                    }
                }

            }
        });

        item_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phones.remove(position);
                notifyDataSetChanged();

            }
        });

        return renglon;
    }

    public void agregarPhone(Phone phone){
        phones.add(phone);
        notifyDataSetChanged();
    }
}
