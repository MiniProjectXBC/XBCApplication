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

public class AddAssignmentActivity extends Activity {
    private Context context = this;

    private EditText addAssignmentEditTextName,
            addAssignmentEditTextTitle,
            addAssignmentEditTextStartDate,
            addAssignmentEditTextEndDate,
            addAssignmentEditTextDescription;

    private Button addAssignmentButtonSave,
            addAssignmentButtonCancel;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Create Assignmnet");

        calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener startDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateStartDate();
            }
        };

        calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener endDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // TODO Auto-generated method stub
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEndDate();
            }
        };

        addAssignmentEditTextName = (EditText) findViewById(R.id.addAssignmentEditTextName);
        addAssignmentEditTextTitle = (EditText) findViewById(R.id.addAssignmentEditTextTitle);
        addAssignmentEditTextDescription = (EditText) findViewById(R.id.addAssignmentEditTextDescription);

        addAssignmentEditTextStartDate = (EditText) findViewById(R.id.addAssignmentEditTextStartDate);
        addAssignmentEditTextStartDate.setFocusable(false);
        addAssignmentEditTextStartDate.setClickable(true);
        addAssignmentEditTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, startDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addAssignmentEditTextEndDate = (EditText) findViewById(R.id.addAssignmentEditTextEndDate);
        addAssignmentEditTextEndDate.setFocusable(false);
        addAssignmentEditTextEndDate.setClickable(true);
        addAssignmentEditTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, endDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addAssignmentButtonSave = (Button) findViewById(R.id.addAssignmentButtonSave);
        addAssignmentButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();

            }
        });

        addAssignmentButtonCancel = (Button) findViewById(R.id.addAssignmentButtonCancel);
        addAssignmentButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateStartDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        addAssignmentEditTextStartDate.setText(sdf.format(calendar.getTime()));
    }

    private void updateEndDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        addAssignmentEditTextEndDate.setText(sdf.format(calendar.getTime()));
    }

    private void inputValidation() {
        if (addAssignmentEditTextName.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addAssignmentEditTextTitle.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Title Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addAssignmentEditTextStartDate.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Start Date Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addAssignmentEditTextEndDate.getText().toString().trim().length() == 0){
            Toast.makeText(context,"End Date Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
            SaveSuccessNotification();
        }
    }

    private void SaveSuccessNotification() {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Data Successfully Added!")
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

        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}