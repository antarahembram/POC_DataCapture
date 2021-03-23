package com.wc.poc.dataCapture.controller;

import com.wc.poc.dataCapture.domain.DraftXml;
import com.wc.poc.dataCapture.domain.Pdf;
import com.wc.poc.dataCapture.service.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CmsController {
    CmsService cmsService;

    @Autowired
    public CmsController(CmsService cmsService) {
        this.cmsService = cmsService;
    }


    @GetMapping("/interviewtemplates")
    public ResponseEntity<?> interview() {
        return new ResponseEntity<String>(cmsService.getInterviewForms(), HttpStatus.OK);
    }


    @PostMapping("/generate")
    public ResponseEntity<?> generatePdf(@RequestBody String transactionData)
    {

        return new ResponseEntity<Pdf>(cmsService.generateDoc(transactionData), HttpStatus.OK);

    }

    @GetMapping("/getproject/{resourceId}")
    public ResponseEntity<?> getProjectID(@PathVariable("resourceId") String resourceId) {
        return new ResponseEntity<String>(cmsService.getProjectID(resourceId), HttpStatus.OK);
    }

    @GetMapping("/getDocList")
    public ResponseEntity<?> getDocList() {
        return new ResponseEntity<String[]>(cmsService.getListOfFile(), HttpStatus.OK);
    }
    @PostMapping("/draftXml")
    public ResponseEntity<?> getDraftXml(@RequestBody String transactionData)
    {

//        transactionData="PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz48ZGF0YUNhcHR1cmU+CiAgPFRSQU5TQUNUSU9OSU5GTz4KICAgIDxwcm9kVHlwZS8+CiAgICA8dHJhbnNhY3Rpb25UeXBlLz4KICA8cHJvZFR5cGUxPmNhcmQ8L3Byb2RUeXBlMT48bWVyY2hhbnQvPjxhY2N0TnVtYmVyLz48dHJhbnNhY3Rpb25EYXRlMS8+PHRyYW5zYWN0aW9uQW10MS8+PHRyYW5zYWN0aW9uQW10Mi8+PC9UUkFOU0FDVElPTklORk8+CiAgPENVU1RPTUVSSU5GTz4KICAgIDxidXNpbmVzc05hbWU+Sy5HLiBTYXVyPC9idXNpbmVzc05hbWU+CiAgICA8YWRkcmVzc0xpbmUxPk4uSy4gU3RyZWV0PC9hZGRyZXNzTGluZTE+CiAgICA8YWRkcmVzc0xpbmUyLz4KICAgIDxjaXR5Lz4KICAgIDxzdGF0ZS8+CiAgICA8cG9zdGFsQ29kZS8+CiAgPC9DVVNUT01FUklORk8+Cjxmb3JtSWQ+MTU4MTMyMzEzPC9mb3JtSWQ+PC9kYXRhQ2FwdHVyZT4=";
        return new ResponseEntity<DraftXml>(cmsService.getDraftXml(transactionData), HttpStatus.OK);

    }

    @PostMapping("/generateDraft")
    public ResponseEntity<?> generatePdfDraft(@RequestBody String transactionData)
    {

        return new ResponseEntity<Pdf>(cmsService.generateDocumentDaft(transactionData), HttpStatus.OK);

    }



//    @GetMapping("/auth")
//    public ResponseEntity<?> auth() {
//        return new ResponseEntity<String>(cmsService.auth(), HttpStatus.OK);
//    }

}


