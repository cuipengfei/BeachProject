package com.second.job.tw.handle;

import com.second.job.tw.OverdraftException;
import com.second.job.tw.request.CustomerRequest;

/**
 * Created by ppyao on 8/13/15.
 */
public interface CustomerHandler {
    public double handlers(CustomerRequest customerRequest) throws OverdraftException;
}
