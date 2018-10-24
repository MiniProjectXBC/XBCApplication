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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditBatchActivity extends Activity {
    Context context = this;

    EditText editBatchEditTextTechnology, editBatchEditTextTrainer,
            editBatchEditTextName, editBatchEditTextPeriodForm,
            editBatchEditTextPeriodTo, editBatchEditTextRoom,
            editBatchEditTextNotes;
    Spinner spinnerBatchType;
    Button editBatchButtonSave, editBatchButtonCancel;

    String[] arrayType = {
            "- Pilih Type -","Gratis","Berbayar"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_batch);

        ActionBar actionBar = getActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);

        editBatchEditTextTechnology = (EditText) findViewById(R.id.editBatchEditTextTechnology);
        editBatchEditTextTrainer = (EditText) findViewById(R.id.editBatchEditTextTrainer);
        editBatchEditTextName = (EditText) findViewById(R.id.editBatchEditTextName);

        Calendar today = Calendar.getInstance();
        final int yearStart = today.get(Calendar.YEAR);
        final int monthStart = today.get(Calendar.MONTH);
        final int dayStart = today.get(Calendar.DATE);
        editBatchEditTextPeriodForm = (EditText) findViewById(R.id.editBatchEditTextPeriodForm);
        editBatchEditTextPeriodForm.setFocusable(false);
        editBatchEditTextPeriodForm.setClickable(true);
        editBatchEditTextPeriodForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        R.style.DatePickerPeriod, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selected = Calendar.getInstance();
                        selected.set(year,month,dayOfMonth);

                        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                        String periodForm = formatDate.format(selected.getTime());

                        editBatchEditTextPeriodForm.setText(periodForm);
                    }
                }, yearStart, monthStart, dayStart
                );
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.show();
            }
        });

        spinnerBatchType = (Spinner) findViewById(R.id.spinnerBatchType);

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item,
                arrayType);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBatchType.setAdapter(adapterType);

        editBatchEditTextPeriodTo = (EditText) findViewById(R.id.editBatchEditTextPeriodTo);
        editBatchEditTextPeriodTo.setFocusable(false);
        editBatchEditTextPeriodTo.setClickable(true);
        editBatchEditTextPeriodTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        R.style.DatePickerPeriod, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selected = Calendar.getInstance();
                        selected.set(year,month,dayOfMonth);

                        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                        String periodTo = formatDate.format(selected.getTime());

                        editBatchEditTextPeriodTo.setText(periodTo);
                    }
                }, yearStart, monthStart, dayStart
                );
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.show();
            }
        });

        editBatchEditTextRoom = (EditText) findViewById(R.id.editBatchEditTextRoom);

        editBatchEditTextNotes = (EditText) findViewById(R.id.editBatchEditTextNotes);

        editBatchButtonSave = (Button) findViewById(R.id.editBatchButtonSave);
        editBatchButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        editBatchButtonCancel = (Button) findViewById(R.id.editBatchButtonCancel);
        editBatchButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputValidation() {
        if (editBatchEditTextTechnology.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Technology Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (editBatchEditTextTrainer.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Trainer Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (editBatchEditTextName.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Name Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (editBatchEditTextPeriodForm.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Period form Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (editBatchEditTextPeriodTo.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Period to Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (editBatchEditTextRoom.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Room Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (editBatchEditTextNotes.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Notes Field still empty!", Toast.LENGTH_SHORT).show();
        } else{
            SaveSuccessNotification();
        }
    }

    private void SaveSuccessNotification() {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Data Successfully Edited!")
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
