package sg.edu.rp.c346.id19029863.liningxin_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Island> islandList;

    public CustomAdapter(Context context, int resource, ArrayList<Island> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        islandList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvname = rowView.findViewById(R.id.textViewName);
        TextView tvDesc = rowView.findViewById(R.id.textViewDesc);
        TextView tvArea = rowView.findViewById(R.id.textViewArea);
        //TextView tvStar = rowView.findViewById(R.id.textViewStar);
        RatingBar rbStar=rowView.findViewById(R.id.ratingBar);
        ImageView iv = rowView.findViewById(R.id.imageView);



        // Obtain the Android Version information based on the position
        Island currentIsland = islandList.get(position);

        // Set values to the TextView to display the corresponding information
        tvname.setText(currentIsland.getName());
        tvDesc.setText(currentIsland.getDescription());
        tvArea.setText(String.valueOf(currentIsland.getArea()));
        //tvStar.setText(currentSong.toString());
        rbStar.setRating(currentIsland.getStars());

        if (currentIsland.getArea() <= 13 ) {
            iv.setImageResource(R.drawable.rating_pg13);
        }else if (currentIsland.getArea() <= 16){
            iv.setImageResource(R.drawable.rating_nc16);
        }else if (currentIsland.getArea() <= 18 ){
            iv.setImageResource(R.drawable.rating_m18);}
        else if (currentIsland.getArea() <= 21 ) {
            iv.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}
