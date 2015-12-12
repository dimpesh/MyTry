package com.movie.app.mytry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by DIMPESH : ${month}
 */
public class MovieAdapter<S> extends ArrayAdapter<String>{
   // private static final String LOG_TAG=MovieAdapter<String>.class.getSimpleName();
    ArrayList<MovieObject>movieObjects;
    public MovieAdapter(Context context,ArrayList<MovieObject>movieObjects)
    {
        super(context,R.layout.image_item ,movieObjects);
        this.movieObjects=movieObjects;
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    @Override
    public MovieObject getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
    MovieObject movieObject=getItem(position);

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.image_item, parent, false);

        if(convertView==null) {
             ImageView imageView= (ImageView) rootView.findViewById(R.id.imageitem);
            Picasso.with(getContext()).load(R.string.baseUrlImage+movieObject.poster_path);

            // Dont know how to collect
        }

        return rootView;
    }

}
