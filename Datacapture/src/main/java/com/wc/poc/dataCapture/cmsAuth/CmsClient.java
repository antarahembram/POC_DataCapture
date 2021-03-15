package com.wc.poc.dataCapture.cmsAuth;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.oauth.signature.*;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Component
public class CmsClient {
    private static final Logger logger = LoggerFactory.getLogger(CmsClient.class);
    private static final String PROTOCOL="https://";
    private static final String URL_PREFIX="one/oauth1";
    private static final String API_KEY="c62846e6-4b68-4fb7-a745-49045bf0fe03";
    private static final String SHARED_SECRET="ac407483-6e7c-4b5b-8f25-cd0bbb5a5607";
    private static final String SERVICE_ACCOUNT="om.prakash@cgi.com.partner";
    private static final String THUNDERHEAD_PART="na4.smartcommunications.cloud";
    private static final String SEARCH_INTERVIEW="/cmsSearchServices/v1/search/fts";
    private static final String FULL_KEY = "c62846e6-4b68-4fb7-a745-49045bf0fe03!om.prakash%40cgi.com.partner";
    private static final String GET_PROJECT="/cms/v5/resources/";
    private static final String LATEST_VERSION="/latestversion";


    public   String generateOAuthHeader(CustomAuthRequest request, String key, String secret, String username)
    {
        String header = null;
        try{

            OAuthParameters params = new OAuthParameters().signatureMethod(HMAC_SHA1.NAME).consumerKey(FULL_KEY).timestamp().nonce().version();
            OAuthSecrets secrets = new OAuthSecrets().consumerSecret(secret);

            OAuthSignature.sign(request,params,secrets);
            header = request.getOauthHeader();
            //System.out.println(OAuthSignature.verify(request,params,secrets));
            System.out.println("Authentication URL "+request.getRequestURL());
            logger.info("The header to be used " + header);
            //header = new String(Base64.getEncoder().encode(header.getBytes()));
        } catch (OAuthSignatureException ex) {
            System.out.println("Unable to sign request");
        }
        return header;
    }
        private static WebResource buildWebResource(String path, Client client) throws MalformedURLException {
            //logger.info("DATAPOWER "+DATAPOWER_PART);
            WebResource res = client.resource (PROTOCOL+THUNDERHEAD_PART+"/" + URL_PREFIX + path);
          //  System.out.println(PROTOCOL+THUNDERHEAD_PART+"/" + URL_PREFIX + path);
            return res;
        }
        public String getIntervieforms () {


            //ClientConfig clientConfig = new DefaultClientConfig();
            //clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create();
            CustomAuthRequest customAuthRequest = null;
            WebResource res=null ;
            MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
           // queryParams.add("query", "tags:INTERVIEW");

            try {
                String path=PROTOCOL+THUNDERHEAD_PART+"/" + URL_PREFIX +SEARCH_INTERVIEW+"?query=tags:INTERVIEW" ;

                res = client.resource(path);
                customAuthRequest = new CustomAuthRequest("GET", new URL(PROTOCOL + THUNDERHEAD_PART + "/" + URL_PREFIX+ SEARCH_INTERVIEW+"?query=tags:INTERVIEW"));
            } catch (MalformedURLException e) {
                logger.error("Unable to create the URL", e);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String r1=null;
             try {
                ClientConfig config = new DefaultClientConfig();
                Client client1 = Client.create(config);
            WebResource resource = client1.resource(new URI("https://na4.smartcommunications.cloud/one/oauth1/cmsSearchServices/v1/search/fts"));
            OAuthParameters params = new OAuthParameters();
            params.signatureMethod("HMAC-SHA1").consumerKey(FULL_KEY);
            OAuthSecrets secrets = new OAuthSecrets();
            secrets.consumerSecret(SHARED_SECRET);
            OAuthClientFilter filter = new OAuthClientFilter(client.getProviders(), params, secrets);
            resource.addFilter(filter);

            MultivaluedMap formParams = new MultivaluedMapImpl();
            formParams.add("query", "tags:INTERVIEW");

            r1=resource.queryParams(formParams).accept(MediaType.APPLICATION_JSON).type("application/x-www-form-urlencoded").get(String.class);
            System.out.println(r1);
            r1=r1.replace("while(1);","");

            }catch ( URISyntaxException err){
                logger.info("Error", err.toString());
            }


            return r1;

        }

        public String getProjectId(String resourceId)
        {
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
            Client client = Client.create(clientConfig);
            CustomAuthRequest customAuthRequest = null;
            WebResource res=null ;
            CmsClient cmsClient=new CmsClient();
            try {
                String path=PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+GET_PROJECT+resourceId+LATEST_VERSION ;

                res = client.resource(path);
                customAuthRequest = new CustomAuthRequest("GET", new URL(PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+GET_PROJECT+resourceId+LATEST_VERSION));
            } catch (MalformedURLException e) {
                logger.error("Unable to create the URL", e);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String r1=null;
            r1=res.header(HttpHeaders.AUTHORIZATION,cmsClient.generateOAuthHeader(customAuthRequest, API_KEY, SHARED_SECRET, SERVICE_ACCOUNT))
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class).getEntity(String.class);
            System.out.println(r1);
            r1=r1.replace("while(1);","");
            String projectId_text="\"projectId\":";
            int index=r1.indexOf(projectId_text);
            index=index+projectId_text.length();
            String projectId="";
            while(r1.charAt(index)!=',')
            {
                projectId=projectId+r1.charAt(index);
                index++;
            }
            return projectId;
        }


        public  String auth()
        {

            Client client = Client.create();
            CustomAuthRequest customAuthRequest = null;
            WebResource res=null ;


                String path= "https://na4.smartcommunications.cloud/one/idm_login";

                res = client.resource(path);
                String params="username=om.prakash@cgi.com.partner&password=Scomm$0125";
//            MultivaluedMap arguments = new MultivaluedMapImpl();
//            arguments.put("username", "om.prakash@cgi.com.partner");
//            arguments.put("password", "Scomm$0125");
            String r1=res.accept(MediaType.APPLICATION_XML)
                    .type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                    .post(ClientResponse.class,params).getEntity(String.class);
        System.out.println(r1);
          return r1;
        }

}

