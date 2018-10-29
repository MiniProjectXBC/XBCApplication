package xbc.miniproject.com.xbcapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.model.biodata.Biodata;
import xbc.miniproject.com.xbcapplication.model.biodata.ModelBiodata;
import xbc.miniproject.com.xbcapplication.model.idleNews.Category;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNews;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.model.idleNews.ModelIdleNews;
import xbc.miniproject.com.xbcapplication.model.idleNews.getOne.ModelIdleNewsGetOne;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;
import xbc.miniproject.com.xbcapplication.utility.Constanta;

public class EditIdleNewsActivity extends Activity {
    private Context context = this;

    EditText editIdleNewsEditTextTitle,
            editIdleNewsEditTextCategory,
            editIdleNewsEditTextContent;

    Button editIdleNewsButtonSave,
            editIdleNewsButtonCancel;

    RequestAPIServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_idle_news);

        String title, category, content;

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

        Bundle data = getIntent().getExtras();
        if (data == null){
            title = null;
            category = null;
//            content = null;
        }
        else {
            title = data.getString("title");
            category = data.getString("category");
//            content = data.getString("content");
        }
        editIdleNewsEditTextTitle.setText(title);
        editIdleNewsEditTextCategory.setText(category);
//        editIdleNewsEditTextContent.setText(content);
    }

    private void inputValidation() {
        if (editIdleNewsEditTextTitle.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Title Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editIdleNewsEditTextCategory.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Category Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
            inputEditIdleNewsAPI(editIdleNewsEditTextTitle.getText().toString(), editIdleNewsEditTextCategory.getText().toString(), editIdleNewsEditTextContent.getText().toString());
        }
    }

    private void inputEditIdleNewsAPI(String title, String category, String content) {

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
                .setMessage("Data successfully updated !").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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