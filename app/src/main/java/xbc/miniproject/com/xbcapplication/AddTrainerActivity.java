package xbc.miniproject.com.xbcapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTrainerActivity extends Activity {

    private Context context =this;
    private EditText addTrainerEditTextName
            ,addTrainerEditTexNote;
    private Button addTrainerButtonSave,
            addTrainerButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trainer);
        addTrainerEditTextName = (EditText) findViewById(R.id.addTrainerEditTextName);
        addTrainerEditTexNote = (EditText) findViewById(R.id.addTrainerEditTexNote);
        addTrainerButtonSave = (Button) findViewById(R.id.addTrainerButtonSave);
        addTrainerButtonCancel = (Button) findViewById(R.id.addTrainerButtonCancel);

        //button save data
        addTrainerButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        //button cancel
        addTrainerButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void inputValidation(){
        if(addTrainerEditTextName.getText().toString().trim().length()==0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        }else if (addTrainerEditTexNote.getText().toString().trim().length()==0){
            Toast.makeText(context,"Note Field still empty!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Data succesfully added!",Toast.LENGTH_SHORT).show();
        }
    }

}
