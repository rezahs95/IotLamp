package ir.altontelecom.iotlamp;

import android.content.*;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import ir.altontelecom.iotlamp.webservice.CustomDialogClass;
import ir.altontelecom.iotlamp.webservice.WebServiceHelper;
import ir.altontelecom.iotlamp.webservice.response.GetStateResponse;
import ir.altontelecom.iotlamp.webservice.response.SetResponse;
import ir.altontelecom.iotlamp.webservice.resquest.GetStateRequest;
import ir.altontelecom.iotlamp.webservice.resquest.SetColorRequest;
import ir.altontelecom.iotlamp.webservice.resquest.SetPowerRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.altontelecom.iotlamp.LoginActivity.EXTRA_SESSION_KEY;

public class MainActivity extends AppCompatActivity {
    private static final String PREFERENCES_SESSION_KEY = "preferences_session_key";
    private static final String PERFERENCES_FILE = "preferences_file";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Switch switch1 = (Switch) findViewById(R.id.switch1);
        final SeekBar seekRed = (SeekBar) findViewById(R.id.seekBar5);
        final SeekBar seekGreen = (SeekBar) findViewById(R.id.seekBar6);
        final SeekBar seekBlue = (SeekBar) findViewById(R.id.seekBar7);
        final ProgressBar load = (ProgressBar) findViewById(R.id.progressBar);
        final TextView colort = (TextView) findViewById(R.id.textView);
        final TextView redt = (TextView) findViewById(R.id.textView2);
        final TextView greent = (TextView) findViewById(R.id.textView3);
        final TextView bluet = (TextView) findViewById(R.id.textView4);
        switch1.setVisibility(View.INVISIBLE);
        seekBlue.setVisibility(View.INVISIBLE);
        seekGreen.setVisibility(View.INVISIBLE);
        seekRed.setVisibility(View.INVISIBLE);
        colort.setVisibility(View.INVISIBLE);
        redt.setVisibility(View.INVISIBLE);
        greent.setVisibility(View.INVISIBLE);
        bluet.setVisibility(View.INVISIBLE);
        seekRed.setEnabled(false);
        seekGreen.setEnabled(false);
        seekBlue.setEnabled(false);

        final String SESSION_KEY = getIntent().getStringExtra(EXTRA_SESSION_KEY);

        //STATE/////////////////////////////////////////////////////////
        Call<GetStateResponse> call = WebServiceHelper.getWebServiceMethods().getState(new
                GetStateRequest(new GetStateRequest.Identity(SESSION_KEY)));
        call.enqueue(new Callback<GetStateResponse>() {
            @Override
            public void onResponse(Call<GetStateResponse> call, Response<GetStateResponse>
                    response) {

                GetStateResponse stateResponse = response.body();
                if(stateResponse.getStatus().getCode().equals("G00000")) {
                    switch1.setChecked(stateResponse.getParameters().getPower());
                    seekRed.setProgress(stateResponse.getParameters().getRed());
                    seekGreen.setProgress(stateResponse.getParameters().getGreen());
                    seekBlue.setProgress(stateResponse.getParameters().getBlue());

                    switch1.setVisibility(View.VISIBLE);
                    seekBlue.setVisibility(View.VISIBLE);
                    seekGreen.setVisibility(View.VISIBLE);
                    seekRed.setVisibility(View.VISIBLE);
                    colort.setVisibility(View.VISIBLE);
                    redt.setVisibility(View.VISIBLE);
                    greent.setVisibility(View.VISIBLE);
                    bluet.setVisibility(View.VISIBLE);
                    load.setVisibility(View.INVISIBLE);

                    if(!switch1.isChecked()) {
                        seekRed.setEnabled(false);
                        seekGreen.setEnabled(false);
                        seekBlue.setEnabled(false);
                    }
                    else {
                        seekRed.setEnabled(true);
                        seekGreen.setEnabled(true);
                        seekBlue.setEnabled(true);
                    }
                }
                else if(stateResponse.getStatus().getCode().equals("G00010")){
                    load.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Bulb is not connected", Toast
                            .LENGTH_SHORT).show();
                }
                else {
                    load.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Expired Session Key", Toast
                            .LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetStateResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error", Toast
                        .LENGTH_SHORT).show();
            }
        });


        //POWER////////////////////////////////////////////////////
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<SetResponse> call = WebServiceHelper.getWebServiceMethods().setPower(new
                        SetPowerRequest(new SetPowerRequest.Identity(SESSION_KEY), new
                        SetPowerRequest.Parameters(switch1.isChecked())));
                if(!switch1.isChecked()) {
                    seekRed.setEnabled(false);
                    seekGreen.setEnabled(false);
                    seekBlue.setEnabled(false);
                }
                else {
                    seekRed.setEnabled(true);
                    seekGreen.setEnabled(true);
                    seekBlue.setEnabled(true);
                }

                call.enqueue(new Callback<SetResponse>() {
                    @Override
                    public void onResponse(Call<SetResponse> call, Response<SetResponse> response) {
                        //SetResponse powerResponse = response.body();
                        Log.d("123", "success");
                    }

                    @Override
                    public void onFailure(Call<SetResponse> call, Throwable t) {
                        Log.d("123", "No success");
                    }
                });
            }
        });

        //COLOR//////////////////////////////////////
        seekRed.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(ContextCompat
                .getColor(this, R.color.red), PorterDuff.Mode.SRC_IN));
        seekGreen.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(ContextCompat
                .getColor(this, R.color.green), PorterDuff.Mode.SRC_IN));
        seekBlue.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(ContextCompat
                .getColor(this, R.color.blue), PorterDuff.Mode.SRC_IN));

        seekRed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Call<SetResponse> call = WebServiceHelper.getWebServiceMethods().setColor(new
                        SetColorRequest(new SetColorRequest.Identity(SESSION_KEY), new
                        SetColorRequest.Parameters(seekRed.getProgress(), seekGreen.getProgress()
                        , seekBlue.getProgress(), 0, 0)));
                call.enqueue(new Callback<SetResponse>() {
                    @Override
                    public void onResponse(Call<SetResponse> call, Response<SetResponse> response) {
                        Log.d("123", "success");
                    }

                    @Override
                    public void onFailure(Call<SetResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Server Error", Toast
                                .LENGTH_SHORT).show();
                    }
                });

            }
        });


        seekGreen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Call<SetResponse> call = WebServiceHelper.getWebServiceMethods().setColor(new
                        SetColorRequest(new SetColorRequest.Identity(SESSION_KEY), new
                        SetColorRequest.Parameters(seekRed.getProgress(), seekGreen.getProgress()
                        , seekBlue.getProgress(), 0, 0)));
                call.enqueue(new Callback<SetResponse>() {
                    @Override
                    public void onResponse(Call<SetResponse> call, Response<SetResponse> response) {
                        Log.d("123", "success");
                    }

                    @Override
                    public void onFailure(Call<SetResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Server Error", Toast
                                .LENGTH_SHORT).show();
                    }
                });

            }
        });


        seekBlue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Call<SetResponse> call = WebServiceHelper.getWebServiceMethods().setColor(new
                        SetColorRequest(new SetColorRequest.Identity(SESSION_KEY), new
                        SetColorRequest.Parameters(seekRed.getProgress(), seekGreen.getProgress()
                        , seekBlue.getProgress(), 0, 0)));
                call.enqueue(new Callback<SetResponse>() {
                    @Override
                    public void onResponse(Call<SetResponse> call, Response<SetResponse> response) {
                        Log.d("123", "success");
                    }

                    @Override
                    public void onFailure(Call<SetResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Server Error", Toast
                                .LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch(item.getItemId()) {
            case R.id.logout:
                final CustomDialogClass cdd=new CustomDialogClass(MainActivity.this);
                cdd.show();
                cdd.yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        android.content.SharedPreferences.Editor editor = getSharedPreferences
                                (PERFERENCES_FILE, 0).edit();
                        editor.remove(PREFERENCES_SESSION_KEY);
                        editor.apply();
                        editor.commit();
                        Intent LoginActivity = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(LoginActivity);
                    }
                });
                cdd.no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cdd.cancel();
                    }
                });
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}