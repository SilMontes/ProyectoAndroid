package com.silmontes.proyectoandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Toolbar toolbar;
    private Book mSelectedBook;
    private MenuItem mItem;


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
                mSelectedBook = DataManager.getInstance().getBookById(bookId);

                if(null != mSelectedBook){
                    setData(mSelectedBook);
                    handleAlreadyRead(mSelectedBook);
                    handleWantToRead(mSelectedBook);
                    handleCurrentlyReading(mSelectedBook);

                }
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_share,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Checking witch item was selected
        switch (item.getItemId()){
            case R.id.heartFavBook:
                mItem = item;
                handleFavoriteBooks(mSelectedBook);
                return true;
            case R.id.send_em_ic:
                sendEmail();
                return true;
            case R.id.send_ms_ic:
                sendMessage();
                return true;
            case R.id.send_whatsapp_ic:
                sendWhatsAppMessage();
            default:
                return super.onOptionsItemSelected(item);

        }
    }


/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem favItem= findViewById(R.id.heartFavBook);
        ArrayList<Book> favoriteBooks = DataManager.getInstance().getFavoriteBooks();

        if(favoriteBooks.contains(mSelectedBook)){
            favItem.setIcon(R.drawable.ic_fav_red);
        }else if(!favoriteBooks.contains(mSelectedBook)){
            favItem.setIcon(R.drawable.ic_favorite);
        }


        return super.onPrepareOptionsMenu(menu);
    }
*/
    private void handleFavoriteBooks(Book book) {

        ArrayList<Book> favoriteBooks = DataManager.getInstance().getFavoriteBooks();

        if(favoriteBooks.contains(book)){
            Toast.makeText(BookActivity.this, "Item Already Added", Toast.LENGTH_SHORT).show();
        }else{
            if(DataManager.getInstance().addToFavoriteBooks(book)){
                Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                mItem.setIcon(R.drawable.ic_fav_red);

            }else{
                Toast.makeText(BookActivity.this, "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendWhatsAppMessage() {
        String messageBody = bookInfoToSend();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,messageBody);
        intent.setType("text/plane");
        intent.setPackage("com.whatsapp");
        startActivity(intent);
    }


    private void sendMessage() {

        String messageBody = bookInfoToSend();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("smsto:"+""));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", messageBody);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void sendEmail() {

        String bookName = mSelectedBook.getBookName();
        String subject = "Sending Book: "+bookName;
        String messageBody = bookInfoToSend();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,messageBody);

        startActivity(intent);

    }

    private String bookInfoToSend(){
        String bookName = mSelectedBook.getBookName();
        String bookAuthor = mSelectedBook.getAuthor();
        int pages = mSelectedBook.getPages();
        LocalDate releaseDate = mSelectedBook.getReleaseData();
        String shortDesc = mSelectedBook.getShortDescription();
        String longDesc = mSelectedBook.getLongDescription();

        String messageBody = "Book Name: "+bookName +
                "\nAuthor: "+bookAuthor +
                "\n\nPages: "+pages +
                "\n\nRelease Date: "+releaseDate +
                "\n\nDescription: "+shortDesc +
                "\n\nMore Details "+longDesc;
        return messageBody;
    }





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
        int  releaseDateYear = book.getReleaseData().getYear();
        int  releaseDateMonth = book.getReleaseData().getMonthValue();
        int  releaseDateDay = book.getReleaseData().getDayOfMonth();
        txtReleaseDataT.setText(releaseDateDay+" "+monthFormat(releaseDateMonth)+" "+releaseDateYear);

        Glide.with(this).asBitmap().load(book.getImageUrl()).into(bookImg);
    }
    private void initializeViews(){

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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

    private String monthFormat(int month){

        if(month == 1) {
            return "JAN";
        }
        if(month == 2) {
            return "FEB";
        }
        if(month == 3) {
            return "MAR";
        }
        if(month == 4) {
            return "APR";
        }
        if(month == 5) {
            return "MAY";
        }
        if(month == 6) {
            return "JUN";
        }
        if(month == 7) {
            return "JULY";
        }
        if(month == 8) {
            return "AUG";
        }
        if(month == 9) {
            return "SEP";
        }
        if(month == 10) {
            return "OCT";
        }
        if(month == 11) {
            return "NOV";
        }
        if(month == 12) {
            return "DEC";
        }

        return "JAN";
    }

}