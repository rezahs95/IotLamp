package ir.altontelecom.iotlamp;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Switch;

import ir.altontelecom.iotlamp.webservice.WebServiceHelper;
import ir.altontelecom.iotlamp.webservice.response.GetStateResponse;
import ir.altontelecom.iotlamp.webservice.response.SetResponse;
import ir.altontelecom.iotlamp.webservice.resquest.GetStateRequest;
import ir.altontelecom.iotlamp.webservice.resquest.SetColorRequest;
import ir.altontelecom.iotlamp.webservice.resquest.SetPowerRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "ir.altontelecom.iotlamp.MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Switch switch1 = (Switch) findViewById(R.id.switch1);
        final SeekBar seekRed = (SeekBar) findViewById(R.id.seekBar5);
        final SeekBar seekGreen = (SeekBar) findViewById(R.id.seekBar6);
        final SeekBar seekBlue = (SeekBar) findViewById(R.id.seekBar7);
        seekRed.setEnabled(false);
        seekGreen.setEnabled(false);
        seekBlue.setEnabled(false);

        Call<GetStateResponse> call = WebServiceHelper.getWebServiceMethods().getState(new
                GetStateRequest(new GetStateRequest.Identity("E6B6D964B966E71180CC4A9D6544D113")));
        call.enqueue(new Callback<GetStateResponse>() {
            @Override
            public void onResponse(Call<GetStateResponse> call, Response<GetStateResponse>
                    response) {
                GetStateResponse stateResponse = response.body();
                switch1.setChecked(stateResponse.getParameters().getPower());
                seekRed.setProgress(stateResponse.getParameters().getRed());
                seekGreen.setProgress(stateResponse.getParameters().getGreen());
                seekBlue.setProgress(stateResponse.getParameters().getBlue());

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

            @Override
            public void onFailure(Call<GetStateResponse> call, Throwable t) {

            }
        });


        //POWER////////////////////////////////////////////////////
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<SetResponse> call = WebServiceHelper.getWebServiceMethods().setPower(new
                        SetPowerRequest(new SetPowerRequest.Identity
                        ("E6B6D964B966E71180CC4A9D6544D113"), new SetPowerRequest.Parameters
                        (switch1.isChecked())));
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
                        SetResponse powerResponse = response.body();
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
        seekRed.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(getResources()
                .getColor(R.color.red), PorterDuff.Mode.SRC_IN));
        seekGreen.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(getResources()
                .getColor(R.color.green), PorterDuff.Mode.SRC_IN));
        seekBlue.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(getResources()
                .getColor(R.color.blue), PorterDuff.Mode.SRC_IN));

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
                        SetColorRequest(new SetColorRequest.Identity
                        ("E6B6D964B966E71180CC4A9D6544D113"), new SetColorRequest.Parameters
                        (seekRed.getProgress(), seekGreen.getProgress(), seekBlue.getProgress(),
                                0, 0)));
                call.enqueue(new Callback<SetResponse>() {
                    @Override
                    public void onResponse(Call<SetResponse> call, Response<SetResponse> response) {
                        SetResponse powerResponse = response.body();
                        Log.d("123", "success");
                    }

                    @Override
                    public void onFailure(Call<SetResponse> call, Throwable t) {
                        Log.d("123", "No success");
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
                        SetColorRequest(new SetColorRequest.Identity
                        ("E6B6D964B966E71180CC4A9D6544D113"), new SetColorRequest.Parameters
                        (seekRed.getProgress(), seekGreen.getProgress(), seekBlue.getProgress(),
                                0, 0)));
                call.enqueue(new Callback<SetResponse>() {
                    @Override
                    public void onResponse(Call<SetResponse> call, Response<SetResponse> response) {
                        SetResponse powerResponse = response.body();
                        Log.d("123", "success");
                    }

                    @Override
                    public void onFailure(Call<SetResponse> call, Throwable t) {
                        Log.d("123", "No success");
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
                        SetColorRequest(new SetColorRequest.Identity
                        ("E6B6D964B966E71180CC4A9D6544D113"), new SetColorRequest.Parameters
                        (seekRed.getProgress(), seekGreen.getProgress(), seekBlue.getProgress(),
                                0, 0)));
                call.enqueue(new Callback<SetResponse>() {
                    @Override
                    public void onResponse(Call<SetResponse> call, Response<SetResponse> response) {
                        SetResponse powerResponse = response.body();
                        Log.d("123", "success");
                    }

                    @Override
                    public void onFailure(Call<SetResponse> call, Throwable t) {
                        Log.d("123", "No success");
                    }
                });

            }
        });

    }
}