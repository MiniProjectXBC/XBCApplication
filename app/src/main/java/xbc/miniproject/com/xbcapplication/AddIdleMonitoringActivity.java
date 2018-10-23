package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xbc.miniproject.com.xbcapplication.R;

public class AddIdleMonitoringActivity extends AppCompatActivity {
    private Context context = this;

    private EditText addMonitoringEditTextIdleDate,
            addMonitoringEditTextLastProjectAt,
            addMonitoringEditTextIdleReason;

    private AutoCompleteTextView addMonitoringEditTextName;

    private Button addMonitoringButtonSave,
            addMonitoringButtonCancel;

    private String[] names = {"Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Agus",
            "Bagus",
            "Cagus",
            "Dagus",
            "Eagus"};

    private boolean isNameSelected;

    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_idle_monitoring);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Input Idle");

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

        addMonitoringEditTextName = (AutoCompleteTextView) findViewById(R.id.addMonitoringEditTextName);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, names);
        addMonitoringEditTextName.setThreshold(0);
        addMonitoringEditTextName.setAdapter(adapter);

        addMonitoringEditTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (names.length > 0){
                    if (!addMonitoringEditTextName.getText().toString().equals(""))
                        adapter.getFilter().filter(null);
                    addMonitoringEditTextName.showDropDown();
                }
                return false;
            }
        });

        addMonitoringEditTextName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isNameSelected = true;
                addMonitoringEditTextName.setError(null);
            }
        });

        addMonitoringEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isNameSelected = false;
                addMonitoringEditTextName.setError("Name must from the list!");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        addMonitoringEditTextIdleDate = (EditText) findViewById(R.id.addMonitoringEditTextIdleDate);
        addMonitoringEditTextIdleDate.setFocusable(false);
        addMonitoringEditTextIdleDate.setClickable(true);
        addMonitoringEditTextIdleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        addMonitoringEditTextLastProjectAt = (EditText) findViewById(R.id.addMonitoringEditTextLastProjectAt);
        addMonitoringEditTextIdleReason = (EditText) findViewById(R.id.addMonitoringEditTextIdleReason);

        addMonitoringButtonSave = (Button) findViewById(R.id.addMonitoringButtonSave);
        addMonitoringButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        addMonitoringButtonCancel = (Button) findViewById(R.id.addMonitoringButtonCancel);
        addMonitoringButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        addMonitoringEditTextIdleDate.setText(sdf.format(calendar.getTime()));
    }

    private void inputValidation() {
        if (addMonitoringEditTextName.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Name Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (addMonitoringEditTextIdleDate.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Idle Date Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (!isNameSelected) {
            addMonitoringEditTextName.setText("");
            Toast.makeText(context, "Name Field Must From the List!", Toast.LENGTH_SHORT).show();
        } else {
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

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
