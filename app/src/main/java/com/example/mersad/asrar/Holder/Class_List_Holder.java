package com.example.mersad.asrar.Holder;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mersad.asrar.R;
import com.example.mersad.asrar.ItemClickListener;


public class Class_List_Holder extends RecyclerView.ViewHolder implements OnClickListener , OnLongClickListener {

    //lables
    public TextView tvLableClassName ;
    public TextView tvLableClassCapacity ;
    public TextView tvLableClassTime ;
    public TextView tvLableClassCode ;
    public TextView tvLableClassLocation ;
    public TextView tvLableClassMemo ;

    //infos
    public TextView tvClassName ;
    public TextView tvCapacityClass ;
    public TextView tvClassTime ;
    public TextView tvClassCode ;
    public TextView tvClassLocation ;



    //Edit text memos
    public EditText edtClassMemo ;

    //CardView
    CardView classlist_CardView ;

    //listener
    private ItemClickListener itemClickListener ;

    //image view background
    public ImageView Class_List_iv_background ;


    public Class_List_Holder(View itemView) {
        super (itemView) ;

        //find views :
        tvLableClassName = itemView.findViewById(R.id.tvLableClassName);
        tvLableClassCapacity = itemView.findViewById(R.id.tvLableClassCapacity);
        tvLableClassTime = itemView.findViewById(R.id.tvLableClassTime);
        tvLableClassCode = itemView.findViewById(R.id.tvLableClassCode);
        tvLableClassLocation = itemView.findViewById(R.id.tvLableClassLocation);
        tvLableClassMemo = itemView.findViewById(R.id.tvLableClassMemo);

        tvClassName = itemView.findViewById(R.id.tvClassName);
        tvCapacityClass = itemView.findViewById(R.id.tvCapacityClass);
        tvClassTime = itemView.findViewById(R.id.tvClassTime);
        tvClassCode = itemView.findViewById(R.id.tvClassCode);
        tvClassLocation = itemView.findViewById(R.id.tvClassLocation);


        edtClassMemo = itemView.findViewById(R.id.edtClassMemo);

        classlist_CardView = itemView.findViewById(R.id.classlist_CardView);

        Class_List_iv_background = itemView.findViewById(R.id.Class_List_iv_background);



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
