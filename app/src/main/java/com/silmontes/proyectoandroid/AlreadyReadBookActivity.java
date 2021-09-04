package com.silmontes.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class AlreadyReadBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);

        RecyclerView recyclerView = findViewById(R.id.alReadBook_RecView);

        //We can use the adapter created as many times as needed
        BooksRecyclerAdapter adapter =new BooksRecyclerAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setBooks(DataManager.getAlreadyReadBooks());

    }

    @Override
    public void onBackPressed() { //If the user is in this activity, whenever they press the back button, they will be navigated back to mainActivity
        Intent intent = new Intent(this,MainActivity.class);
        //As the "information" is saved in the backStack, when the user presses the back button in mainActivity, it is redirected to activity_already_read_book. For fixed that, we will use some flags to clear the backStack
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //Clearing the backStack and declaring this activity as a new activity
        startActivity(intent);

    }
}