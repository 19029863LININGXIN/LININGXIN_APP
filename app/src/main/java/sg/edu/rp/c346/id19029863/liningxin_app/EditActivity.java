package sg.edu.rp.c346.id19029863.liningxin_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText etID, etName, etDescription, etArea;
    Button btnUpdate, btnDel, btnCancel;
    RatingBar rb;
    //RadioButton r1, r2, r3, r4, r5;
    Island data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID=findViewById(R.id.etID);
        etName=findViewById(R.id.etName);
        etDescription=findViewById(R.id.etDescription);
        etArea=findViewById(R.id.etArea);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDel=findViewById(R.id.btnDel);
        btnCancel=findViewById(R.id.btnCancel);
        rb = findViewById(R.id.rb);
       /* r1 = findViewById(R.id.rgStar1);
        r2 = findViewById(R.id.rgStar2);
        r3 = findViewById(R.id.rgStar3);
        r4 = findViewById(R.id.rgStar4);
        r5 = findViewById(R.id.rgStar5);*/

        Intent i = getIntent();
        data = (Island) i.getSerializableExtra("data");

        etID.setText(String.valueOf(data.getId()));
        etDescription.setText(data.getDescription());
        etName.setText(data.getName());
        etArea.setText(String.valueOf(data.getArea()));

        /*etID.setText(currentIsland.getId() + "");
        etName.setText(currentIsland.getName());
        etDescription.setText(currentIsland.getDescription());
        etArea.setText(currentIsland.getArea()+"");
        rb.setRating(currentIsland.getStars());
*/
        /*if (data.getStars() == 1)
        {
            r1.setChecked(true);
        }
        else if (data.getStars() == 2)
        {
            r2.setChecked(true);
        }
        else if (data.getStars() == 3)
        {
            r3.setChecked(true);
        }
        else if (data.getStars() == 4)
        {
            r4.setChecked(true);
        }
        else if (data.getStars() == 5)
        {
            r5.setChecked(true);
        }
*/

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setName(etName.getText().toString().trim());
                data.setDescription(etDescription.getText().toString().trim());
                int area = 0;
                try {
                    area = Integer.valueOf(etArea.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(EditActivity.this, "Invalid area", Toast.LENGTH_SHORT).show();
                    return;
                }
                data.setArea(area);

                data.setStars((int) rb.getRating());

                int result = dbh.updateIsland(data);
                if (result>0){
                    Toast.makeText(EditActivity.this, "Island updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);

                    dbh.close();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the island \n "+ etName.getText().toString().trim());
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Cancel",null);
                myBuilder.setNeutralButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(EditActivity.this);
                        int result = dbh.deleteIsland(data.getId());
                        if (result>0){
                            Toast.makeText(EditActivity.this, "Island deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        } else {
                            Toast.makeText(EditActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Do not discard",null);
                myBuilder.setNeutralButton("discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });


    }

}