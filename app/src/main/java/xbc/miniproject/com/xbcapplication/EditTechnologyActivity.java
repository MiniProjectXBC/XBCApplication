package xbc.miniproject.com.xbcapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTechnologyActivity extends Activity {
    private Context context=this;
    private EditText editTechnologyEditTextName,
            editTechnologyEditTexNote;
    private Button editTechnologyButtonSave,
            editTechnologyButtonCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_technology);
        editTechnologyEditTextName = (EditText)findViewById(R.id.editTechnologyEditTextName);
        editTechnologyEditTexNote = (EditText) findViewById(R.id.editTechnologyEditTexNote);
        editTechnologyButtonSave = (Button) findViewById(R.id.editTechnologyButtonSave);
        editTechnologyButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editValidation();
            }
        });
        editTechnologyButtonCancel =(Button)findViewById(R.id.editTechnologyButtonCancel);
        editTechnologyButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void editValidation(){
        if(editTechnologyEditTextName.getText().toString().trim().length()==0){
            Toast.makeText(context,"Name Field still empty!",Toast.LENGTH_SHORT).show();
        }else if(editTechnologyEditTexNote.getText().toString().trim().length()==0){
            Toast.makeText(context,"Note Field still empty!",Toast.LENGTH_SHORT).show();
        }else{
            //hanya pesan
            Toast.makeText(context,"Data successfully updated!",Toast.LENGTH_SHORT).show();
        }
    }
}
