package id.astrajingga.monicca.auth;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by grb on 6/20/2017.
 */

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://monicca.ai/api/login.php";
    private Map<String, String> params;

    public LoginRequest(String signinStringEmail, String signinStringPassword, Response.Listener<String> listener ) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("email", signinStringEmail);
        params.put("password", signinStringPassword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
