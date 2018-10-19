package xbc.miniproject.com.xbcapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import xbc.miniproject.com.xbcapplication.utility.Constanta;

public class SplashScreenActivity extends Activity {
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
        //rahmat

//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                //pindah ke scree berikutnya
//                if (SessionManager.isRegister(context)) {
//                    //bypass ke main menu
//                    Intent intent = new Intent(context, /*MainMenuActivity*/. class);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(context, /*WelcomeActivity*/. class);
//                    startActivity(intent);
//                }
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);

                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, Constanta.SPLASH_DELAY_TIME);
    }
}
