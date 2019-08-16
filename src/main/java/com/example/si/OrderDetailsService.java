package com.example.si;

import static com.example.pd.util.Constants.CANNOT_BE_EMPTY;
import static com.example.pd.util.Constants.SERVICE_NUMBER;
import static com.example.pd.util.Constants.ORDER_ID;
import static com.example.pd.util.Constants.ID;
import static com.example.pd.util.Constants.STATUS;
import static com.example.pd.util.Constants.PROVISION_REQUEST;
import static com.example.pd.util.Constants.ORDER_DETAILS;
import static com.example.pd.util.ObjectUtils.isEmpty;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.pd.dm.OrderDetailsRepository;
import com.example.pd.enumeration.OrderStatus;
import com.example.pd.model.OrderDetails;
import com.example.pd.request.ProvisionRequest;

@Service
public class OrderDetailsService
{
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> findOrderByServiceNumber( final String serviceNumber )
    {
        Assert.hasLength( serviceNumber, String.format( CANNOT_BE_EMPTY, SERVICE_NUMBER ) );
        return orderDetailsRepository.findBySvcNo( serviceNumber );
    }

    public OrderDetails findByOrderId( final String orderId )
    {
        Assert.hasLength( orderId, String.format( CANNOT_BE_EMPTY, ORDER_ID ) );
        return orderDetailsRepository.findOne( UUID.fromString( orderId ) );
    }

    public List<OrderDetails> findOrderByStatusIn( final List<OrderStatus> status )
    {
        Assert.notEmpty( status, String.format( CANNOT_BE_EMPTY, STATUS ) );

        final List<OrderDetails> orders = orderDetailsRepository.findByStatusIn( status );
        if ( isEmpty( orders ) )
        {
            return Collections.emptyList();
        }
        return orders;
    }

    public OrderDetails initOrder( final ProvisionRequest request )
    {
        Assert.notNull( request, String.format( CANNOT_BE_EMPTY, PROVISION_REQUEST ) );
        OrderDetails order = new OrderDetails();
        BeanUtils.copyProperties( request, order );
        order.setId(UUID.randomUUID());
        order.setOrderId( "" );
        order.setStatus( OrderStatus.NEW );
        order.setCreationDate( LocalDateTime.now() );

        return orderDetailsRepository.save( order );
    }

    public OrderDetails updateOrderDetails( final OrderDetails orderDetails )
    {
        Assert.notNull( orderDetails, String.format( CANNOT_BE_EMPTY, ORDER_DETAILS ) );
        Assert.notNull( orderDetails.getId(), String.format( CANNOT_BE_EMPTY, ID ) );

        orderDetails.setCompletionDate( LocalDateTime.now() );

        return orderDetailsRepository.save( orderDetails );
    }

}
