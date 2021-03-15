package com.wc.poc.dataCapture.domain;

public class Pdf {
    private String pdfName;
    private String pdfPath;

    public Pdf(String pdfName, String pdfPath) {
        this.pdfName = pdfName;
        this.pdfPath = pdfPath;
    }

    public String getPdfName() {
        return pdfName;
    }

    public String getPdfPath() {
        return pdfPath;
    }
}
