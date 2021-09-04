package com.silmontes.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private ImageView bookImg;
    private TextView  txtBNameT,txtBAuthorT,txtBPagesT,txtBShortDescT,txtBLongDescT,txtReleaseDataT;
    private Button btnWantRead, btnAlRead,btnReading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initializeViews();


        //Getting data from the recyclerView
        Intent intent = getIntent();
        if(null != intent){

            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);

            if(bookId != -1){ //make sure to receive some data from the intent
                Book selectedBook = DataManager.getInstance().getBookById(bookId);

                if(null != selectedBook){
                    setData(selectedBook);
                    handleAlreadyRead(selectedBook);
                    handleWantToRead(selectedBook);
                    handleCurrentlyReading(selectedBook);
                  //  handleFavoriteBooks(selectedBook);
                }
            }
        }


    }
    /*
    private void handleFavoriteBooks(Book book) {

        ArrayList<Book> favoriteBooks = DataManager.getInstance().getFavoriteBooks();
        if(favoriteBooks.contains(book)){
            btnWantRead.setEnabled(false);
        }else{
            btnWantRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DataManager.getInstance().addToFavoriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        // Navigate the user to the activity where all the already read books are
                        Intent intent = new Intent(BookActivity.this,FavoriteBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

     */

    private void handleCurrentlyReading(Book book) {

        ArrayList<Book> currentlyReadingBooks = DataManager.getInstance().getCurrentlyReadingBooks();
        if(currentlyReadingBooks.contains(book)){
            btnReading.setEnabled(false);
        }else{
            btnReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DataManager.getInstance().addToCurrentlyReading(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        // Navigate the user to the activity where all the already read books are
                        Intent intent = new Intent(BookActivity.this,CurrentlyReadingBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleWantToRead(Book book) {

        ArrayList<Book> wantToReadBooks = DataManager.getInstance().getWantToReadBooks();
        if(wantToReadBooks.contains(book)){
            btnWantRead.setEnabled(false);
        }else{
            btnWantRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DataManager.getInstance().addToWantRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        // Navigate the user to the activity where all the already read books are
                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void handleAlreadyRead(Book book) {

        ArrayList<Book> alreadyReadBooks = DataManager.getInstance().getAlreadyReadBooks();
        if(alreadyReadBooks.contains(book)){
            btnAlRead.setEnabled(false);
        }else{
            btnAlRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(DataManager.getInstance().addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();

                        // Navigate the user to the activity where all the already read books are
                        Intent intent = new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        
    }

    private void setData(Book book){
        txtBNameT.setText(book.getBookName());
        txtBAuthorT.setText(book.getAuthor());
        txtBPagesT.setText(String.valueOf(book.getPages()));
        txtBShortDescT.setText(book.getShortDescription());
        txtBLongDescT.setText(book.getLongDescription());
        txtReleaseDataT.setText(book.getReleaseData().toString());

        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImg);
    }
    private void initializeViews(){

        bookImg = findViewById(R.id.bookImg);

        txtBNameT = findViewById(R.id.txtBNameT);
        txtBAuthorT = findViewById(R.id.txtBAuthorT);
        txtBPagesT = findViewById(R.id.txtBPagesT);
        txtBShortDescT = findViewById(R.id.txtBShortDescT);
        txtBLongDescT = findViewById(R.id.txtBLongDescT);
        txtReleaseDataT = findViewById(R.id.txtReleaseDataT);

        btnWantRead = findViewById(R.id.btnWantRead);
        btnAlRead = findViewById(R.id.btnAlRead);
        btnReading = findViewById(R.id.btnReading);
    }
}