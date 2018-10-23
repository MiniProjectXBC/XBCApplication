package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPlacementMonitoringActivity extends Activity {
    Context context = this;

    EditText placementEditTextPlacementDate,
            placementEditTextPlacementAt,
            placementEditTextNotes;

    Button placementButtonSave,
            placementButtonCancel;

    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_placement_monitoring);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Input Placement");

        calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        placementEditTextPlacementDate = (EditText) findViewById(R.id.placementEditTextPlacementDate);
        placementEditTextPlacementDate.setFocusable(false);
        placementEditTextPlacementDate.setClickable(true);
        placementEditTextPlacementDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        placementEditTextPlacementAt = (EditText) findViewById(R.id.placementEditTextPlacementAt);
        placementEditTextNotes = (EditText) findViewById(R.id.placementEditTextNotes);

        placementButtonSave = (Button) findViewById(R.id.placementButtonSave);
        placementButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        placementButtonCancel = (Button) findViewById(R.id.placementButtonCancel);
        placementButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        placementEditTextPlacementDate.setText(sdf.format(calendar.getTime()));
    }

    private void inputValidation() {
        if (placementEditTextPlacementDate.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Placement Date Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (placementEditTextPlacementAt.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Placement At Field still empty!", Toast.LENGTH_SHORT).show();
        } else {
            SaveSuccessNotification();
        }
    }

    private void SaveSuccessNotification() {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Placement Successfully Added!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setCancelable(false)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
