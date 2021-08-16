package sg.edu.rp.c346.id19029863.liningxin_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etname, etdescription, etSquareKM;
    Button btnInsert, btnShow;
    RatingBar rb;
    RadioButton r1, r2, r3, r4, r5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etname = findViewById(R.id.etName);
        etdescription = findViewById(R.id.etDescription);
        etSquareKM = findViewById(R.id.etArea);
        rb = findViewById(R.id.ratingBar);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
        /*r1 = findViewById(R.id.rgStar1);
        r2 = findViewById(R.id.rgStar2);
        r3 = findViewById(R.id.rgStar3);
        r4 = findViewById(R.id.rgStar4);
        r5 = findViewById(R.id.rgStar5);*/

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etname.getText().toString().trim();
                String singers = etdescription.getText().toString().trim();
                if (title.length() == 0 || singers.length() == 0) {
                    Toast.makeText(MainActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                String year_str = etSquareKM.getText().toString().trim();
                int year = 0;
                try {
                    year = Integer.valueOf(year_str);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                String star = String.valueOf(getStars());
                String s = String.valueOf(star.charAt(0));
                int stars = Integer.parseInt(s);
                dbh.insertIsland(title, singers, year, stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
            }

        });

    }
    private float getStars()
    {
        float stars = rb.getRating();
        return stars;
    }
}