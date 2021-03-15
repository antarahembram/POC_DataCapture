package com.wc.poc.dataCapture.controller;

import com.wc.poc.dataCapture.domain.Pdf;
import com.wc.poc.dataCapture.service.CmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

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

//    @GetMapping("/auth")
//    public ResponseEntity<?> auth() {
//        return new ResponseEntity<String>(cmsService.auth(), HttpStatus.OK);
//    }

}


