package com.tw.beach.entity;

interface RequestHandler {
    void handle(CustomerRequest request) throws InsufficientFundException;
}
