package ir.altontelecom.iotlamp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.altontelecom.iotlamp.webservice.WebServiceHelper;
import ir.altontelecom.iotlamp.webservice.response.LoginResponse;
import ir.altontelecom.iotlamp.webservice.resquest.LoginRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_SESSION_KEY = "extra_session_key";
    private static final String PREFERENCES_SESSION_KEY = "preferences_session_key";
    private static final String PERFERENCES_FILE = "preferences_file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefs = getSharedPreferences(PERFERENCES_FILE, 0);
        String isLoggedIn = prefs.getString(PREFERENCES_SESSION_KEY, null);
        if(isLoggedIn != null) {
            Intent mainActivity = new Intent(LoginActivity.this, MainActivity.class);
            mainActivity.putExtra(EXTRA_SESSION_KEY, isLoggedIn);
            startActivity(mainActivity);
        }


        final Button button = (Button) findViewById(R.id.button);
        final EditText user = (EditText) findViewById(R.id.editText2);
        final EditText pass = (EditText) findViewById(R.id.editText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<LoginResponse> call = WebServiceHelper.getWebServiceMethods().login(new
                        LoginRequest(new LoginRequest.Parameters(user.getText().toString(), pass
                        .getText().toString())));
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse>
                            response) {
                        LoginResponse loginResponse = response.body();
                        if(loginResponse.getStatus().getCode().equals("G00000")) {
                            Intent mainActivity = new Intent(LoginActivity.this, MainActivity
                                    .class);
                            String sessionKey = loginResponse.getParameters().getSessionKey();
                            SharedPreferences.Editor editor = getSharedPreferences
                                    (PERFERENCES_FILE, 0).edit();
                            editor.putString(PREFERENCES_SESSION_KEY, sessionKey);
                            editor.apply();
                            editor.commit();
                            mainActivity.putExtra(EXTRA_SESSION_KEY, sessionKey);
                            startActivity(mainActivity);
                        }
                        else if(loginResponse.getStatus().getCode().equals("GC0007")){
                            Toast.makeText(getApplicationContext(), "Empty Field",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Wrong Username or Password",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}