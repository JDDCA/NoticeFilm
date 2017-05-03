package com.gmail.nf.project.jddca.noticefilm.view.fragment.list;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.minPoster)
    ImageView poster;
    @BindView(R.id.minTitle)
    TextView title;
    @BindView(R.id.minYear)
    TextView year;
    @BindView(R.id.minFav)
    ImageButton addFav;
    @BindView(R.id.minList)
    ImageButton addList;
    private Context context;


    public ListHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void setTitle(String title) {
        if (title != null)
            this.title.setText(title);
    }

    public void setYear(@NonNull String year) {
        this.year.setText(year.substring(0, 4));
    }

    public void setPoster(String path) {
        Picasso
                .with(context)
                .load(RetrofitService.BASE_PATH_POSTER + path)
                .into(poster, new Callback() {
                    @Override
                    public void onSuccess() {
                        /*NOP*/
                    }
                    @Override
                    public void onError() {
                        Picasso.with(context)
                                .load(R.drawable.lock_stock)
                                .into(poster);
                    }
                });

    }

    public void setAddFav (boolean check, int first, int second){
        addFav.setImageResource(first);
        if (check)
            addFav.setImageResource(second);
        else
            addFav.setImageResource(first);
    }

}
