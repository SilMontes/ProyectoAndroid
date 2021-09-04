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
    private Button btnAllBooks, btnCurrentlyReading,  btnAlreadyRead, btnWantRead,  btnSeeFavorites;
    private TextView txtName, txtLicense;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        //Create database
        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllBooks_Activity.class); // Navigate from MainActivity to AllBooks_Activity
                startActivity(intent);
            }
        });
        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigate the user to activity_already_read_book

                Intent intent = new Intent(MainActivity.this,AlreadyReadBookActivity.class);
                startActivity(intent);
            }
        });

        btnWantRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WantToReadActivity.class);
                startActivity(intent);
            }
        });

        btnSeeFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FavoriteBooksActivity.class);
                startActivity(intent);
            }
        });
        btnCurrentlyReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CurrentlyReadingBooksActivity.class);
                startActivity(intent);
            }
        });



        DataManager.getInstance(); // By doing this, we are prevented a potential bug to happen (in case there a re not items in alreadyReadBooks ArrayList or the other ones)
    }

    private void initializeViews(){

        imgLogo = findViewById(R.id.imgLogo);

        btnAllBooks = findViewById(R.id.btnAllBooks);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnWantRead = findViewById(R.id.btnWanTotRead);
        btnSeeFavorites = findViewById(R.id.btnFavorites);

        txtName = findViewById(R.id.txtName);
        txtLicense = findViewById(R.id.txtLicense);
    }
}