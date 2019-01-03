package com.example.mersad.asrar.Holder;


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mersad.asrar.R;
import com.example.mersad.asrar.ItemClickListener;


public class Students_Aattendance_Holder extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnLongClickListener {

    //lables
    public TextView tvLableFirstName ;
    public TextView tvLableLastName ;
    public TextView tvLableMajor ;
    public TextView tvLableStudentCode ;
    public TextView tvLableIsPresent ;
    public TextView tvLableMemo ;

    //infos
    public TextView tvFirstName ;
    public TextView tvLastName ;
    public TextView tvMajor ;
    public TextView tvStudentCode ;
    public TextView tvIsPresent ;

    //pic
    public ImageView ivStudentPic ;

    //Edit text memos
    public EditText edtMemo ;

    //CardView
    CardView Students_CardView ;

    //listener
    private ItemClickListener itemClickListener ;

    //image view background
    public ImageView Students_iv_background ;


    public Students_Aattendance_Holder(View itemView) {
        super (itemView) ;

        //find views :
        tvLableFirstName = itemView.findViewById(R.id.tvLableFirstName);
        tvLableLastName = itemView.findViewById(R.id.tvLableLastName);
        tvLableMajor = itemView.findViewById(R.id.tvLableMajor);
        tvLableStudentCode = itemView.findViewById(R.id.tvLableStudentCode);
        tvLableIsPresent = itemView.findViewById(R.id.tvLableIsPresent);
        tvLableMemo = itemView.findViewById(R.id.tvLableMemo);

        tvFirstName = itemView.findViewById(R.id.tvFirstName);
        tvLastName = itemView.findViewById(R.id.tvLastName);
        tvMajor = itemView.findViewById(R.id.tvMajor);
        tvStudentCode = itemView.findViewById(R.id.tvStudentCode);
        tvIsPresent = itemView.findViewById(R.id.tvIsPresent);

        ivStudentPic = itemView.findViewById(R.id.ivStudentPic);

        edtMemo = itemView.findViewById(R.id.edtMemo);

        Students_CardView = itemView.findViewById(R.id.Students_CardView);

        Students_iv_background = itemView.findViewById(R.id.Students_iv_background);


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
