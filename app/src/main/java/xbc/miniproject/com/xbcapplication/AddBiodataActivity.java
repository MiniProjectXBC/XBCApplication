package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddBiodataActivity extends Activity {
    Context context = this;

    EditText addBiodataEditTextName,
            addBiodataEditTextLastEducation,
            addBiodataEditTextEducationLevel,
            addBiodataEditTextGraduationYear,
            addBiodataEditTextMajors,
            addBiodataEditTextGpa;

    Button addBiodataButtonSave,
            addBiodataButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biodata);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Input Biodata");

        addBiodataEditTextName = (EditText) findViewById(R.id.addBiodataEditTextName);
        addBiodataEditTextLastEducation = (EditText) findViewById(R.id.addBiodataEditTextLastEducation);
        addBiodataEditTextEducationLevel = (EditText) findViewById(R.id.addBiodataEditTextEducationLevel);
        addBiodataEditTextGraduationYear = (EditText) findViewById(R.id.addBiodataEditTextGraduationYear);
        addBiodataEditTextMajors = (EditText) findViewById(R.id.addBiodataEditTextMajors);
        addBiodataEditTextGpa = (EditText) findViewById(R.id.addBiodataEditTextGpa);

        addBiodataButtonSave = (Button) findViewById(R.id.addBiodataButtonSave);
        addBiodataButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();

            }
        });

        addBiodataButtonCancel = (Button) findViewById(R.id.addBiodataButtonCancel);
        addBiodataButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputValidation() {
        if (addBiodataEditTextName.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addBiodataEditTextLastEducation.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Last Education Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addBiodataEditTextEducationLevel.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Education Level Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addBiodataEditTextGraduationYear.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Graduation Year Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addBiodataEditTextMajors.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Majors Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addBiodataEditTextGpa.getText().toString().trim().length() == 0){
            Toast.makeText(context,"GPA Field still empty!",Toast.LENGTH_SHORT).show();
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
