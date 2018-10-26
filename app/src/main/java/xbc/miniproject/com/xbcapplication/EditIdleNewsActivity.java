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

public class EditIdleNewsActivity extends Activity {
    private Context context = this;

    EditText editIdleNewsEditTextTitle,
            editIdleNewsEditTextCategory,
            editIdleNewsEditTextContent;

    Button editIdleNewsButtonSave,
            editIdleNewsButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_idle_news);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Edit Idle News");

        editIdleNewsEditTextTitle = (EditText) findViewById(R.id.editIdleNewsEditTextTitle);
        editIdleNewsEditTextCategory = (EditText) findViewById(R.id.editIdleNewsEditTextCategory);
        editIdleNewsEditTextContent = (EditText) findViewById(R.id.editIdleNewsEditTextContent);

        editIdleNewsButtonSave =(Button) findViewById(R.id.editIdleNewsButtonSave);
        editIdleNewsButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidation();
            }
        });
        editIdleNewsButtonCancel = (Button) findViewById(R.id.editIdleNewsButtonCancel);
        editIdleNewsButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputValidation() {
        if (editIdleNewsEditTextTitle.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Title Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editIdleNewsEditTextCategory.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Category Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
            SaveSuccessNotification();
        }
    }

    public void SaveSuccessNotification(){
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Testimony Successfully Update !").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        })
                .setCancelable(false).show();
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