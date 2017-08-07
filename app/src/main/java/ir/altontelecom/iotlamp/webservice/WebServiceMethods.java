package ir.altontelecom.iotlamp.webservice;

import ir.altontelecom.iotlamp.webservice.response.SetPowerResponse;
import ir.altontelecom.iotlamp.webservice.resquest.SetPowerRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static ir.altontelecom.iotlamp.webservice.WebServiceEndpoints.SET_POWER_ENDPOINT;

public interface WebServiceMethods {
    @POST(SET_POWER_ENDPOINT)
    Call<SetPowerResponse> setPower(@Body SetPowerRequest setPowerRequest);
}