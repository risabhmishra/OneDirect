package risabhmishra.com.onedirect_flightsearch;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class flight_details extends AppCompatActivity {
    dbcontroller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);

        Intent intent = getIntent();
        String src_in = intent.getStringExtra("org");
        String dest_out = intent.getStringExtra("dest");
        int nop = Integer.parseInt(intent.getStringExtra("nop"));

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<flight> alf = new ArrayList<>();

        controller = new dbcontroller(this,"",null,1);
        controller.insertdata("0","Kolkata","New-Delhi","17:50","20:15","2h 25m","2600");
        controller.insertdata("1","Mumbai","New-Delhi","17:50","20:15","2h 25m","2600");
        controller.insertdata("2","Hyderabad","Bengaluru","17:50","20:15","2h 25m","2600");
        controller.insertdata("3","Kolkata","Mumbai","17:50","20:15","2h 25m","2600");
        controller.insertdata("3","Mumbai","New-Delhi","17:50","20:15","2h 25m","2600");
        controller.insertdata("2","Hyderabad","Bengaluru","17:50","20:15","2h 25m","2600");
        controller.insertdata("1","Kolkata","Mumbai","17:50","20:15","2h 25m","2600");
        controller.insertdata("0","Chennai","Chandigarh","17:50","20:15","2h 25m","2600");


        Cursor c = controller.getflights(src_in,dest_out);
        while (c.moveToNext()){
            int prices = Integer.parseInt(c.getString(4))*nop;
            alf.add(new flight(c.getString(0),c.getString(1),
                c.getString(2),c.getString(3),Integer.toString(prices)));
        }

        recyclerView.setAdapter(new flight_adapter(alf));

    }

}
