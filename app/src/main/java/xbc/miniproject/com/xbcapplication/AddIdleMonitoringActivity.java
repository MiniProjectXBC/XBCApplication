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

import xbc.miniproject.com.xbcapplication.R;

public class AddIdleMonitoringActivity extends Activity {
    Context context = this;

    EditText addMonitoringEditTextName,
            addMonitoringEditTextIdleDate,
            addMonitoringEditTextLastProjectAt,
            addMonitoringEditTextIdleReason;

    Button addMonitoringButtonSave,
            addMonitoringButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monitoring);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Input Idle");

        addMonitoringEditTextName = (EditText) findViewById(R.id.addMonitoringEditTextName);
        addMonitoringEditTextIdleDate = (EditText) findViewById(R.id.addMonitoringEditTextIdleDate);
        addMonitoringEditTextLastProjectAt = (EditText) findViewById(R.id.addMonitoringEditTextLastProjectAt);
        addMonitoringEditTextIdleReason = (EditText) findViewById(R.id.addMonitoringEditTextIdleReason);

        addMonitoringButtonSave = (Button) findViewById(R.id.addMonitoringButtonSave);
        addMonitoringButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        addMonitoringButtonCancel = (Button) findViewById(R.id.addMonitoringButtonCancel);
        addMonitoringButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void inputValidation() {
        if (addMonitoringEditTextName.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addMonitoringEditTextIdleDate.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Idle Date Field still empty!",Toast.LENGTH_SHORT).show();
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
