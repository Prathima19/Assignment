package com.example.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.api.controller.ProvisionServiceController;
import com.example.pd.IProvisionService;
import com.example.pd.request.ProvisionRequest;
import com.example.pd.response.ProvisionResponse;
import com.example.pd.util.JsonUtils;

@RunWith ( SpringJUnit4ClassRunner.class )
public class ProvisionServiceControllerTest
{
    @Mock
    private IProvisionService iProvisionService;

    @InjectMocks
    private ProvisionServiceController provisionServiceController;

    @Test
    public void testProvisionService_Success()
    {
        ProvisionRequest request = buildRequest();
        ProvisionResponse response = buildResponse();

        when( iProvisionService.provisionService( eq( request ) ) ).thenReturn( response );

        ResponseEntity<ProvisionResponse> responseEntity = provisionServiceController.provisionService( request );

        System.out.println( JsonUtils.toJson( responseEntity ) );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( "000", responseEntity.getBody().getStatusCode() );
    }

    @Test
    public void testProvisionService_InvalidRequest()
    {
        ProvisionRequest request = buildRequest();
        ProvisionResponse response = new ProvisionResponse();
        response.setStatusCode( "010" );
        response.setStatusDesc( "Invalid Request" );

        when( iProvisionService.provisionService( eq( request ) ) ).thenReturn( response );

        ResponseEntity<ProvisionResponse> responseEntity = provisionServiceController.provisionService( request );

        assertEquals( HttpStatus.BAD_REQUEST, responseEntity.getStatusCode() );
        assertEquals( "010", responseEntity.getBody().getStatusCode() );
    }

    @Test
    public void testProvisionService_Exception()
    {
        ProvisionRequest request = buildRequest();
        ProvisionResponse response = new ProvisionResponse();
        response.setStatusCode( "999" );
        response.setStatusDesc( "Fail" );

        when( iProvisionService.provisionService( eq( request ) ) ).thenReturn( response );

        ResponseEntity<ProvisionResponse> responseEntity = provisionServiceController.provisionService( request );

        assertEquals( HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode() );
        assertEquals( "999", responseEntity.getBody().getStatusCode() );
    }

    private ProvisionRequest buildRequest()
    {
        ProvisionRequest request = new ProvisionRequest();
        request.setRequestId( "20190711111512" );
        request.setAppName( "InstantPayPortal" );
        request.setCrdDate( "20190712 000000" );
        request.setEndDate( "20190713 000000" );
        request.setAddOnType( "roaming" );
        request.setBillingKeyCode( "12345" );
        request.setDuration( "3" );
        request.setSvcNo( "98372453" );
        request.setPaymentId( "123456789" );
        request.setEmailAddr( "test@gmail.com" );
        return request;
    }

    private ProvisionResponse buildResponse()
    {
        ProvisionResponse response = new ProvisionResponse();
        response.setStatusCode( "000" );
        response.setStatusDesc( "Success" );
        response.setOrderId( "123" );
        return response;
    }
}
