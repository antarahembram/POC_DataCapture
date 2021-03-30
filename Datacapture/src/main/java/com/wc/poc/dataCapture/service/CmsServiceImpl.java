package com.wc.poc.dataCapture.service;


import com.wc.poc.dataCapture.cmsAuth.CmsClient;
import com.wc.poc.dataCapture.cmsAuth.DocGenertion;
import com.wc.poc.dataCapture.cmsAuth.DraftEditor;
import com.wc.poc.dataCapture.domain.DraftXml;
import com.wc.poc.dataCapture.domain.Pdf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CmsServiceImpl implements  CmsService{
    private static final Logger logger= LoggerFactory.getLogger(CmsServiceImpl.class);

    @Autowired
    CmsClient cmsClient;

    @Autowired
    DocGenertion docGenertion;

    @Autowired
    DraftEditor draftEditor;
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

    @Override
    public DraftXml getDraftXml(String transactionData) {
//        transactionData="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48ZGF0YUNhcHR1cmU+CiAgPFRSQU5TQUNUSU9OSU5GTz4KICAgIDxwcm9kVHlwZS8+CiAgICA8dHJhbnNhY3Rpb25UeXBlLz4KICA8cHJvZFR5cGUxPmNhcmQ8L3Byb2RUeXBlMT48bWVyY2hhbnQvPjxhY2N0TnVtYmVyLz48dHJhbnNhY3Rpb25EYXRlMS8+PHRyYW5zYWN0aW9uQW10MS8+PHRyYW5zYWN0aW9uQW10Mi8+PC9UUkFOU0FDVElPTklORk8+CiAgPENVU1RPTUVSSU5GTz4KICAgIDxidXNpbmVzc05hbWU+Sy5HLiBTYXVyPC9idXNpbmVzc05hbWU+CiAgICA8YWRkcmVzc0xpbmUxPk4uSy4gU3RyZWV0PC9hZGRyZXNzTGluZTE+CiAgICA8YWRkcmVzc0xpbmUyLz4KICAgIDxjaXR5Lz4KICAgIDxzdGF0ZS8+CiAgICA8cG9zdGFsQ29kZS8+CiAgPC9DVVNUT01FUklORk8+Cjxmb3JtSWQ+MTU4MTMyMzEzPC9mb3JtSWQ+PC9kYXRhQ2FwdHVyZT4=";
        return draftEditor.getDraftXml(transactionData);
    }

    @Override
    public Pdf generateDocumentDaft(String xml) {

        return draftEditor.generateDocument(xml);
    }

    @Override
    public String getResource(String resourceId) {
        return draftEditor.getResource(resourceId);
    }

}
