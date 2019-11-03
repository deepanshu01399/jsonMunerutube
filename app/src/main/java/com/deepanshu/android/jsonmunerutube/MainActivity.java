package com.deepanshu.android.jsonmunerutube;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;


import com.deepanshu.android.jsonmunerutube.Models.MovieModel;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

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
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    private TextView tvresult;
    private Button btn;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start
        // Create global configuration and initialize ImageLoader with this config
        btn = (Button) findViewById(R.id.Hit);
        //tvresult = (TextView) findViewById(R.id.tv_result);
        lv = (ListView) findViewById(R.id.tv_result);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMovie c = new getMovie();
                //c.execute("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt");
                //c.execute("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoList.txt");
                c.execute("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt ");
            }
        });
    }

    public class getMovie extends AsyncTask<String, String, List<MovieModel>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected List<MovieModel> doInBackground(String... urls) {
            List<MovieModel> movieModelsList;

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            InputStream stream;
           /* try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();//connecti to server
                //store the data to which we get from the input steam
                stream = connection.getInputStream();//recriee the steram and store the object
                ///br hep ot read
                reader = new BufferedReader(new InputStreamReader(stream));//line by line in text form ate we get data
                String line = "";

                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("movies");
                StringBuffer finalbuffer=new StringBuffer();
                for (int i = 0; i < parentArray.length(); i++) {
                    //JSONObject finalObject=parentArray.getJSONObject(0);//a  for single object
                    JSONObject finalObject = parentArray.getJSONObject(i);//for single object
                    String movieName = finalObject.getString("movie");
                    int year = finalObject.getInt("year");
                    finalbuffer.append(movieName+""+year+"\n");
                }


                //return movieName + " " + year;//a  complete data is transfer of jason form to back grd to post
                return finalbuffer.toString();

            }*/
           /* try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();//connecti to server
                //store the data to which we get from the input steam
                stream = connection.getInputStream();//recriee the steram and store the object
                ///br hep ot read
                reader = new BufferedReader(new InputStreamReader(stream));//line by line in text form ate we get data
                String line = "";

                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("movies");
                movieModelsList = new ArrayList<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);//for single object
                    MovieModel movieModel = new MovieModel();
                    String movieName = finalObject.getString("movie");
                    movieModel.setMovie(movieName);
                    int year = finalObject.getInt("year");
                    movieModel.setYear(year);
                    Float rating = (float) finalObject.getDouble("rating");
                    movieModel.setRating(rating);
                    movieModel.setDirector(finalObject.getString("director"));
                    movieModel.setDuration(finalObject.getString("duration"));
                    movieModel.setTagline(finalObject.getString("tagline"));
                    movieModel.setImage(finalObject.getString("image"));
                    movieModel.setStory(finalObject.getString("story"));

                    List<MovieModel.Cast> castList = new ArrayList<>();
                    for (int j = 0; j < finalObject.getJSONArray("cast").length(); j++) {
                        MovieModel.Cast cast = new MovieModel.Cast();
                        JSONObject castObject = finalObject.getJSONArray("cast").getJSONObject(j);
                        cast.setName(castObject.getString("name"));
                        castList.add(cast);
                    }
                    movieModel.setCastList(castList);
                    movieModelsList.add(movieModel);
                }


                //return movieName + " " + year;//a  complete data is transfer of jason form to back grd to post
                return movieModelsList;

            }*/
            try {
                URL url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();//connecti to server
                //store the data to which we get from the input steam
                stream = connection.getInputStream();//recriee the steram and store the object
                ///br hep ot read
                reader = new BufferedReader(new InputStreamReader(stream));//line by line in text form ate we get data
                String line = "";

                StringBuffer buffer = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                String finalJson = buffer.toString();
                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("movies");
                movieModelsList = new ArrayList<>();
                Gson gson=new Gson();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);//for single object
                    MovieModel movieModel = gson.fromJson(finalObject.toString(),MovieModel.class);
                    movieModelsList.add(movieModel);
                }
                return movieModelsList;

            }catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;

        }

        @Override
        protected void onPostExecute(List<MovieModel> result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            //tvresult.setText(result);
            //todo new to set data to the list
            //user custom/arr/baseadapter for display details
            Myadapter myadapter = new Myadapter(getApplicationContext(), R.layout.row, result);
            lv.setAdapter(myadapter);

        }

    }

    public class Myadapter extends ArrayAdapter {
        List<MovieModel> movieModelList;
        private int resource;
        private LayoutInflater inflater;

        public Myadapter(@NonNull Context context, int resource, List<MovieModel> objects) {
            super(context, resource, objects);
            movieModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);


        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
           /* ImageView ivMovieIcon;
            RatingBar rbMovieRating;
            TextView tvMovie, tvTagline, tvYear, tvDuration, tvDirector, tvCast, tvStory;
            if (convertView == null) {
                convertView = inflater.inflate(resource, null);

                ivMovieIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
                tvMovie = (TextView) convertView.findViewById(R.id.tvMovie);
                tvTagline = (TextView) convertView.findViewById(R.id.tvTagline);
                tvYear = (TextView) convertView.findViewById(R.id.tvYear);
                tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
                tvDirector = (TextView) convertView.findViewById(R.id.tvDirector);
                rbMovieRating = (RatingBar) convertView.findViewById(R.id.rbMovie);
                tvCast = (TextView) convertView.findViewById(R.id.tvCast);
                tvStory = (TextView) convertView.findViewById(R.id.tvStory);
                //final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar);
                final ProgressBar progressBar=(ProgressBar)convertView.findViewById(R.id.progressBar);
                //ImageLoader.getInstance().displayImage(movieModelList.get(position).getImage(), ivMovieIcon);
                ImageLoader.getInstance().displayImage(movieModelList.get(position).getImage(), ivMovieIcon, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        progressBar.setVisibility(VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        progressBar.setVisibility(GONE);

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressBar.setVisibility(GONE);

                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        progressBar.setVisibility(GONE);

                    }
                }); // Default options will be used
                tvMovie.setText(movieModelList.get(position).getMovie());
                tvYear.setText("year" + movieModelList.get(position).getYear());
                tvDuration.setText(movieModelList.get(position).getDuration());
                tvDirector.setText(movieModelList.get(position).getDirector());
                tvTagline.setText(movieModelList.get(position).getTagline());
                rbMovieRating.setRating(movieModelList.get(position).getRating() / 2);
                StringBuffer stringBuffer = new StringBuffer();
                for (MovieModel.Cast cast : movieModelList.get(position).getCastList()) {
                    stringBuffer.append(cast.getName());
                }
                tvCast.setText(stringBuffer);
                tvStory.setText(movieModelList.get(position).getStory());
            }
            return convertView;


        }
*/
            ViewHolder holder = null;

            if (convertView == null) {
                holder=new ViewHolder();
                convertView = inflater.inflate(resource, null);

                holder.ivMovieIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
                holder.tvMovie = (TextView) convertView.findViewById(R.id.tvMovie);
                holder.tvTagline = (TextView) convertView.findViewById(R.id.tvTagline);
                holder.tvYear = (TextView) convertView.findViewById(R.id.tvYear);
                holder.tvDuration = (TextView) convertView.findViewById(R.id.tvDuration);
                holder.tvDirector = (TextView) convertView.findViewById(R.id.tvDirector);
                holder.rbMovieRating = (RatingBar) convertView.findViewById(R.id.rbMovie);
                holder.tvCast = (TextView) convertView.findViewById(R.id.tvCast);
                holder.tvStory = (TextView) convertView.findViewById(R.id.tvStory);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
                //final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar);
                final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);
                //ImageLoader.getInstance().displayImage(movieModelList.get(position).getImage(), ivMovieIcon);
                ImageLoader.getInstance().displayImage(movieModelList.get(position).getImage(), holder.ivMovieIcon, new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        progressBar.setVisibility(VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        progressBar.setVisibility(GONE);

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        progressBar.setVisibility(GONE);

                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {
                        progressBar.setVisibility(GONE);

                    }
                }); // Default options will be used
                holder.tvMovie.setText(movieModelList.get(position).getMovie());
                holder.tvYear.setText("year" + movieModelList.get(position).getYear());
                holder.tvDuration.setText(movieModelList.get(position).getDuration());
                holder.tvDirector.setText(movieModelList.get(position).getDirector());
                holder.tvTagline.setText(movieModelList.get(position).getTagline());
                holder.rbMovieRating.setRating(movieModelList.get(position).getRating() / 2);
                StringBuffer stringBuffer = new StringBuffer();
                for (MovieModel.Cast cast : movieModelList.get(position).getCastList()) {
                    stringBuffer.append(cast.getName());
                }
                holder.tvCast.setText(stringBuffer);
                holder.tvStory.setText(movieModelList.get(position).getStory());

            return convertView;


        }

        class ViewHolder {
            private ImageView ivMovieIcon;
            private RatingBar rbMovieRating;
            private TextView tvMovie, tvTagline, tvYear, tvDuration, tvDirector, tvCast, tvStory;

        }

    }
}
