package com.wc.poc.dataCapture.service;

import com.wc.poc.dataCapture.domain.Pdf;

import java.io.DataOutputStream;

public interface CmsService {
    String getInterviewForms();
    String getProjectID(String resourceId);
    Pdf generateDoc(String transactionData);
    String[] getListOfFile();
    String auth();
}
