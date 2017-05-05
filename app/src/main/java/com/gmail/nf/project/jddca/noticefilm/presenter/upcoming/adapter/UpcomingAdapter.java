package com.gmail.nf.project.jddca.noticefilm.presenter.upcoming.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.pojos.Film;
import com.gmail.nf.project.jddca.noticefilm.model.utils.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.ViewHolder> {

    private List<Film> filmList;

    public UpcomingAdapter(List<Film> filmList) {
        this.filmList = filmList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_upcoming_films, parent, false);
        ViewHolder vh = new ViewHolder(view, parent.getContext());
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(holder.context)
                .load(RetrofitService.BASE_PATH_POSTER + filmList.get(position).getPosterPath())
                .centerCrop()
                .resize(holder.poster.getMeasuredWidth(), holder.poster.getMeasuredHeight())
                .into(holder.poster);
        holder.title.setText(filmList.get(position).getTitle());
        holder.year.setText(filmList.get(position).getReleaseDate());
        holder.vote.setText(String.valueOf(filmList.get(position).getVoteAverage()));
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        Context context;
        ImageView poster;
        TextView title;
        TextView year;
        TextView vote;


        public ViewHolder(View itemView, Context context) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.upcoming_movie_poster);
            title = (TextView) itemView.findViewById(R.id.upcoming_movie_title);
            year = (TextView) itemView.findViewById(R.id.upcoming_movie_year_release);
            vote = (TextView) itemView.findViewById(R.id.upcoming_movie_vote_average);
            this.context = context;
        }
    }
}
