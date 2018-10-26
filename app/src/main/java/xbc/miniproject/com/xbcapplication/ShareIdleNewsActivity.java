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

public class ShareIdleNewsActivity extends Activity {
    private Context context = this;

    EditText shareIdleNewsEditTextEmail;

    Button shareIdleNewsButtonSend,
            shareIdleNewsButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_idle_news);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Send to Email");

        shareIdleNewsEditTextEmail = (EditText) findViewById(R.id.shareIdleNewsEditTextEmail);

        shareIdleNewsButtonSend =(Button) findViewById(R.id.shareIdleNewsButtonSend);
        shareIdleNewsButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputValidation();
            }
        });
        shareIdleNewsButtonCancel = (Button) findViewById(R.id.shareIdleNewsButtonCancel);
        shareIdleNewsButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void inputValidation() {
        if (shareIdleNewsEditTextEmail.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Email Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
            SaveSuccessNotification();
        }
    }

    public void SaveSuccessNotification(){
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Testimony Successfully Send !").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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