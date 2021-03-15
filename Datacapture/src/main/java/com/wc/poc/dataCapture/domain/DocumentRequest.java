package com.wc.poc.dataCapture.domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "documentRequest")
public class DocumentRequest {
    private String transactionRange;
    private String transactionDataType;
    private String transactionData;
    private Number projectId;
    private Number batchConfigResId;
    private List<DocGenerationProperty> properties;

    public DocumentRequest() {
    }

    public DocumentRequest(String transactionRange, String transactionDataType, String transactionData, Number projectId, Number batchConfigResId, List<DocGenerationProperty> properties) {
        this.transactionRange = transactionRange;
        this.transactionDataType = transactionDataType;
        this.transactionData = transactionData;
        this.projectId = projectId;
        this.batchConfigResId = batchConfigResId;
        this.properties = properties;
    }

    public String getTransactionRange() {
        return transactionRange;
    }

    public void setTransactionRange(String transactionRange) {
        this.transactionRange = transactionRange;
    }

    public String getTransactionDataType() {
        return transactionDataType;
    }

    public void setTransactionDataType(String transactionDataType) {
        this.transactionDataType = transactionDataType;
    }

    public String getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(String transactionData) {
        this.transactionData = transactionData;
    }

    public Number getProjectId() {
        return projectId;
    }

    public void setProjectId(Number projectId) {
        this.projectId = projectId;
    }

    public Number getBatchConfigResId() {
        return batchConfigResId;
    }

    public void setBatchConfigResId(Number batchConfigResId) {
        this.batchConfigResId = batchConfigResId;
    }

    public List<DocGenerationProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<DocGenerationProperty> properties) {
        this.properties = properties;
    }
}
