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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xbc.miniproject.com.xbcapplication.model.idleNews.IdleNewsList;
import xbc.miniproject.com.xbcapplication.model.idleNews.ModelIdleNews;
import xbc.miniproject.com.xbcapplication.retrofit.APIUtilities;
import xbc.miniproject.com.xbcapplication.retrofit.RequestAPIServices;

public class AddIdleNewsActivity extends Activity {
    private Context context = this;

    EditText addIdleNewsEditTextTitle,
            addIdleNewsEditTextCategory,
            addIdleNewsEditTextContent;

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
//            SaveSuccessNotification();
            callAPICreateIdleNews();
        }
    }

    private void callAPICreateIdleNews() {
        apiServices = APIUtilities.getAPIServices();

        IdleNewsList data = new IdleNewsList();
        data.setTitle(addIdleNewsEditTextTitle.getText().toString());
        data.setContent(addIdleNewsEditTextContent.getText().toString());
//        data.getCategory().setName(addIdleNewsEditTextCategory.getText().toString());

        apiServices.createNewIdleNews("application/json", data)
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
