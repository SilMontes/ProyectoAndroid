package com.silmontes.proyectoandroid;

import static com.silmontes.proyectoandroid.BookActivity.BOOK_ID_KEY;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BooksRecyclerAdapter extends  RecyclerView.Adapter<BooksRecyclerAdapter.ViewHolder>{


    private ArrayList<Book> books = new ArrayList<>();
    // As Glide is going to be used, a context will be create to show the images
    private Context mContext;

    public BooksRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;

        notifyDataSetChanged(); // Refreshing data in the RecyclerView
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtBookName.setText(books.get(position).getBookName());
        Glide.with(mContext).asBitmap().load(books.get(position).getImageUrl()).into(holder.imgBook);

        int adapterPosition = holder.getAdapterPosition();

        holder.parentMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,BookActivity.class); //Destination: BookActivity.class (Navigates the user to the BookActivity
                intent.putExtra(BOOK_ID_KEY,books.get(adapterPosition).getId()); //Passing data; in this case the book id  .putExtra() receives key-value pairs as parameters
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        //View from list_item_book
        private CardView parentMaterialCardView;
        private ImageView imgBook;
        private TextView txtBookName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentMaterialCardView = itemView.findViewById(R.id.parentMaterialCardView);
            imgBook = itemView.findViewById(R.id.imgBook);
            txtBookName = itemView.findViewById(R.id.txtBookName);


        }
    }
}
