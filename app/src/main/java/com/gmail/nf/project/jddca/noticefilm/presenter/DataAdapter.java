package com.gmail.nf.project.jddca.noticefilm.presenter;

/**
 * Created by bigi on 08.04.2017.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.nf.project.jddca.noticefilm.R;
import com.gmail.nf.project.jddca.noticefilm.model.POJOS.REST.Result.Result;
import com.squareup.picasso.Picasso;

import java.util.List;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private static final String Imeg = "https://image.tmdb.org/t/p/w500";
    private List<Result> android;
    private final Context context;

    public DataAdapter(List<Result> android, Context applicationContext) {
        this.android = android;
        context = applicationContext;
    }


    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(android.get(i).getTitle());
        viewHolder.tv_version.setText(android.get(i).getOverview());
        viewHolder.tv_api_level.setText(android.get(i).getReleaseDate());
        Picasso.with(context)
                .load(Imeg + android.get(i).getBackdropPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_version, tv_api_level;
        private ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_version = (TextView) view.findViewById(R.id.tv_version);
            tv_api_level = (TextView) view.findViewById(R.id.tv_api_level);
            imageView = (ImageView) view.findViewById(R.id.imageView);

            // on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // get position
                    int pos = getAdapterPosition();
                    // check if item still exists
//                    if(pos != RecyclerView.NO_POSITION){
//
//                        ResultNewF clickedDataItem = ResultNewF.get(pos);
//                        Intent intent = new Intent(context, клас полного филм .class);
//                        intent.putExtra("movie_title", ResultNewF.get(pos).getTitle());
//                        intent.putExtra("movie_actors", ResultNewF.get(pos).getActors());
//                        intent.putExtra("movie_cover", ResultNewF.get(pos).getCover());
//                        intent.putExtra("movie_director", ResultNewF.get(pos).getDirector());
//                        intent.putExtra("movie_year", ResultNewF.get(pos).getYear());
//                        intent.putExtra("movie_plot", ResultNewF.get(pos).getPlot());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getTitle(), Toast.LENGTH_SHORT).show();
//                    }
                }
            });
        }
    }


}