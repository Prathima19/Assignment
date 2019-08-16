package com.example.pd.dm;

import java.util.List;

import com.example.pd.enumeration.OrderStatus;
import com.example.pd.model.OrderDetails;

public interface OrderDetailsRepository extends AbstractIdentifiableRepository<OrderDetails>
{
    List<OrderDetails> findBySvcNo( final String svcNo );

    List<OrderDetails> findByStatusIn( final List<OrderStatus> orderStatus );
}
