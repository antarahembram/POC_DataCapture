package com.wc.poc.dataCapture.service;


import com.wc.poc.dataCapture.cmsAuth.CmsClient;
import com.wc.poc.dataCapture.cmsAuth.DocGenertion;
import com.wc.poc.dataCapture.domain.Pdf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataOutputStream;


@Service
public class CmsServiceImpl implements  CmsService{
    private static final Logger logger= LoggerFactory.getLogger(CmsServiceImpl.class);

    @Autowired
    CmsClient cmsClient;

    @Autowired
    DocGenertion docGenertion;

    @Override
    public String getInterviewForms(){
        String response=cmsClient.getIntervieforms();
        return response;
    }

    @Override
    public String getProjectID(String resourceId) {
        return  cmsClient.getProjectId(resourceId);
    }


    @Override
    public Pdf generateDoc(String transactionData) {
        return docGenertion.generateDocument(transactionData);
    }

    @Override
    public String[] getListOfFile() {
        return docGenertion.getListOfFiles();
    }

    @Override
    public String auth() {
        return cmsClient.auth();
    }

}
