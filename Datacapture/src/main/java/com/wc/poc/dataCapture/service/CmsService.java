package com.wc.poc.dataCapture.service;

import com.wc.poc.dataCapture.domain.DraftXml;
import com.wc.poc.dataCapture.domain.Pdf;

import java.io.DataOutputStream;

public interface CmsService {
    String getInterviewForms();                     //get list of interview templates from the cms
    String getProjectID(String resourceId);         //get the project ID of a template
    Pdf generateDoc(String transactionData);        //Generate document after data capture
    String[] getListOfFile();                       //List of pdf file in assets/document folder
    String auth();

    DraftXml getDraftXml(String  transactionData);
    Pdf generateDocumentDaft(String xml);
}
