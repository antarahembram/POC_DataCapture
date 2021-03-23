package com.wc.poc.dataCapture.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "finalizeDraftRequest")
public class DocumentRequestDraft {
    private Number projectId;
    private Number printFormat;
    private String reviewCaseData;
    private List<DocGenerationProperty> properties;

    public DocumentRequestDraft(Number projectId, Number printFormat, String reviewCaseData, List<DocGenerationProperty> properties) {
        this.projectId = projectId;
        this.printFormat = printFormat;
        this.reviewCaseData = reviewCaseData;
        this.properties = properties;
    }

    public DocumentRequestDraft() {
    }

    public Number getProjectId() {
        return projectId;
    }

    public Number getPrintFormat() {
        return printFormat;
    }

    public String getReviewCaseData() {
        return reviewCaseData;
    }

    public List<DocGenerationProperty> getProperties() {
        return properties;
    }
}
