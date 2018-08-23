package risabhmishra.com.onedirect_flightsearch;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.dpizarro.uipicker.library.picker.PickerUI;
import com.dpizarro.uipicker.library.picker.PickerUISettings;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView dateTextView;
    PickerUI mPickerUI;
    List<String> options;
    ImageButton reverse_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button from_but  = (Button)findViewById(R.id.from_button);
        final Button to_but = (Button)findViewById(R.id.to_button);
        Button date_but = (Button)findViewById(R.id.date_button);
        dateTextView = (TextView)findViewById(R.id.date_display);
        reverse_but = (ImageButton)findViewById(R.id.reverse_but);


        date_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        reverse_but.setImageResource(R.drawable.flightswitch);
        reverse_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curr_src = from_but.getText().toString();
                String curr_dest = to_but.getText().toString();
                from_but.setText(curr_dest);
                to_but.setText(curr_src);
            }
        });

        mPickerUI = (PickerUI) findViewById(R.id.picker_ui_view);

        String airports[] = {"Kolkata","New-Delhi","Mumbai","Chennai","Bengaluru","Hyderabad","Pune","Chandigarh"};


        options = Arrays.asList(airports);

        from_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickerUISettings pickerUISettings = new PickerUISettings.Builder()
                        .withItems(options)
                        .withAutoDismiss(true)
                        .withItemsClickables(false)
                        .withUseBlur(false)
                        .build();

                mPickerUI.setSettings(pickerUISettings);
                mPickerUI.slide(8);

                mPickerUI.setOnClickItemPickerUIListener(new PickerUI.PickerUIItemClickListener() {
                    @Override
                    public void onItemClickPickerUI(int which, int position, String valueResult) {
                        //Toast.makeText(MainActivity.this, valueResult, Toast.LENGTH_SHORT).show();
                        from_but.setText(valueResult);
                    }
                });
            }
        });

        to_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickerUISettings pickerUISettings = new PickerUISettings.Builder()
                        .withItems(options)
                        .withAutoDismiss(true)
                        .withItemsClickables(false)
                        .withUseBlur(false)
                        .build();

                mPickerUI.setSettings(pickerUISettings);
                mPickerUI.slide(8);

                mPickerUI.setOnClickItemPickerUIListener(new PickerUI.PickerUIItemClickListener() {
                    @Override
                    public void onItemClickPickerUI(int which, int position, String valueResult) {
                        //Toast.makeText(MainActivity.this, valueResult, Toast.LENGTH_SHORT).show();
                        to_but.setText(valueResult);
                    }
                });
            }
        });


        final ElegantNumberButton elegantNumberButton = (ElegantNumberButton) findViewById(R.id.number_button);

        //elegantNumberButton.updateColors(Color.WHITE, Color.BLACK);
        elegantNumberButton.setRange(1, 5);
        elegantNumberButton.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = elegantNumberButton.getNumber();
                elegantNumberButton.setNumber(number);
            }
        });
        elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                Log.d("", String.format("oldValue: %d   newValue: %d", oldValue, newValue));
            }
        });

        final ProSwipeButton proSwipeBtn = (ProSwipeButton) findViewById(R.id.awesome_btn);
        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                        // task success! show TICK icon in ProSwipeButton
                        String origin = from_but.getText().toString();
                        String destination = to_but.getText().toString();
                        String date_of_travel = dateTextView.getText().toString();
                        String no_of_pass = elegantNumberButton.getNumber();
                        Intent intent = new Intent(MainActivity.this,flight_details.class);
                        intent.putExtra("org",origin);
                        intent.putExtra("dest",destination);
                        intent.putExtra("nop",no_of_pass);
                        startActivity(intent);
                        //proSwipeBtn.showResultIcon(true); // false if task fails
            }
        });

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
        dateTextView.setText(date);
    }
}
