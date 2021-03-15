package com.wc.poc.dataCapture.cmsAuth;

import com.sun.jersey.oauth.signature.OAuthRequest;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CustomAuthRequest implements OAuthRequest {

    private final String method;
    private final URL url;
    private String oauthHeader;
    public CustomAuthRequest(String method, URL url) throws IOException {
        this.method = method;
        this.url = url;

    }




    public String getOauthHeader()
    {
        return oauthHeader;
    }


    @Override
    public String getRequestMethod() {
        return method;
    }

    @Override
    public URL getRequestURL() {
        return url;
    }

    @Override
    public Set<String> getParameterNames() {
        return new TreeSet<String>();
    }

    @Override
    public List<String> getParameterValues(String s) {
        return null;
    }

    @Override
    public List<String> getHeaderValues(String s) {
        return null;
    }

    @Override
    public void addHeaderValue(String s, String s1) throws IllegalStateException {
        if(s.equals("Authorization"))
        {
            oauthHeader=s1;
        }
    }
}
