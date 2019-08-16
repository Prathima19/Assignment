package com.example.pd;

import com.example.pd.request.ProvisionRequest;
import com.example.pd.response.ProvisionResponse;

public interface IProvisionService
{
    ProvisionResponse provisionService( final ProvisionRequest provisionRequest );
}
