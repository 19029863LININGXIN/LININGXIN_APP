package sg.edu.rp.c346.id19029863.liningxin_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Spinner spn;
    Button btnShowAll;
    ListView lvSong;
    ArrayList<Island> al;
    CustomAdapter ca;
    ArrayList<String> years;
    ArrayAdapter<String> aspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        spn = findViewById(R.id.spinner);
        btnShowAll = findViewById(R.id.btnShowStar);
        lvSong = findViewById(R.id.lv);
        al = new ArrayList<Island>();

        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllIsland();
        dbh.close();

        ca = new CustomAdapter(this, R.layout.row, al);
        lvSong.setAdapter(ca);

        years = new ArrayList<String>();
        aspinner = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, years);
        spn.setAdapter(aspinner);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int count = spn.getAdapter().getCount();

                String selectedYear = spn.getSelectedItem().toString();
                int year = Integer.parseInt(selectedYear);

                DBHelper dbh = new DBHelper(ListActivity.this);
                int yr = year;

                for (int i = 0; i < count; i++) {
                    if (position == i) {
                        al.clear();
                        al.addAll(dbh.getAllIslandByArea(yr));
                        ca.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Island data = al.get(position);
                Intent i = new Intent(ListActivity.this,
                        EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListActivity.this);
                al.clear();
                al.addAll(dbh.getAllIslandByStars(5));
                ca.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        al.clear();
        al.addAll(dbh.getAllIsland());
        ca.notifyDataSetChanged();

        years.clear();
        years.addAll(dbh.getArea());
        aspinner.notifyDataSetChanged();

    }

}