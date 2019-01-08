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
import com.example.mersad.asrar.Activities.Setting_Activity;
import com.example.mersad.asrar.Activities.Student_Attendance_Tablayout_Activity;
import com.example.mersad.asrar.Constant.Constant;
import com.example.mersad.asrar.Holder.Class_List_Holder;
import com.example.mersad.asrar.Holder.Setting_Holder;
import com.example.mersad.asrar.ItemClickListener;
import com.example.mersad.asrar.Model.Setting_Items;
import com.example.mersad.asrar.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Setting_Adapter extends RecyclerView.Adapter<Setting_Holder> {

    Context _Context ;
    List<Setting_Items> Setting_List ;
    Typeface custom_font;



    public Setting_Adapter(Context Context , List<Setting_Items> Setting_list) {

        this._Context = Context ;
        this.Setting_List = Setting_list ;
        this.custom_font = Typeface.createFromAsset(Context.getAssets(), "fonts/koodak.ttf");

    }

    @Override
    public Setting_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View View = LayoutInflater.from(_Context).inflate(R.layout.item_setting, parent, false);
        return new Setting_Holder(View);

    }

    @Override
    public void onBindViewHolder(Setting_Holder holder, int position) {

        holder.setting_item.setText(Setting_List.get(position).getItem());
        Picasso.get().load(Setting_List.get(position).getIcone()).resize(50,50).into(holder.setting_icone);

        //set fonts :
        holder.setting_item.setTypeface(custom_font);


//----- listener baraye click long ya short baraye ratfan be safheye bad ya baz kardane menu-----//
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View View, int position, boolean isLongClick) {
                if (isLongClick) {

                    // TODO: 1/8/2019 in avaz she ;
                    Toast.makeText(_Context, "mitoone inja ham chizi set she", Toast.LENGTH_SHORT).show();

                } else {
//----- position 0 = edit password activity -----//
                    if (position==0) {
                        Intent go_to_item = new Intent(_Context, Edit_Password_Activity.class) ;
                        _Context.startActivity(go_to_item);
                    }

                }
            }

        });

    }

    @Override
    public int getItemCount() {
        return Setting_List.size() ;
    }
}

