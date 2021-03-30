package com.wc.poc.dataCapture.cmsAuth;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.wc.poc.dataCapture.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class DraftEditor {
    private static final Logger logger = LoggerFactory.getLogger(DraftEditor.class);

    private static final String PROTOCOL="https://";
    private static final String URL_PREFIX="one/oauth1";
    private static final String API_KEY="c62846e6-4b68-4fb7-a745-49045bf0fe03";
    private static final String SHARED_SECRET="ac407483-6e7c-4b5b-8f25-cd0bbb5a5607";
    private static final String SERVICE_ACCOUNT="om.prakash@cgi.com.partner";
    private static final String THUNDERHEAD_PART="na4.smartcommunications.cloud";
    private static final String GENERATE_DRAFT="/api/v8/job/generateDraft";
    private static final String GENERATE_DOC="/api/v8/job/finalizeDraft";
    private static final String GET_KEYWORD="/cms/v5/resources/";

    @Autowired
    DocGenertion docGenertion;

    public DraftXml getDraftXml(String transactionData)
    {

        List<DocGenerationProperty> properties=new ArrayList<>();
        properties.add(new DocGenerationProperty("merge.pdf","true"));
        DocumentRequest documentRequest=new DocumentRequest("1","application/xml",transactionData,157697023,158132318,properties);
//        System.out.println(documentRequest.getTransactionRange());
//        System.out.println(documentRequest.getTransactionDataType());
//        System.out.println(documentRequest.getTransactionData());
//        System.out.println(documentRequest.getProjectId());
//        System.out.println(documentRequest.getBatchConfigResId());
//        System.out.println(documentRequest.getProperties().get(0).getName()+" "+documentRequest.getProperties().get(0).getValue());

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        CustomAuthRequest customAuthRequest = null;
        WebResource res=null ;
        CmsClient cmsClient=new CmsClient();
        try {
            String path=PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+GENERATE_DRAFT ;

            res = client.resource(path);
            customAuthRequest = new CustomAuthRequest("POST", new URL(PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+GENERATE_DRAFT));
        } catch (MalformedURLException e) {
            logger.error("Unable to create the URL", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String r1=null;
        r1=res.header(HttpHeaders.AUTHORIZATION,cmsClient.generateOAuthHeader(customAuthRequest, API_KEY, SHARED_SECRET, SERVICE_ACCOUNT))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, documentRequest).getEntity(String.class);
        r1=r1.replace("while(1);","");
        String location="\"reviewCaseLocation\":\"";
        int index=r1.indexOf(location);
        index=index+location.length();
        String DRAFT_XML_PATH="";
        while(r1.charAt(index)!='"')
        {
            DRAFT_XML_PATH=DRAFT_XML_PATH+r1.charAt(index);
            index++;
        }
        System.out.println(DRAFT_XML_PATH);

        Client client2 = Client.create(clientConfig);
        CustomAuthRequest customAuthRequest2 = null;
        WebResource res2=null ;
        try {
            String path=PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+"/api" +DRAFT_XML_PATH;

            res2 = client2.resource(path);
            customAuthRequest2 = new CustomAuthRequest("GET", new URL(PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+"/api" +DRAFT_XML_PATH));
        } catch (MalformedURLException e) {
            logger.error("Unable to create the URL", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String xml=res2.header(HttpHeaders.AUTHORIZATION,cmsClient.generateOAuthHeader(customAuthRequest2, API_KEY, SHARED_SECRET, SERVICE_ACCOUNT))
                .accept(MediaType.APPLICATION_XML).get(ClientResponse.class).getEntity(String.class);
            logger.info(xml);

        DraftXml draftXml=new DraftXml(xml);
        return draftXml;
    }

    public Pdf generateDocument(String transactionData) {
        List<DocGenerationProperty> properties=new ArrayList<>();
        String base64encodedString ="";
        try {
            base64encodedString=Base64.getEncoder().encodeToString(
                    transactionData.getBytes("utf-8"));
        }
        catch( UnsupportedEncodingException e)
        {
            logger.info("Exception");
        }
        System.out.println(base64encodedString);
        properties.add(new DocGenerationProperty("merge.pdf","true"));
        DocumentRequestDraft documentRequestDraft=new DocumentRequestDraft(157697023,3,base64encodedString,properties);
        System.out.println(documentRequestDraft.getProjectId());
        System.out.println(documentRequestDraft.getPrintFormat());
        System.out.println(documentRequestDraft.getReviewCaseData());
        System.out.println(documentRequestDraft.getProperties().get(0).getName()+" "+documentRequestDraft.getProperties().get(0).getValue());

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        CustomAuthRequest customAuthRequest = null;
        WebResource res=null ;
        CmsClient cmsClient=new CmsClient();
        try {
            String path=PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+GENERATE_DOC ;

            res = client.resource(path);
            customAuthRequest = new CustomAuthRequest("POST", new URL(PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+GENERATE_DOC));
        } catch (MalformedURLException e) {
            logger.error("Unable to create the URL", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String r1=null;
        r1=res.header(HttpHeaders.AUTHORIZATION,cmsClient.generateOAuthHeader(customAuthRequest, API_KEY, SHARED_SECRET, SERVICE_ACCOUNT))
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, documentRequestDraft).getEntity(String.class);
        r1=r1.replace("while(1);","");

        logger.info("partLocation"+r1);
        String location="\"partLocation\":\"";
        int index=r1.indexOf(location);
        index=index+location.length();
        String PDF_PATH="";
        while(r1.charAt(index)!='"')
        {
            PDF_PATH=PDF_PATH+r1.charAt(index);
            index++;
        }
        String docName="\"documentName\":\"";
        index=r1.indexOf(docName);
        index=index+docName.length();
        String DOCUMENT_NAME="";
        while(r1.charAt(index)!='"')
        {
            DOCUMENT_NAME=DOCUMENT_NAME+r1.charAt(index);
            index++;
        }
        logger.info(PDF_PATH+" "+DOCUMENT_NAME);
        String[] doclist=docGenertion.getListOfFiles();
        int i=0;
        for(String doc:doclist)
        {
            if(doc.contains(DOCUMENT_NAME))
            {
                i++;
            }
        }
        String indx=Integer.toString(i);
        if(i!=0){
            DOCUMENT_NAME=DOCUMENT_NAME+indx+".pdf";}
        else{
            DOCUMENT_NAME=DOCUMENT_NAME+".pdf";}



        System.out.println(DOCUMENT_NAME);

        Client client2 = Client.create(clientConfig);
        CustomAuthRequest customAuthRequest2 = null;
        WebResource res2=null ;
        try {
            String path=PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+"/api" +PDF_PATH;

            res2 = client2.resource(path);
            customAuthRequest2 = new CustomAuthRequest("GET", new URL(PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+"/api" +PDF_PATH));
        } catch (MalformedURLException e) {
            logger.error("Unable to create the URL", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File s=res2.header(HttpHeaders.AUTHORIZATION,cmsClient.generateOAuthHeader(customAuthRequest2, API_KEY, SHARED_SECRET, SERVICE_ACCOUNT))
                .accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(File.class);
        String documentPath="..\\app-poc\\src\\assets\\document\\";
        File ff = new File(documentPath+DOCUMENT_NAME);

        s.renameTo(ff);
        try {
            FileWriter fr = new FileWriter(s);
            fr.flush();
        }
        catch (IOException e)
        {
            logger.info(e.toString());
        }



        Pdf pdf= new Pdf(DOCUMENT_NAME,documentPath);
        System.out.println(pdf.getPdfName());
        return  pdf;
    }


    public String getResource(String resourceId)
    {

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        Client client = Client.create(clientConfig);
        CustomAuthRequest customAuthRequest = null;
        WebResource res=null ;
        CmsClient cmsClient=new CmsClient();
        try {
            String path=PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+GET_KEYWORD +resourceId;

            res = client.resource(path);
            customAuthRequest = new CustomAuthRequest("GET", new URL(PROTOCOL+THUNDERHEAD_PART+"/"+URL_PREFIX+GET_KEYWORD+resourceId));
        } catch (MalformedURLException e) {
            logger.error("Unable to create the URL", e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String r1=null;
        r1=res.header(HttpHeaders.AUTHORIZATION,cmsClient.generateOAuthHeader(customAuthRequest, API_KEY, SHARED_SECRET, SERVICE_ACCOUNT))
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class).getEntity(String.class);
        r1=r1.replace("while(1);","");
        return r1;
    }

}
