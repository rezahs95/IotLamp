package ir.altontelecom.iotlamp.webservice;

class WebServiceEndpoints {
//    public static final String BASE_URL = "http://bulbmanager.simsend.ir/";
    static final String BASE_URL = "http://192.168.1.40/";
    static final String LOGIN_ENDPOINT = "WebServices/Profile.svc/Login";
    static final String GET_STATE_ENDPOINT = "WebServices/Core.svc/GetState";
    static final String SET_COLOR_ENDPOINT = "WebServices/Core.svc/SetColor";
    static final String SET_POWER_ENDPOINT = "WebServices/Core.svc/SetPower";
    static final String SET_POWER_WITH_COLOR_ENDPOINT = "WebServices/Core.svc/SetPowerWithColor";
}