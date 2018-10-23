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

public class EditAssignmnetActivity extends Activity {
    private Context context = this;

    private EditText editAssignmentEditTextName,
            editAssignmentEditTextTitle,
            editAssignmentEditTextStartDate,
            editAssignmentEditTextEndDate,
            editAssignmentEditTextDescription;

    private Button editAssignmentButtonSave,
            editAssignmentButtonCancel;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignmnet);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Update Assignmnet");

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

        editAssignmentEditTextName = (EditText) findViewById(R.id.editAssignmentEditTextName);
        editAssignmentEditTextTitle = (EditText) findViewById(R.id.editAssignmentEditTextTitle);
        editAssignmentEditTextDescription = (EditText) findViewById(R.id.editAssignmentEditTextDescription);

        editAssignmentEditTextStartDate = (EditText) findViewById(R.id.editAssignmentEditTextStartDate);
        editAssignmentEditTextStartDate.setFocusable(false);
        editAssignmentEditTextStartDate.setClickable(true);
        editAssignmentEditTextStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, startDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editAssignmentEditTextEndDate = (EditText) findViewById(R.id.editAssignmentEditTextEndDate);
        editAssignmentEditTextEndDate.setFocusable(false);
        editAssignmentEditTextEndDate.setClickable(true);
        editAssignmentEditTextEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, endDate, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        editAssignmentButtonSave = (Button) findViewById(R.id.editAssignmentButtonSave);
        editAssignmentButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();

            }
        });

        editAssignmentButtonCancel = (Button) findViewById(R.id.editAssignmentButtonCancel);
        editAssignmentButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateStartDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editAssignmentEditTextStartDate.setText(sdf.format(calendar.getTime()));
    }

    private void updateEndDate() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editAssignmentEditTextEndDate.setText(sdf.format(calendar.getTime()));
    }

    private void inputValidation() {
        if (editAssignmentEditTextName.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editAssignmentEditTextTitle.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Title Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editAssignmentEditTextStartDate.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Start Date Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editAssignmentEditTextEndDate.getText().toString().trim().length() == 0){
            Toast.makeText(context,"End Date Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
            SaveSuccessNotification();
        }
    }

    private void SaveSuccessNotification() {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Data Successfully Update!")
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