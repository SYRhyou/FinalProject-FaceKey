package com.example.facekey;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://203.244.145.238/Register.php";

    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userDepartment, Response.Listener<String> listener){
            super(Method.POST, URL, listener, null);

            parameters = new HashMap<>();

            parameters.put("userID", userID);
            parameters.put("userPassword", userPassword);
            parameters.put("userDepartment", userDepartment);
    }

    @Override
    public Map<String, String> getParams(){
        return parameters;
    }

}
