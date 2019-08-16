package com.example.si;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.pd.AbstractIdentifiableEntity;
import com.example.pd.enumeration.OrderStatus;
import com.example.pd.model.OrderDetails;
import com.example.pd.request.ProvisionRequest;
import com.example.pd.response.ProvisionResponse;

@RunWith ( SpringJUnit4ClassRunner.class )
public class ProvisionServiceImplTest
{
    private static final Logger logger = LoggerFactory.getLogger( ProvisionServiceImplTest.class );

    @Mock
    private Environment env;

    @Mock
    private OrderDetailsService orderDetailsService;

    @InjectMocks
    private ProvisionServiceImpl provisionServiceImpl;

    @Test
    public void testValidateCustomerOrder_NullAppName()
    {
        ProvisionRequest request = buildRequest();
        request.setAppName( null );

        ProvisionResponse actualResponse = provisionServiceImpl.provisionService( request );

        logger.info( actualResponse.toString() );

        assertEquals( "010", actualResponse.getStatusCode() );
        assertEquals( "Invalid Request", actualResponse.getStatusDesc() );
    }
    
    @Test
    public void testValidateCustomerOrder_NullRequestId()
    {
        ProvisionRequest request = buildRequest();
        request.setRequestId( null );

        ProvisionResponse actualResponse = provisionServiceImpl.provisionService( request );

        logger.info( actualResponse.toString() );

        assertEquals( "010", actualResponse.getStatusCode() );
        assertEquals( "Invalid Request", actualResponse.getStatusDesc() );
    }

    @Test
    public void testValidateCustomerOrder_NullBillingKeyCode()
    {
        ProvisionRequest request = buildRequest();
        request.setBillingKeyCode( null );
        
        ProvisionResponse actualResponse = provisionServiceImpl.provisionService( request );

        logger.info( actualResponse.toString() );

        assertEquals( "010", actualResponse.getStatusCode() );
        assertEquals( "Invalid Request", actualResponse.getStatusDesc() );
    }

    @Test
    public void testValidateCustomerOrder_NullSvcNo()
    {
        ProvisionRequest request = buildRequest();
        request.setSvcNo( null );

        ProvisionResponse actualResponse = provisionServiceImpl.provisionService( request );

        logger.info( actualResponse.toString() );

        assertEquals( "010", actualResponse.getStatusCode() );
        assertEquals( "Invalid Request", actualResponse.getStatusDesc() );
    }

    @Test
    public void testValidateCustomerOrder_NullAddOnType()
    {
        ProvisionRequest request = buildRequest();
        request.setAddOnType( "" );

        ProvisionResponse actualResponse = provisionServiceImpl.provisionService( request );

        logger.info( actualResponse.toString() );

        assertEquals( "010", actualResponse.getStatusCode() );
        assertEquals( "Invalid Request", actualResponse.getStatusDesc() );
    }

    @Test
    public void testValidateCustomerOrder_Success()
    {
        when( orderDetailsService.initOrder( anyObject() ) ).thenReturn( buildOrderDetails() );
        ProvisionResponse actualResponse = provisionServiceImpl.provisionService( buildRequest() );

        logger.info( actualResponse.toString() );

        assertEquals( "000", actualResponse.getStatusCode() );
        assertEquals( "Success", actualResponse.getStatusDesc() );
    }

    @Test
    public void testValidateCustomerOrder_Exception()
    {
        when( orderDetailsService.initOrder( anyObject() ) ).thenReturn( buildOrderDetailsWithoutId() );
        ProvisionResponse actualResponse = provisionServiceImpl.provisionService( buildRequest() );

        logger.info( actualResponse.toString() );

        assertEquals( "999", actualResponse.getStatusCode() );
        assertEquals( "Fail", actualResponse.getStatusDesc() );
    }

    private ProvisionRequest buildRequest()
    {
        ProvisionRequest request = new ProvisionRequest();
        request.setAppName( "instantPayPortal" );
        request.setAddOnType( "roaming" );
        request.setCrdDate( "20190725 000000" );
        request.setDuration( "3" );
        request.setEndDate( "20190830 000000" );
        request.setRequestId( "20190711111512" );
        request.setBillingKeyCode( "BDRHoliday9Dest" );
        request.setSvcNo( "98372453" );
        request.setPaymentId( "123456789" );
        request.setEmailAddr( "test@gmail.com" );
        return request;
    }

    private OrderDetails buildOrderDetails()
    {
        OrderDetails newOrderDetails = new OrderDetails();

        newOrderDetails.setAppName( "instantPayPortal" );
        newOrderDetails.setAddOnType( "roaming" );
        newOrderDetails.setBillingKeyCode( "BDRHoliday9Dest" );
        newOrderDetails.setCompletionDate( LocalDateTime.now() );
        newOrderDetails.setCrdDate( "20190725 000000" );
        newOrderDetails.setCreationDate( LocalDateTime.now() );
        newOrderDetails.setDuration( "3" );
        newOrderDetails.setEmailAddr( "test@gmail.com" );
        newOrderDetails.setEndDate( "20190830 000000" );
        newOrderDetails.setErrorCode( "" );
        newOrderDetails.setErrorMsg( "" );
        newOrderDetails.setMsCallBackStatusCode( "" );
        newOrderDetails.setOrderId( "123456789" );
        newOrderDetails.setPaymentId( "123456789" );
        newOrderDetails.setRequestId( "20190711111512" );
        newOrderDetails.setStatus( OrderStatus.NEW );
        newOrderDetails.setSvcNo( "98372453" );

        try
        {
            Field privateStringField = AbstractIdentifiableEntity.class.getDeclaredField( "id" );
            privateStringField.setAccessible( true );
            privateStringField.set( newOrderDetails, UUID.randomUUID() );
        } catch ( Exception e )
        {
            fail();
        }

        return newOrderDetails;
    }

    private OrderDetails buildOrderDetailsWithoutId()
    {
        OrderDetails newOrderDetails = new OrderDetails();

        newOrderDetails.setAppName( "instantPayPortal" );
        newOrderDetails.setAddOnType( "roaming" );
        newOrderDetails.setBillingKeyCode( "BDRHoliday9Dest" );
        newOrderDetails.setCompletionDate( LocalDateTime.now() );
        newOrderDetails.setCrdDate( "20190725 000000" );
        newOrderDetails.setCreationDate( LocalDateTime.now() );
        newOrderDetails.setDuration( "3" );
        newOrderDetails.setEmailAddr( "test@gmail.com" );
        newOrderDetails.setEndDate( "20190830 000000" );
        newOrderDetails.setErrorCode( "" );
        newOrderDetails.setErrorMsg( "" );
        newOrderDetails.setMsCallBackStatusCode( "" );
        newOrderDetails.setOrderId( "123456789" );
        newOrderDetails.setPaymentId( "123456789" );
        newOrderDetails.setRequestId( "20190711111512" );
        newOrderDetails.setStatus( OrderStatus.NEW );
        newOrderDetails.setSvcNo( "98372453" );

        return newOrderDetails;
    }
}
