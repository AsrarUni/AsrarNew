package com.example.mersad.asrar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mersad.asrar.Activities.Edit_Password_Activity;
import com.example.mersad.asrar.Holder.Remind_Holder;
import com.example.mersad.asrar.Holder.Setting_Holder;
import com.example.mersad.asrar.ItemClickListener;
import com.example.mersad.asrar.Model.Remind_Items;
import com.example.mersad.asrar.Model.Setting_Items;
import com.example.mersad.asrar.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Remind_Adapter extends RecyclerView.Adapter<Remind_Holder> {

    Context _Context ;
    List<Remind_Items> Remind_list ;
    Typeface custom_font;


    public Remind_Adapter(Context Context , List<Remind_Items> Remind_list) {

        this._Context = Context ;
        this.Remind_list = Remind_list ;
        this.custom_font = Typeface.createFromAsset(Context.getAssets(), "fonts/koodak.ttf");

    }

    @Override
    public Remind_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View View = LayoutInflater.from(_Context).inflate(R.layout.item_remind, parent, false);
        return new Remind_Holder(View);

    }

    @Override
    public void onBindViewHolder(Remind_Holder holder, int position) {

        holder.remind_title.setText(Remind_list.get(position).getTitle());
        // TODO: 1/20/2019 change this 
        holder.remind_jtv_message.setText(Remind_list.get(position).getMessage());

        //set fonts :
        holder.remind_title.setTypeface(custom_font);
        holder.remind_title_lable.setTypeface(custom_font);

    }


    @Override
    public int getItemCount() {
        return Remind_list.size() ;
    }

}
