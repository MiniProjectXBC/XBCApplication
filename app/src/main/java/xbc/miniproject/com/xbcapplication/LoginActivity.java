package xbc.miniproject.com.xbcapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private Context context = this;

    private EditText inputUsername, inputPassword;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.userName);
        inputPassword = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.loginButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HomeActivity.class);
                submitLogin();
            }
        });
    }

    private void submitLogin() {
        String username = inputUsername.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (username.isEmpty()) {
            Toast.makeText(context, getResources().getString(R.string.userNameEmpty), Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(context, getResources().getString(R.string.passwordEmpty), Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
