package com.example.mersad.asrar.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mersad.asrar.ItemClickListener;
import com.example.mersad.asrar.R;

public class Setting_Holder  extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    public TextView setting_item   ;
    public ImageView setting_icone ;
    private ItemClickListener itemClickListener ;


    public Setting_Holder(View itemView) {
        super (itemView) ;


        setting_item = itemView.findViewById(R.id.setting_item);
        setting_icone = itemView.findViewById(R.id.setting_icone);

        //item click listener :
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }


    // listener :

    public void setItemClickListener (ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener ; }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }


}
