package co.edu.udea.compumovil.gr09_20171.controlparental.Views;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import co.edu.udea.compumovil.gr09_20171.controlparental.R;

public class LoginView extends FragmentActivity {

    private TextView username_tv, password_tv;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username_tv = (TextView) findViewById(R.id.user_name);
        password_tv = (TextView) findViewById(R.id.password);

        login_btn = (Button) findViewById(R.id.login_button);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), username_tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
