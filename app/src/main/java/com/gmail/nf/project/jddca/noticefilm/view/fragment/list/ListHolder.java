package com.gmail.nf.project.jddca.noticefilm.view.fragment.list;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseService;
import com.gmail.nf.project.jddca.noticefilm.model.db.DatabaseServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.rest.GenerateMovieServiceImpl;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.gmail.nf.project.jddca.noticefilm.view.fragment.generate.GenerateFragmentImpl;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.minCard)
    CardView cardView;
    @BindView(R.id.minPoster)
    ImageView poster;
    @BindView(R.id.minTitle)
    TextView title;
    @BindView(R.id.minYear)
    TextView year;

    private Context context;

    public ListHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public void bindFilm (@NonNull Film film){
        title.setText(film.getTitle());
        if (film.getReleaseDate()!=null)
            year.setText(film.getReleaseDate().substring(0, 4));
        Picasso
                .with(context)
                .load(RetrofitService.BASE_PATH_POSTER + film.getPosterPath())
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
        cardView.setOnClickListener(v -> Toast.makeText(context,film.getTitle(),Toast.LENGTH_SHORT).show());
    }


}
