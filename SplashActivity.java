package fabricio.g2trabalho.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import fabricio.g2trabalho.R;

public class SplashActivity extends Activity {
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Thread timer = new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(2000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    chamaMain();
                }
            }
        };

        timer.start();
    }
    void chamaMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
