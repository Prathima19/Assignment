package com.example.pd.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude ( JsonInclude.Include.NON_NULL )
public class ProvisionRequest
{
    @JsonProperty ( value = "requestId" )
    @Max ( 50 )
    @NotNull
    private String requestId;

    @JsonProperty ( value = "appName" )
    @Max ( 50 )
    @NotNull
    private String appName;

    @JsonProperty ( value = "crdDate" )
    @Max ( 15 )
    private String crdDate;

    @JsonProperty ( value = "endDate" )
    @Max ( 15 )
    private String endDate;

    @JsonProperty ( value = "billingKeyCode" )
    @Max ( 50 )
    @NotNull
    private String billingKeyCode;

    @JsonProperty ( value = "duration" )
    @Max ( 3 )
    private String duration;

    @JsonProperty ( value = "svcNo" )
    @Max ( 8 )
    @NotNull
    private String svcNo;

    @JsonProperty ( value = "addOnType" )
    @Max ( 20 )
    @NotNull
    private String addOnType;

    @JsonProperty ( value = "paymentId" )
    @Max ( 50 )
    @NotNull
    private String paymentId;

    @JsonProperty ( value = "emailAddr" )
    @Max ( 50 )
    @NotNull
    private String emailAddr;
    
    @JsonProperty ( value = "authorizePurchaseId" )
    @Max ( 50 )
    @NotNull
    private String authorizePurchaseId;

    @Override
    public String toString()
    {
        return "ProvisionRequest [requestId=" + requestId + ", appName=" + appName + ", crdDate=" + crdDate
                + ", endDate=" + endDate + ", billingKeyCode=" + billingKeyCode + ", duration=" + duration + ", svcNo="
                + svcNo + ", addOnType=" + addOnType + ", paymentId=" + paymentId + ", emailAddr=" + emailAddr
                + ", authorizePurchaseId=" + authorizePurchaseId + "]";
    }

    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId( String requestId )
    {
        this.requestId = requestId;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName( String appName )
    {
        this.appName = appName;
    }

    public String getCrdDate()
    {
        return crdDate;
    }

    public void setCrdDate( String crdDate )
    {
        this.crdDate = crdDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate( String endDate )
    {
        this.endDate = endDate;
    }

    public String getBillingKeyCode()
    {
        return billingKeyCode;
    }

    public void setBillingKeyCode( String billingKeyCode )
    {
        this.billingKeyCode = billingKeyCode;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration( String duration )
    {
        this.duration = duration;
    }

    public String getSvcNo()
    {
        return svcNo;
    }

    public void setSvcNo( String svcNo )
    {
        this.svcNo = svcNo;
    }

    public String getAddOnType()
    {
        return addOnType;
    }

    public void setAddOnType( String addOnType )
    {
        this.addOnType = addOnType;
    }

    public String getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId( String paymentId )
    {
        this.paymentId = paymentId;
    }

    public String getEmailAddr()
    {
        return emailAddr;
    }

    public void setEmailAddr( String emailAddr )
    {
        this.emailAddr = emailAddr;
    }

    public String getAuthorizePurchaseId()
    {
        return authorizePurchaseId;
    }

    public void setAuthorizePurchaseId( String authorizePurchaseId )
    {
        this.authorizePurchaseId = authorizePurchaseId;
    }
    
}
