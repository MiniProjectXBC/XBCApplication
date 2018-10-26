package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

import xbc.miniproject.com.xbcapplication.model.user.DataList;
import xbc.miniproject.com.xbcapplication.model.user.Role;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class AddUserActivity extends Activity {
    private Context context = this;
    private EditText addUserEditTexUsername,
            addUserEditTexPassword,
            addUserEditTexRetypePassword;
    private AutoCompleteTextView addUserEditTextRole;
    private Button addUserButtonSave;
    private Button addUserButtonCancel;
    private String[] roles ;
    private boolean isRoleSelected;
    private RequestAPIServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        addUserEditTexUsername = (EditText) findViewById(R.id.addUserEditTexUsername);
        addUserEditTexPassword = (EditText) findViewById(R.id.addUserEditTexPassword);
        addUserEditTexRetypePassword = (EditText) findViewById(R.id.addUserEditTexRetypePassword);
        addUserEditTextRole =  (AutoCompleteTextView) findViewById(R.id.addUserEditTextRole);

        getRolefromApi();

        final ArrayAdapter<String> adapter =  new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, roles);
        addUserEditTextRole.setThreshold(0);
        addUserEditTextRole.setAdapter(adapter);
        addUserEditTextRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addUserEditTextRole.getText().toString().trim().length()==0){
                    addUserEditTextRole.showDropDown();
                }
            }
        });
        addUserEditTextRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isRoleSelected =  true;
                addUserEditTextRole.setError(null);
            }
        });
        addUserEditTextRole.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(addUserEditTextRole.getText().toString().trim().length()==0){
                    addUserEditTextRole.setError(null);
                }else{
                    isRoleSelected =  false;
                    addUserEditTextRole.setError("Role Must from the list !");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        addUserButtonSave  =  (Button) findViewById(R.id.addUserButtonSave);
        addUserButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataValidtaion();
            }
        });
        addUserButtonCancel =  (Button) findViewById(R.id.addUserButtonCancel);
        addUserButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void addDataValidtaion(){
        if(addUserEditTextRole.getText().toString().trim().length()==0){
            Toast.makeText(context,"Role field still empty !", Toast.LENGTH_SHORT).show();
        }else if(addUserEditTexUsername.getText().toString().trim().length()==0){
            Toast.makeText(context,"Username field still empty !", Toast.LENGTH_SHORT).show();
        }else if(addUserEditTexPassword.getText().toString().trim().length()==0){
            Toast.makeText(context, "Password field still empty !", Toast.LENGTH_SHORT).show();
        }else if(addUserEditTexRetypePassword.getText().toString().trim().length()== 0){
            Toast.makeText(context, "Please Retype password !", Toast.LENGTH_SHORT).show();
        }else if(isRoleSelected ==false){
            Toast.makeText(context, "Role Must from the list!", Toast.LENGTH_SHORT).show();
        }else{
            final String pas = addUserEditTexPassword.getText().toString();
            final String repas = addUserEditTexRetypePassword.getText().toString();
            if(pas.equalsIgnoreCase(repas)){
                panggilAPI();
            }else{
                Toast.makeText(context, "Password Tidak Sama!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void getRolefromApi(){
//        apiServices =  APIUtilities.getAPIServices();
//        IdleNewsList data = new IdleNewsList();
//        DataListTestimony data = new DataListTestimony();
//        Role role = new Role();
//        for(role.getId()!){
//
//        }
    }
    public void panggilAPI(){
        saveDataNotification();
        DataList dataUser = new DataList();
        dataUser.setUsername(addUserEditTexUsername.getText().toString());
        dataUser.setPassword(addUserEditTexPassword.getText().toString());
        dataUser.setMRoleId(addUserEditTextRole);
    }
    private void saveDataNotification(){
            final AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(context);
            builder.setTitle("NOTIFICATION !")
                    .setMessage("Testimony Successfully added !")
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
}
