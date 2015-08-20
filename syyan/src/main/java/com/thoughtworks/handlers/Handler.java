package com.thoughtworks.handlers;


import com.thoughtworks.requests.CustomerRequest;

public interface Handler {
    double handle(CustomerRequest customerRequest);
}
