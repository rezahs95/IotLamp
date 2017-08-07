package ir.altontelecom.iotlamp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import ir.altontelecom.iotlamp.webservice.WebServiceHelper;
import ir.altontelecom.iotlamp.webservice.response.SetPowerResponse;
import ir.altontelecom.iotlamp.webservice.resquest.SetPowerRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.altontelecom.iotlamp.R.id.switch1;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "ir.altontelecom.iotlamp.MESSAGE";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<SetPowerResponse> call = WebServiceHelper
                        .getWebServiceMethods()
                        .setPower(new SetPowerRequest(new SetPowerRequest.Identity("E6B6D964B966E71180CC4A9D6544D113")
                                , new SetPowerRequest.Parameters(switch1.isChecked())));
                call.enqueue(new Callback<SetPowerResponse>() {
                    @Override
                    public void onResponse(Call<SetPowerResponse> call, Response<SetPowerResponse> response) {
                        SetPowerResponse powerResponse = response.body();
                        Log.d("123", "success");
                    }

                    @Override
                    public void onFailure(Call<SetPowerResponse> call, Throwable t) {
                        Log.d("123", "No success");
                    }
                });
            }
        });
    }
}