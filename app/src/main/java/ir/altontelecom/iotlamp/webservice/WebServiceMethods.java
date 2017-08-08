package ir.altontelecom.iotlamp.webservice;

import ir.altontelecom.iotlamp.webservice.response.GetStateResponse;
import ir.altontelecom.iotlamp.webservice.response.SetResponse;
import ir.altontelecom.iotlamp.webservice.resquest.GetStateRequest;
import ir.altontelecom.iotlamp.webservice.resquest.SetColorRequest;
import ir.altontelecom.iotlamp.webservice.resquest.SetPowerRequest;
import ir.altontelecom.iotlamp.webservice.resquest.SetPowerWithColorRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static ir.altontelecom.iotlamp.webservice.WebServiceEndpoints.GET_STATE_ENDPOINT;
import static ir.altontelecom.iotlamp.webservice.WebServiceEndpoints.SET_COLOR_ENDPOINT;
import static ir.altontelecom.iotlamp.webservice.WebServiceEndpoints.SET_POWER_ENDPOINT;
import static ir.altontelecom.iotlamp.webservice.WebServiceEndpoints.SET_POWER_WITH_COLOR_ENDPOINT;

public interface WebServiceMethods {
    @POST(GET_STATE_ENDPOINT)
    Call<GetStateResponse> getState(@Body GetStateRequest getStateRequest);

    @POST(SET_COLOR_ENDPOINT)
    Call<SetResponse> setColor(@Body SetColorRequest setColorRequest);

    @POST(SET_POWER_ENDPOINT)
    Call<SetResponse> setPower(@Body SetPowerRequest setPowerRequest);

    @POST(SET_POWER_WITH_COLOR_ENDPOINT)
    Call<SetResponse> setPowerWithColor(@Body SetPowerWithColorRequest setPowerWithColorRequest);
}