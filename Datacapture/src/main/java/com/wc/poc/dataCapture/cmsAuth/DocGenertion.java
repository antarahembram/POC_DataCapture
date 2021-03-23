package com.wc.poc.dataCapture.cmsAuth;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.wc.poc.dataCapture.domain.DocGenerationProperty;
import com.wc.poc.dataCapture.domain.DocumentRequest;
import com.wc.poc.dataCapture.domain.Pdf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DocGenertion {

private static final Logger logger = LoggerFactory.getLogger(DocGenertion.class);
    private static final String PROTOCOL="https://";
    private static final String URL_PREFIX="one/oauth1";
    private static final String API_KEY="c62846e6-4b68-4fb7-a745-49045bf0fe03";
    private static final String SHARED_SECRET="ac407483-6e7c-4b5b-8f25-cd0bbb5a5607";
    private static final String SERVICE_ACCOUNT="om.prakash@cgi.com.partner";
    private static final String THUNDERHEAD_PART="na4.smartcommunications.cloud";
    private static final String GENERATE_DOC="/api/v8/job/generateDocument";
    public  Pdf generateDocument(String transactionData) {
        List<DocGenerationProperty> properties=new ArrayList<>();
        properties.add(new DocGenerationProperty("merge.pdf","true"));
        DocumentRequest documentRequest=new DocumentRequest("1","application/xml",transactionData,157697023,158132318,properties);
        System.out.println(documentRequest.getTransactionRange());
        System.out.println(documentRequest.getTransactionDataType());
        System.out.println(documentRequest.getTransactionData());
        System.out.println(documentRequest.getProjectId());
        System.out.println(documentRequest.getBatchConfigResId());
        System.out.println(documentRequest.getProperties().get(0).getName()+" "+documentRequest.getProperties().get(0).getValue());

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
                .type(MediaType.APPLICATION_XML)
                .post(ClientResponse.class, documentRequest).getEntity(String.class);
        r1=r1.replace("while(1);","");
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
        Date date = new Date();
        //This method returns the time in millis
        String dateString=date.toString();
//        String timeStamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        String[] doclist=getListOfFiles();
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
    public String[] getListOfFiles(){
        String[] pathnames;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File("..\\app-poc\\src\\assets\\document");

        // Populates the array with nameD:/Programmings of files and directories
        pathnames = f.list();

        // For each pathname in the pathnames array
//        for (String pathname : pathnames) {
//            // Print the names of files and directories
//            System.out.println(pathname);
//        }
        return pathnames;
    }

}
