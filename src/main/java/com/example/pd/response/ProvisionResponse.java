package com.example.pd.response;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude ( JsonInclude.Include.NON_NULL )
public class ProvisionResponse
{
    @JsonProperty ( value = "statusCode" )
    @Max ( 3 )
    @NotNull
    private String statusCode;

    @JsonProperty ( value = "statusDesc" )
    @Max ( 100 )
    @NotNull
    private String statusDesc;

    @JsonProperty ( value = "orderId" )
    @Max ( 50 )
    private String orderId;

    @Override
    public String toString()
    {
        return "ProvisionResponse [statusCode=" + statusCode + ", statusDesc=" + statusDesc + ", orderId=" + orderId
                + "]";
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String statusCode )
    {
        this.statusCode = statusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String statusDesc )
    {
        this.statusDesc = statusDesc;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId( String orderId )
    {
        this.orderId = orderId;
    }
    
}
