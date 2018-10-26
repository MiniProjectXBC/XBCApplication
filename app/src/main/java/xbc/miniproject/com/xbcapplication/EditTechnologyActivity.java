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

public class EditTechnologyActivity extends Activity {
    private Context context=this;
    private EditText editTechnologyEditTextName,
            editTechnologyEditTexNote;
    private Button editTechnologyButtonSave,
            editTechnologyButtonCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_technology);

        ActionBar actionBar =  getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Edit Technology");

        editTechnologyEditTextName = (EditText)findViewById(R.id.editTechnologyEditTextName);
        editTechnologyEditTexNote = (EditText) findViewById(R.id.editTechnologyEditTexNote);
        editTechnologyButtonSave = (Button) findViewById(R.id.editTechnologyButtonSave);
        editTechnologyButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editValidation();
            }
        });
        editTechnologyButtonCancel =(Button)findViewById(R.id.editTechnologyButtonCancel);
        editTechnologyButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void editValidation(){
        if(editTechnologyEditTextName.getText().toString().trim().length()==0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        }else if(editTechnologyEditTexNote.getText().toString().trim().length()==0){
            Toast.makeText(context,"Note Field still empty!",Toast.LENGTH_SHORT).show();
        }else{
            //hanya pesan
            saveSuccessfullyNotification();
            Toast.makeText(context,"Testimony successfully updated!",Toast.LENGTH_SHORT).show();
        }
    }
    public  void saveSuccessfullyNotification(){
        final AlertDialog.Builder builder;
        builder =  new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Testimony Successfully Updated!")
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
