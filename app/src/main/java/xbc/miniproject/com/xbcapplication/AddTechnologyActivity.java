package xbc.miniproject.com.xbcapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTechnologyActivity extends Activity {
    private Context context =this;
    private EditText addTechnologyEditTextName
            ,addTechnologyEditTexNote;
    private Button addTechnologyButtonSave,
            addTechnologyButtonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_technology);
        addTechnologyEditTextName = (EditText) findViewById(R.id.addTechnologyEditTextName);
        addTechnologyEditTexNote = (EditText) findViewById(R.id.addTechnologyEditTexNote);
        addTechnologyButtonSave = (Button) findViewById(R.id.addTechnologyButtonSave);
        addTechnologyButtonCancel = (Button) findViewById(R.id.addTechnologyButtonCancel);

        //button save data
        addTechnologyButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputValidation();
            }
        });

        //button cancel
        addTechnologyButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void inputValidation(){
        if(addTechnologyEditTextName.getText().toString().trim().length()==0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        }else if (addTechnologyEditTexNote.getText().toString().trim().length()==0){
            Toast.makeText(context,"Note Field still empty!",Toast.LENGTH_SHORT).show();
        }
    }
}
