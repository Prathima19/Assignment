package com.example.si;

import static com.example.pd.util.Constants.STATUS_010;
import static com.example.pd.util.Constants.STATUS_000;
import static com.example.pd.util.Constants.STATUS_999;
import static com.example.pd.util.Constants.SUCCESS;
import static com.example.pd.util.Constants.FAIL;
import static com.example.pd.util.Constants.EMPTY;
import static com.example.pd.util.Constants.ERROR_INVALID_REQUEST;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pd.IProvisionService;
import com.example.pd.model.OrderDetails;
import com.example.pd.request.ProvisionRequest;
import com.example.pd.response.ProvisionResponse;

@Service
public class ProvisionServiceImpl implements IProvisionService
{
    private static final Logger log = LoggerFactory.getLogger( ProvisionServiceImpl.class );

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Override
    public ProvisionResponse provisionService( final ProvisionRequest provisionRequest )
    {
        // Step1 : Validate input parameters
        if ( !validate( provisionRequest ) )
        {
            final ProvisionResponse provisionResponse = populateResponse( STATUS_010, ERROR_INVALID_REQUEST, EMPTY );

            log.info( "Input validation failed" );
            return provisionResponse;
        }

        // Step2 : store the request into OrderDetails table
        OrderDetails newOrder;
        try
        {
            newOrder = orderDetailsService.initOrder( provisionRequest );

            final ProvisionResponse provisionResponse = populateResponse( STATUS_000, SUCCESS,
                    newOrder.getId().toString() );

            return provisionResponse;

        } catch ( Exception e )
        {
            final ProvisionResponse provisionResponse = populateResponse( STATUS_999, FAIL, EMPTY );
            log.error( e.getMessage() );
            return provisionResponse;
        }
    }

    private ProvisionResponse populateResponse( String statusCode, String statusDesc, String orderId )
    {
        ProvisionResponse provisionResponse = new ProvisionResponse();

        provisionResponse.setStatusCode( statusCode );
        provisionResponse.setStatusDesc( statusDesc );
        provisionResponse.setOrderId( orderId );

        return provisionResponse;
    }

    private boolean validate( ProvisionRequest provisionRequest )
    {
        if ( StringUtils.isBlank( provisionRequest.getAppName() ) )
        {
            log.warn( "empty or null appName" );
            return false;
        }

        if ( StringUtils.isBlank( provisionRequest.getRequestId() ) )
        {
            log.warn( "empty or null requestId" );
            return false;
        }

        if ( StringUtils.isBlank( provisionRequest.getBillingKeyCode() ) )
        {
            log.warn( "empty or null service" );
            return false;
        }

        if ( StringUtils.isBlank( provisionRequest.getSvcNo() ) )
        {
            log.warn( "empty or null svcNo" );
            return false;
        }

        if ( StringUtils.isBlank( provisionRequest.getAddOnType() ) )
        {
            log.warn( "empty or null addonType" );
            return false;
        }

        return true;
    }

}
