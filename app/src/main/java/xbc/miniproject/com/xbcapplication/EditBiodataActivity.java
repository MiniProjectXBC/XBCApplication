package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import xbc.miniproject.com.xbcapplication.R;

public class EditBiodataActivity extends Activity {

    Context context = this;

    EditText editBiodataEditTextName,
            editBiodataEditTextLastEducation,
            editBiodataEditTextEducationLevel,
            editBiodataEditTextGraduationYear,
            editBiodataEditTextMajors,
            editBiodataEditTextGpa;

    Button editBiodataButtonSave,
            editBiodataButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_biodata);

        ActionBar actionBar = getActionBar();
        ((ActionBar) actionBar).setDisplayHomeAsUpEnabled(true);

        editBiodataEditTextName = (EditText) findViewById(R.id.editBiodataEditTextName);
        editBiodataEditTextLastEducation = (EditText) findViewById(R.id.editBiodataEditTextLastEducation);
        editBiodataEditTextEducationLevel = (EditText) findViewById(R.id.editBiodataEditTextEducationLevel);
        editBiodataEditTextGraduationYear = (EditText) findViewById(R.id.editBiodataEditTextGraduationYear);
        editBiodataEditTextMajors = (EditText) findViewById(R.id.editBiodataEditTextMajors);
        editBiodataEditTextGpa = (EditText) findViewById(R.id.editBiodataEditTextGpa);

        editBiodataButtonSave = (Button) findViewById(R.id.editBiodataButtonSave);
        editBiodataButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();

            }
        });

        editBiodataButtonCancel = (Button) findViewById(R.id.editBiodataButtonCancel);
        editBiodataButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void inputValidation() {
        if (editBiodataEditTextName.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editBiodataEditTextLastEducation.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Last Education Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editBiodataEditTextEducationLevel.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Education Level Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editBiodataEditTextGraduationYear.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Graduation Year Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editBiodataEditTextMajors.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Majors Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editBiodataEditTextGpa.getText().toString().trim().length() == 0){
            Toast.makeText(context,"GPA Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
            SaveSuccessNotification();
        }
    }

    private void SaveSuccessNotification() {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Data Successfully Updated!")
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
