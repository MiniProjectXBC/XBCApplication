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

    int id;

    List<IdleNewsList> listIdleNews = new ArrayList<IdleNewsList>();

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

        id = getIntent().getIntExtra("id", 0);
        getOneIdleNewsAPI(id);
    }

    private void getOneIdleNewsAPI(int id) {
        apiServices = APIUtilities.getAPIServices();
        apiServices.getOneIdleNews(id).enqueue(new Callback<ModelIdleNews>() {
            @Override
            public void onResponse(Call<ModelIdleNews> call, Response<ModelIdleNews> response) {
                if (response.code() == 200){
//                    IdleNewsList data = response.body().getDataList();
//                    editIdleNewsEditTextTitle.setText(data.getTitle());
//                    editIdleNewsEditTextCategory.setText(data.getCategory().getName());
//                    editIdleNewsEditTextContent.setText(data.getContent().toString());
                } else{
                    Toast.makeText(context, "Gagal Mendapatkan Testimony Biodata: " + response.code() + " msg: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ModelIdleNews> call, Throwable t) {
                Toast.makeText(context, "Get Testimony onFailure: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void inputValidation() {
        if (editIdleNewsEditTextTitle.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Title Field still empty!",Toast.LENGTH_SHORT).show();
        } else if(editIdleNewsEditTextCategory.getText().toString().trim().length() == 0){
            Toast.makeText(context,"Category Field still empty!",Toast.LENGTH_SHORT).show();
        } else{
            inputEditIdleNewsAPI("title", "category", "content");
        }
    }

    private void inputEditIdleNewsAPI(String title, String category, String content) {

        String contentType = "application/json";
        String json = APIUtilities.generateIdleNewsMap(title, category, content);
        RequestBody bodyRequest = RequestBody.create(APIUtilities.mediaType(), json);

        apiServices = APIUtilities.getAPIServices();

        IdleNews data = new IdleNews();
//        data.setId(id);
//        data.setTitle(editIdleNewsEditTextTitle.getText().toString());
//        data.getCategory().setName(editIdleNewsEditTextCategory.getText().toString());
//        data.setContent(editIdleNewsEditTextContent.getText().toString());

        apiServices.editIdleNews(Constanta.CONTENT_TYPE_API,
                Constanta.AUTHORIZATION_EDIT_IDLE_NEWS,
                data)
                .enqueue(new Callback<ModelIdleNews>() {
                    @Override
                    public void onResponse(Call<ModelIdleNews> call, Response<ModelIdleNews> response) {
                        if (response.code() == 200) {
                            String message = response.body().getMessage();
                            if (message!=null){
                                SaveSuccessNotification();
                            } else{
                                SaveSuccessNotification();
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