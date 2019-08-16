package com.example.pd.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.util.Assert;

import com.example.pd.AbstractIdentifiableEntity;
import com.example.pd.enumeration.OrderStatus;

@Entity
@Table ( name = "order_details", indexes =
{ @Index ( name = "orderId_idx", columnList = "orderId" ) } )
public class OrderDetails extends AbstractIdentifiableEntity
{
    @Column ( name = "orderId", nullable = false )
    private String orderId;
    private String appName;
    private String requestId;
    private String billingKeyCode;
    private String crdDate;
    private String endDate;
    private String duration;
    private String svcNo;
    private String addOnType;
    private String paymentId;
    private String emailAddr;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    private OrderStatus status;
    private String errorCode;
    private String errorMsg;
    private String msCallBackStatusCode;
    private String serviceNumber;
    private String authorizePurchaseId;

    public void updateFailure( final String errorCode, final String errorMsg, final OrderStatus status )
    {
        Assert.notNull( errorMsg, String.format( "%s cannot be NULL/EMPTY", "errorMsg" ) );
        Assert.notNull( status, String.format( "%s cannot be NULL/EMPTY", "status" ) );
        this.setErrorCode( errorCode );
        this.setErrorMsg( errorMsg );
        this.setCompletionDate( LocalDateTime.now() );
        this.setStatus( status );
    }

    public void updateCompletion( final String orderId, final OrderStatus status )
    {
        Assert.notNull( orderId, String.format( "%s cannot be NULL/EMPTY", "orderId" ) );
        Assert.notNull( status, String.format( "%s cannot be NULL/EMPTY", "status" ) );
        this.setCompletionDate( LocalDateTime.now() );
        this.setStatus( status );
        this.setOrderId( orderId );
    }

    public void updateMSStatus( final String msCallBackStatusCode )
    {
        Assert.notNull( msCallBackStatusCode, String.format( "%s cannot be NULL/EMPTY", "msCallBackStatusCode" ) );
        this.setCompletionDate( LocalDateTime.now() );
        this.setMsCallBackStatusCode( msCallBackStatusCode );
    }

    @PrePersist
    public void onPrePersist()
    {
        LocalDateTime now = LocalDateTime.now();
        this.creationDate = now;
    }
    
    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId( String orderId )
    {
        this.orderId = orderId;
    }

    public String getAppName()
    {
        return appName;
    }

    public void setAppName( String appName )
    {
        this.appName = appName;
    }

    public String getRequestId()
    {
        return requestId;
    }

    public void setRequestId( String requestId )
    {
        this.requestId = requestId;
    }

    public String getBillingKeyCode()
    {
        return billingKeyCode;
    }

    public void setBillingKeyCode( String billingKeyCode )
    {
        this.billingKeyCode = billingKeyCode;
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

    public LocalDateTime getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate( LocalDateTime creationDate )
    {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompletionDate()
    {
        return completionDate;
    }

    public void setCompletionDate( LocalDateTime completionDate )
    {
        this.completionDate = completionDate;
    }

    public OrderStatus getStatus()
    {
        return status;
    }

    public void setStatus( OrderStatus status )
    {
        this.status = status;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode( String errorCode )
    {
        this.errorCode = errorCode;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg( String errorMsg )
    {
        this.errorMsg = errorMsg;
    }

    public String getMsCallBackStatusCode()
    {
        return msCallBackStatusCode;
    }

    public void setMsCallBackStatusCode( String msCallBackStatusCode )
    {
        this.msCallBackStatusCode = msCallBackStatusCode;
    }

    public String getServiceNumber()
    {
        return serviceNumber;
    }

    public void setServiceNumber( String serviceNumber )
    {
        this.serviceNumber = serviceNumber;
    }

    public String getAuthorizePurchaseId()
    {
        return authorizePurchaseId;
    }

    public void setAuthorizePurchaseId( String authorizePurchaseId )
    {
        this.authorizePurchaseId = authorizePurchaseId;
    }

    @Override
    public String toString()
    {
        return "OrderDetails [orderId=" + orderId + ", appName=" + appName + ", requestId=" + requestId
                + ", billingKeyCode=" + billingKeyCode + ", crdDate=" + crdDate + ", endDate=" + endDate + ", duration="
                + duration + ", svcNo=" + svcNo + ", addOnType=" + addOnType + ", paymentId=" + paymentId
                + ", emailAddr=" + emailAddr + ", creationDate=" + creationDate + ", completionDate=" + completionDate
                + ", status=" + status + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
                + ", msCallBackStatusCode=" + msCallBackStatusCode + ", serviceNumber=" + serviceNumber
                + ", authorizePurchaseId=" + authorizePurchaseId + "]";
    }

}
