package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTrainerActivity extends Activity {
    private Context context=this;
    private EditText editTrainerEditTextName,
            editTrainerEditTexNote;
    private Button editTrainerButtonSave,
            editTrainerButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trainer);


        ActionBar actionBar = getActionBar();
        ((ActionBar)actionBar).setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Edit Trainer");

        editTrainerEditTextName = (EditText) findViewById(R.id.editTrainerEditTextName);
        editTrainerEditTexNote = (EditText) findViewById(R.id.editTrainerEditTexNote);
        editTrainerButtonSave = (Button) findViewById(R.id.editTrainerButtonSave);
        editTrainerButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editValidation();
            }
        });


        editTrainerButtonCancel = (Button) findViewById(R.id.editTrainerButtonCancel);
        editTrainerButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void editValidation(){
        if(editTrainerEditTextName.getText().toString().trim().length()==0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        }else if(editTrainerEditTexNote.getText().toString().trim().length()==0){
            Toast.makeText(context,"Note Field still empty!",Toast.LENGTH_SHORT).show();
        }else{
            //hanya pesan
            saveSuccessfullyNotification();
            Toast.makeText(context,"Data successfully updated!",Toast.LENGTH_SHORT).show();
        }
    }


    public  void saveSuccessfullyNotification(){
        final AlertDialog.Builder builder;
        builder =  new AlertDialog.Builder(context);
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
        if(id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
