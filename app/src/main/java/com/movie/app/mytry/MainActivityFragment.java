package com.movie.app.mytry;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
   // MovieObject movieObject[];
    ArrayList<String> arrayList;

    public MainActivityFragment() {
    }

    String[] food = {"Apple", "pineapple", "Tomato", "Potato", "Spinach"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new FetchMovieTask().execute();
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListAdapter movieAdapter = new MovieAdapter<String>(getActivity(), food);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview);
        movieAdapter= new MovieAdapter(getActivity(),R.layout.image_item,R.id.gridview,arrayList);
        gridView.setAdapter(movieAdapter);
        return rootView;


    }
}
 class FetchMovieTask extends AsyncTask<Void,Void,ArrayList<MovieObject>>
    {

        MovieAdapter movieAdapter;
        @Override
        protected void onPostExecute(ArrayList<MovieObject> movieObjects) {

            if (movieObjects != null) {
                movieAdapter.notifyDataSetChanged();
                GridView gridView=View.inflate(new MainActivityFragment().getContext(),R.layout.image_item,R.layout.fragment_main);
                     gridView.setAdapter(new MovieAdapter<String>(getContext(),result));

            }
            // New data is back from the server.  Hooray!
        }

        }

        MovieObject mObject;
        protected  ArrayList<MovieObject> doInBackground(Void... voids) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String movieJSONStr = null;
            ArrayList <MovieObject>movieObject = null;
            try {
                URL url = new URL(R.string.mainUrl+"sort_by="+R.string.popularity+"&api_key="+R.string.api_key);
              // URL url =new URL("http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=69323240f26aaa3f0ed513e2fd344a5f");
                Log.v("MY URL",url.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    movieJSONStr = null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    movieJSONStr = null;
                }
                movieJSONStr = buffer.toString();
                // Log.v("MY JSON OUTPUT", movieJSONStr);
                /*
                String title=null;
                String overview=null;
                String poster_path=null;
                String =null;
                String title=null;

                */
                JSONObject movieJSONObject =new JSONObject(movieJSONStr);
                JSONArray jsonArray=movieJSONObject.getJSONArray("results");
                for(int i=0;i<jsonArray.length();i++)
                {
                    mObject=new MovieObject();
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    mObject.title=jsonObject.getString("title").toString();
                    mObject.overview=jsonObject.getString("overview").toString();
                    mObject.poster_path=jsonObject.getString("poster_path").toString();
                    mObject.vote_average=jsonObject.getString("vote_average").toString();
                    mObject.release_date=jsonObject.getString("release_date").toString();
                    Log.v("POSTER PATH",jsonObject.getString("poster_path").toString());
                    movieObject.add(mObject);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return movieObject;

        }


    }

