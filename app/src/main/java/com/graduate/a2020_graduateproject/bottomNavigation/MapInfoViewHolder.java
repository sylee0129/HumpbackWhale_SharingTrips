package com.graduate.a2020_graduateproject.bottomNavigation;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduate.a2020_graduateproject.R;

public class MapInfoViewHolder extends RecyclerView.ViewHolder {

    private TextView place_text;

    public MapInfoViewHolder(@NonNull View itemView) {
        super(itemView);

        place_text = itemView.findViewById(R.id.place_text);
    }

    public void setPlace_text(String text){
        this.place_text.setText(text);
    }


}
