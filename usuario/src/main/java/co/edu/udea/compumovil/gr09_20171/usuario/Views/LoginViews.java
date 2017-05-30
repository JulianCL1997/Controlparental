package co.edu.udea.compumovil.gr09_20171.usuario.Views;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import co.edu.udea.compumovil.gr09_20171.usuario.R;

public class LoginViews extends FragmentActivity {

    private TextView documento;
    private Button buscar_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        documento = (TextView) findViewById(R.id.user_name);
        buscar_btn = (Button) findViewById(R.id.login_button);
    }
}
