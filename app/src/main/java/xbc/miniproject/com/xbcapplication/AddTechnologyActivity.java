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

public class AddTechnologyActivity extends Activity {
    private Context context =this;
    private EditText addTechnologyEditTextName
            ,addTechnologyEditTexNote;
    private Button addTechnologyButtonSave,
            addTechnologyButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_technology);

        ActionBar actionBar = getActionBar();
        ((ActionBar)actionBar).setDisplayHomeAsUpEnabled(true);

        addTechnologyEditTextName = (EditText) findViewById(R.id.addTechnologyEditTextName);
        addTechnologyEditTexNote = (EditText) findViewById(R.id.addTechnologyEditTexNote);
        addTechnologyButtonSave = (Button) findViewById(R.id.addTechnologyButtonSave);
        addTechnologyButtonCancel = (Button) findViewById(R.id.addTechnologyButtonCancel);

        //button save data
        addTechnologyButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        //button cancel
        addTechnologyButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void inputValidation(){
        if(addTechnologyEditTextName.getText().toString().trim().length()==0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        }else if (addTechnologyEditTexNote.getText().toString().trim().length()==0){
            Toast.makeText(context,"Note Field still empty!",Toast.LENGTH_SHORT).show();
        }else{

            saveSuccesNotification();
        }
    }
    public void saveSuccesNotification(){
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Data Successfully Added! ")
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
