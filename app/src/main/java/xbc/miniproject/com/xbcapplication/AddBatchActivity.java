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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.model.batch.DataList;
import xbc.miniproject.com.xbcapplication.model.batch.ModelBatch;
import xbc.miniproject.com.xbcapplication.model.batch.Technology;
import xbc.miniproject.com.xbcapplication.model.batch.Trainer;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class AddBatchActivity extends Activity {
    Context context = this;

    EditText addBatchEditTextTechnology, addBatchEditTextTrainer,
            addBatchEditTextName, addBatchEditTextPeriodForm,
            addBatchEditTextPeriodTo, addBatchEditTextRoom,
            addBatchEditTextNotes;

    Spinner spinnerBatchType;

    DataList data = new DataList();

    Button addBatchButtonSave, addBatchButtonCancel;

    RequestAPIServices apiServices;

    String[] arrayType = {
            "- Pilih Type -","Gratis","Reguler"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);

        ActionBar actionBar = getActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);

        addBatchEditTextTechnology = (EditText) findViewById(R.id.addBatchEditTextTechnology);
        addBatchEditTextTrainer = (EditText) findViewById(R.id.addBatchEditTextTrainer);
        addBatchEditTextName = (EditText) findViewById(R.id.addBatchEditTextName);

        Calendar today = Calendar.getInstance();
        final int yearStart = today.get(Calendar.YEAR);
        final int monthStart = today.get(Calendar.MONTH);
        final int dayStart = today.get(Calendar.DATE);
        addBatchEditTextPeriodForm = (EditText) findViewById(R.id.addBatchEditTextPeriodForm);
        addBatchEditTextPeriodForm.setFocusable(false);
        addBatchEditTextPeriodForm.setClickable(true);
        addBatchEditTextPeriodForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        R.style.DatePickerPeriod, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selected = Calendar.getInstance();
                        selected.set(year,month,dayOfMonth);

                        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
                        String periodForm = formatDate.format(selected.getTime());

                        addBatchEditTextPeriodForm.setText(periodForm);
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
        spinnerBatchType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data.setBootcampType(spinnerBatchType.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        addBatchEditTextPeriodTo = (EditText) findViewById(R.id.addBatchEditTextPeriodTo);
        addBatchEditTextPeriodTo.setFocusable(false);
        addBatchEditTextPeriodTo.setClickable(true);
        addBatchEditTextPeriodTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        R.style.DatePickerPeriod, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selected = Calendar.getInstance();
                        selected.set(year,month,dayOfMonth);

                        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
                        String periodTo = formatDate.format(selected.getTime());

                        addBatchEditTextPeriodTo.setText(periodTo);
                    }
                }, yearStart, monthStart, dayStart
                );
                datePickerDialog.getDatePicker().setSpinnersShown(true);
                datePickerDialog.getDatePicker().setCalendarViewShown(false);
                datePickerDialog.show();
            }
        });

        addBatchEditTextRoom = (EditText) findViewById(R.id.addBatchEditTextRoom);

        addBatchEditTextNotes = (EditText) findViewById(R.id.addBatchEditTextNotes);

        addBatchButtonSave = (Button) findViewById(R.id.addBatchButtonSave);
        addBatchButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        addBatchButtonCancel = (Button) findViewById(R.id.addBatchButtonCancel);
        addBatchButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputValidation() {
        if (addBatchEditTextTechnology.getText().toString().trim().length() == 0){
            Toast.makeText(context, "Technology Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (addBatchEditTextTrainer.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Trainer Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (addBatchEditTextName.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Name Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (addBatchEditTextPeriodForm.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Period form Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (addBatchEditTextPeriodTo.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Period to Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (addBatchEditTextRoom.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Room Field still empty!", Toast.LENGTH_SHORT).show();
        } else if (addBatchEditTextNotes.getText().toString().trim().length() == 0) {
            Toast.makeText(context, "Notes Field still empty!", Toast.LENGTH_SHORT).show();
        } else{
//            SaveSuccessNotification();
            createBatch();
        }
    }

    private void createBatch(){
        apiServices = APIUtilities.getAPIServices();


//        Technology data2 = new Technology();
//        Trainer data3 = new Trainer();
//
//        data2.setName(addBatchEditTextTechnology.getText().toString());
//        data3.setName(addBatchEditTextTrainer.getText().toString());
//        data.setName(addBatchEditTextName.getText().toString());
//        data.setPeriodFrom(addBatchEditTextPeriodForm.getText().toString());
//        data.setPeriodTo(addBatchEditTextPeriodTo.getText().toString());
//        data.setRoom(addBatchEditTextRoom.getText().toString());
////        data.setBootcampType(spinnerBatchType.getItemAtPosition()); //Belum ada getText //getAlignment :1, //getAdapter posisi array
//        data.setNotes(addBatchEditTextNotes.getText().toString());



        apiServices.createNewBatch("application/json", data)
                .enqueue(new Callback<ModelBatch>() {
                    @Override
                    public void onResponse(Call<ModelBatch> call, Response<ModelBatch> response) {
                        if(response.code() == 201){
                            String message = response.body().getMessage();
                            if(message!=null){
                                SaveSuccessNotification(message);
                            } else {
                                SaveSuccessNotification("Data Gagal Ditambahkan");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ModelBatch> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void SaveSuccessNotification(String message) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage(message+"!")
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
