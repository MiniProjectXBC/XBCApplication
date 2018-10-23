package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditIdleMonitoringActivity extends Activity {
    Context context = this;
    
    AutoCompleteTextView editMonitoringEditTextName;
    
    EditText editMonitoringEditTextIdleDate,
            editMonitoringEditTextLastProjectAt,
            editMonitoringEditTextIdleReason;
    
    Button editMonitoringButtonSave,
            editMonitoringButtonCancel;

    boolean isNameSelected = false;

    Calendar calendar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_idle_monitoring);

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

        editMonitoringEditTextName = (AutoCompleteTextView) findViewById(R.id.editMonitoringEditTextName);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, names);
        editMonitoringEditTextName.setThreshold(0);
        editMonitoringEditTextName.setAdapter(adapter);

        editMonitoringEditTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editMonitoringEditTextName.getText().toString().trim().length() == 0){
                    editMonitoringEditTextName.showDropDown();
                }
            }
        });

        editMonitoringEditTextName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isNameSelected = true;
                editMonitoringEditTextName.setError(null);
            }
        });

        editMonitoringEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isNameSelected = false;
                editMonitoringEditTextName.setError("Name must from the list!");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editMonitoringEditTextIdleDate = (EditText) findViewById(R.id.editMonitoringEditTextIdleDate);
        editMonitoringEditTextIdleDate.setFocusable(false);
        editMonitoringEditTextIdleDate.setClickable(true);
        editMonitoringEditTextIdleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        editMonitoringEditTextLastProjectAt = (EditText) findViewById(R.id.editMonitoringEditTextLastProjectAt);
        editMonitoringEditTextIdleReason = (EditText) findViewById(R.id.editMonitoringEditTextIdleReason);

        editMonitoringButtonSave = (Button) findViewById(R.id.editMonitoringButtonSave);
        editMonitoringButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });
        
        editMonitoringButtonCancel =(Button) findViewById(R.id.editMonitoringButtonCancel);
        editMonitoringButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Edit Idle");




    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editMonitoringEditTextIdleDate.setText(sdf.format(calendar.getTime()));
    }

    private void inputValidation() {
        if (editMonitoringEditTextName.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Name Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (editMonitoringEditTextIdleDate.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Idle Date Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (!isNameSelected) {
            editMonitoringEditTextName.setText("");
            Toast.makeText(context, "Name Field Must From the List!", Toast.LENGTH_SHORT).show();
        } else {
            SaveSuccessNotification();
        }
    }

    private void SaveSuccessNotification() {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Idle Successfully Edited!")
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
