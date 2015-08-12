package com.tw.beach.entity.handlers;

import com.tw.beach.entity.requests.CustomerRequest;
import com.tw.beach.entity.requests.InsufficientFundException;

public interface RequestHandler {
    void handle(CustomerRequest request) throws InsufficientFundException;
}
