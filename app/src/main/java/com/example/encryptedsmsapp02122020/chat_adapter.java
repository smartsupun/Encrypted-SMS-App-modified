package com.example.encryptedsmsapp02122020;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chat_adapter extends RecyclerView.Adapter<chat_adapter.MyViewHolder> {

    private Context context;
    private ArrayList user_id,phone_no,message;


    chat_adapter(Context context,
                 ArrayList user_id,
                 ArrayList phone_no,
                 ArrayList message){

        this.context=context;
        this.user_id = user_id;
        this.phone_no= phone_no;
        this.message=message;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


        holder.user_id_txt.setText(String.valueOf(user_id.get(position)));
        holder.phone_no_txt.setText(String.valueOf(phone_no.get(position)));
        holder.message_txt.setText(String.valueOf(message.get(position)));
        holder.mainLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent( context,bubble_chat.class );
                context.startActivity( intent );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView user_id_txt,phone_no_txt,message_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_id_txt = itemView.findViewById(R.id.user_id_txt);
            phone_no_txt = itemView.findViewById(R.id.phone_no_txt);
            message_txt = itemView.findViewById(R.id.message_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
