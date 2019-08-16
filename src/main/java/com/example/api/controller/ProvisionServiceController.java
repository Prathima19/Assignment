package com.example.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pd.IProvisionService;
import com.example.pd.dm.OrderDetailsRepository;
import com.example.pd.model.OrderDetails;
import com.example.pd.request.ProvisionRequest;
import com.example.pd.response.ProvisionResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api ( value = "/provisionservice" )
public class ProvisionServiceController
{
    private static final Logger provisionServiceLog = LoggerFactory.getLogger( "provisionServiceLog" );

    @Autowired
    private IProvisionService iProvisionService;

    @Autowired
    private OrderDetailsRepository orderDetailsRepo;

    @PostMapping ( path = "/provisionservice" )
    public ResponseEntity<ProvisionResponse> provisionService( @RequestBody final ProvisionRequest provisionRequest )
    {
        provisionServiceLog.info( "Request: {} ", provisionRequest.toString() );

        final ProvisionResponse provisionResponse = iProvisionService.provisionService( provisionRequest );

        provisionServiceLog.info( "Response: {} ", provisionResponse.toString() );

        switch (provisionResponse.getStatusCode())
        {
        case "000":
            return ResponseEntity.ok( provisionResponse );
        case "010":
            return ResponseEntity.badRequest().body( provisionResponse );
        case "999":
            return ResponseEntity.status( HttpStatus.INTERNAL_SERVER_ERROR ).body( provisionResponse );
        default:
            return ResponseEntity.ok( provisionResponse );
        }
    }

    @RequestMapping ( value = "/getorderdetails", method = RequestMethod.GET )
    @ApiOperation (
            value = "getorderdetails", notes = "getorderdetails", response = String.class, responseContainer = "List"
    )
    @ApiResponses ( value =
    { @ApiResponse ( code = 200, message = "success" ), @ApiResponse (
            code = 400, message = "invalid parameters provided"
    ), @ApiResponse ( code = 404, message = "orderdetails not found" ), } )
    @Transactional ( readOnly = true )
    ResponseEntity<?> getOrderDetails()
    {
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();

        orderDetailsList = (List<OrderDetails>) orderDetailsRepo.findAll();

        return new ResponseEntity<>( orderDetailsList, HttpStatus.OK );
    }

    @RequestMapping ( value = "/getorderdetailsbysvcno", method = RequestMethod.GET )
    @ApiOperation (
            value = "getorderdetailsbysvcno", notes = "getorderdetailsbysvcno", response = String.class, responseContainer = "List"
    )
    @ApiResponses ( value =
    { @ApiResponse ( code = 200, message = "success" ), @ApiResponse (
            code = 400, message = "invalid parameters provided"
    ), @ApiResponse ( code = 404, message = "orderdetails not found" ), } )
    @Transactional ( readOnly = true )
    ResponseEntity<?> getOrderDetailsBySvcNo( @ApiParam ( value = "service Number of the order" ) @RequestParam (
        "svcNo"
    ) final String svcNo )
    {
        List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();

        orderDetailsList = (List<OrderDetails>) orderDetailsRepo.findBySvcNo( svcNo );

        return new ResponseEntity<>( orderDetailsList, HttpStatus.OK );
    }
}
