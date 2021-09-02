package com.silmontes.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.time.LocalDate;
import java.util.ArrayList;

public class AllBooks_Activity extends AppCompatActivity {
    private RecyclerView booksRecyclerView;
    private BooksRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);

        adapter = new BooksRecyclerAdapter(this);
        booksRecyclerView = findViewById(R.id.booksRecyclerView);

        booksRecyclerView.setAdapter(adapter);
        booksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book(1,
                "1Q84",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
        books.add(new Book(2,
                "1Q84",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
        books.add(new Book(3,
                "1Q84",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
        books.add(new Book(4,
                "1Q84",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
        books.add(new Book(5,
                "1Q84",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));

        adapter.setBooks(books);
    }
}