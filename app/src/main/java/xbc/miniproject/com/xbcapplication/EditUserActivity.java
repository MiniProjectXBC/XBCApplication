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

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.model.role.DataListRole;
import xbc.miniproject.com.xbcapplication.model.role.ModelRole;
import xbc.miniproject.com.xbcapplication.model.user.DataList;
import xbc.miniproject.com.xbcapplication.model.user.ModelUser;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;
import xbc.miniproject.com.xbcapplication.utility.Constanta;
import xbc.miniproject.com.xbcapplication.utility.SessionManager;

public class EditUserActivity extends Activity {
    private Context context =  this;
    private EditText editUserEditTexUsername,
            editUserEditTexPassword,
            editUserEditTexRetypePassword;
    private AutoCompleteTextView editUserEditTextRole;
    private Button editUserButtonSave;
    private  Button editUserButtonCancel;
    private ArrayList<String> listRole = new ArrayList<String>();
    private List<DataListRole> dataListRoles =  new ArrayList<>();
    private RequestAPIServices apiServices;
    int id;
    private boolean isRoleSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        ActionBar actionBar =  getActionBar();
        ((ActionBar)actionBar).setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Edit User");

        editUserEditTexUsername = (EditText) findViewById(R.id.editUserEditTexUsername);
        editUserEditTexPassword = (EditText) findViewById(R.id.editUserEditTexPassword);
        editUserEditTexRetypePassword = (EditText) findViewById(R.id.editUserEditTexRetypePassword);
        editUserEditTextRole = (AutoCompleteTextView) findViewById(R.id.editUserEditTextRole);

        getRole();
//        getRole(editUserEditTextRole.getText().toString().trim());

        final ArrayAdapter<String> adapter=  new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, listRole);
        editUserEditTextRole.setThreshold(0);
        editUserEditTextRole.setAdapter(adapter);
        editUserEditTextRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editUserEditTextRole.getText().toString().trim().length()==0){
                    editUserEditTextRole.showDropDown();
                }
            }
        });
        editUserEditTextRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                isRoleSelected = true;
                editUserEditTextRole.setError(null);
            }
        });
        editUserEditTextRole.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Value Role get one
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editUserEditTextRole.getText().toString().trim().length()==0){
                    editUserEditTextRole.setError(null);
                }else{
                    isRoleSelected = false;
                    editUserEditTextRole.setError("Role must from the list!");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editUserButtonSave =  (Button) findViewById(R.id.editUserButtonSave);
        editUserButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editValidation();
            }
        });
        editUserButtonCancel =  (Button) findViewById(R.id.editUserButtonCancel);
        editUserButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        id = getIntent().getIntExtra("id",0);
        getOneUserAPI(id);
    }
    private void getRole(){

        apiServices = APIUtilities.getAPIServices();
        apiServices.getListRole().enqueue(new Callback<ModelRole>() {
            @Override
            public void onResponse(Call<ModelRole> call, Response<ModelRole> response) {
                List<DataListRole> tmp = response.body().getDataList();

                if(response.code()==200){
                    for(int i=0; i<tmp.size(); i++){
                        DataListRole data = tmp.get(i);
                        dataListRoles.add(data);
                        listRole.add(data.getName());
                        System.out.println(dataListRoles);
                    }

                }else{
                    Toast.makeText(context, "Gagal Mendapatkan List Role"+response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ModelRole> call, Throwable t) {
                Toast.makeText(context, "List User onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private  void getOneUserAPI(int id){
     apiServices = APIUtilities.getAPIServices();
     apiServices.getOneUser(id).enqueue(new Callback<ModelUser>() {
         @Override
         public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {
             if(response.code()==200){
                // DataList data = response.body().;

             }
         }

         @Override
         public void onFailure(Call<ModelUser> call, Throwable t) {

         }
     });
    }
    private  void editValidation(){
        if(editUserEditTextRole.getText().toString().trim().length()==0){
            Toast.makeText(context,  "Role field still empty !", Toast.LENGTH_SHORT).show();
        }else if(editUserEditTexUsername.getText().toString().trim().length()==0){
            Toast.makeText(context,  "Username field still empty !", Toast.LENGTH_SHORT).show();
        }else if(editUserEditTexPassword.getText().toString().trim().length()==0){
            Toast.makeText(context,  "Password field still empty !", Toast.LENGTH_SHORT).show();
        }else if(editUserEditTexRetypePassword.getText().toString().trim().length()==0){
            Toast.makeText(context,  "Please Retype password !", Toast.LENGTH_SHORT).show();
        }else if(isRoleSelected == false){
            Toast.makeText(context,  "Role must from the list !", Toast.LENGTH_SHORT).show();
        }else{
            final String pas = editUserEditTexPassword.getText().toString();
            final String repas = editUserEditTexRetypePassword.getText().toString();
            if(pas.equalsIgnoreCase(repas)){
                inputEditValidation();
            }else{
                Toast.makeText(context, "Pasword tidak sama!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void inputEditValidation(){

    }
    private void saveDataNotification(){
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Testimony Successfully updated !")
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
