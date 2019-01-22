package com.example.mersad.asrar.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mersad.asrar.R;
import com.example.mersad.asrar.Utils.JustifiedTextView;

public class Remind_Holder extends RecyclerView.ViewHolder {

    public TextView remind_title_lable   ;
    public TextView remind_title ;
    public JustifiedTextView remind_jtv_message ;


    public Remind_Holder (View itemView) {
        super (itemView) ;

        remind_title_lable = itemView.findViewById(R.id.remind_title_lable);
        remind_title = itemView.findViewById(R.id.remind_title);
        remind_jtv_message = itemView.findViewById(R.id.remind_jtv_message);

    }

}
