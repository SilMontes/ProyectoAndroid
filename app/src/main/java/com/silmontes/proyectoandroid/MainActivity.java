package com.silmontes.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView imgLogo;
    private Button btnAllBooks, btnCurrentlyReading,  btnAlreadyRead,  btnWishList,  btnSeeFavorites;
    private TextView txtName, txtLicense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooks_Activity.class); // Navigate from MainActivity to AllBooks_Activity
                startActivity(intent);
            }
        });

    }

    private void initializeViews(){

        imgLogo = findViewById(R.id.imgLogo);

        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWishList = findViewById(R.id.btnWishList);
        btnSeeFavorites = findViewById(R.id.btnWishList);

        txtName = findViewById(R.id.txtName);
        txtLicense = findViewById(R.id.txtLicense);
    }
}