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

public class AddIdleNewsActivity extends Activity {
    private Context context = this;

    EditText addIdleNewsEditTextTitle,
            addIdleNewsEditTextCategory,
            addIdleNewsEditTextContent;

    Button addIdleNewsButtonSave,
            addIdleNewsButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_idle_news);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Input Idle News");

        addIdleNewsEditTextTitle = (EditText) findViewById(R.id.addIdleNewsEditTextTitle);
        addIdleNewsEditTextCategory = (EditText) findViewById(R.id.addIdleNewsEditTextCategory);
        addIdleNewsEditTextContent = (EditText) findViewById(R.id.addIdleNewsEditTextContent);

        addIdleNewsButtonSave =(Button) findViewById(R.id.addIdleNewsButtonSave);
        addIdleNewsButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidation();
            }
        });

        addIdleNewsButtonCancel = (Button) findViewById(R.id.addIdleNewsButtonCancel);
        addIdleNewsButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputValidation() {
        if (addIdleNewsEditTextTitle.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Title Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addIdleNewsEditTextCategory.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Category Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
            SaveSuccessNotification();
        }
    }

    public void SaveSuccessNotification(){
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Testimony Successfully Added!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
