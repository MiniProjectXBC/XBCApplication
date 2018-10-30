package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.model.idleNews.ModelIdleNews;
import xbc.miniproject.com.xbcapplication.model.idleNews.autoComplete.DataList;
import xbc.miniproject.com.xbcapplication.model.idleNews.autoComplete.ModelAutoCompleteIdleNews;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;
import xbc.miniproject.com.xbcapplication.utility.SessionManager;

public class AddIdleNewsActivity extends Activity {
    private Context context = this;

    EditText addIdleNewsEditTextTitle,
            addIdleNewsEditTextContent;

    AutoCompleteTextView addIdleNewsEditTextCategory;

    Button addIdleNewsButtonSave,
            addIdleNewsButtonCancel;
    RequestAPIServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_idle_news);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Input Idle News");

        addIdleNewsEditTextTitle = (EditText) findViewById(R.id.addIdleNewsEditTextTitle);
        addIdleNewsEditTextContent = (EditText) findViewById(R.id.addIdleNewsEditTextContent);

        addIdleNewsEditTextCategory = (AutoCompleteTextView) findViewById(R.id.addIdleNewsEditTextCategory);
        autoCompleteIdleNewsAPI();

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

    private void autoCompleteIdleNewsAPI(){
        apiServices = APIUtilities.getAPIServices();
        apiServices.idleNewsAutoComplete("application/json", SessionManager.getToken(context), "g")
                .enqueue(new Callback<ModelAutoCompleteIdleNews>() {
                    @Override
                    public void onResponse(Call<ModelAutoCompleteIdleNews> call, Response<ModelAutoCompleteIdleNews> response) {
                        if (response.code() == 201) {
                            List<String> str = new ArrayList<String>();
                            for (DataList s : response.body().getDataList()){
                                str.add(s.getName());
                            }
                            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                                    android.R.layout.select_dialog_item,str.toArray(new String[0]));
                            addIdleNewsEditTextCategory.setThreshold(1);
                            addIdleNewsEditTextCategory.setAdapter(adapter);
                            }
                        }

                    @Override
                    public void onFailure(Call<ModelAutoCompleteIdleNews> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void inputValidation() {
        if (addIdleNewsEditTextTitle.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Title Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(addIdleNewsEditTextCategory.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Category Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
//            SaveSuccessNotification();
            callAPICreateIdleNews(addIdleNewsEditTextTitle.getText().toString(), addIdleNewsEditTextCategory.getText().toString(), addIdleNewsEditTextContent.getText().toString());
        }
    }

    private void callAPICreateIdleNews(String title, String category, String content) {

        String contentType = "application/json";
        String json = APIUtilities.generateIdleNewsMap(title, category, content);
        RequestBody bodyRequest = RequestBody.create(APIUtilities.mediaType(), json);
        apiServices = APIUtilities.getAPIServices();

        IdleNewsList data = new IdleNewsList();
//        data.setTitle(addIdleNewsEditTextTitle.getText().toString());
//        data.setContent(addIdleNewsEditTextContent.getText().toString());
//        data.getCategory().setName(addIdleNewsEditTextCategory.getText().toString());

        apiServices.createNewIdleNews("application/json", bodyRequest)
                .enqueue(new Callback<ModelIdleNews>() {
                    @Override
                    public void onResponse(Call<ModelIdleNews> call, Response<ModelIdleNews> response) {
                        if (response.code() == 201) {
                            String message = response.body().getMessage();
                            if (message!=null){
                                SaveSuccessNotification();
                            } else{
                                Toast.makeText(context,"Message Gagal Diambil", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ModelIdleNews> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void SaveSuccessNotification(){
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(context);
        builder.setTitle("NOTIFICATION !")
                .setMessage("Data Successfully Added!").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
