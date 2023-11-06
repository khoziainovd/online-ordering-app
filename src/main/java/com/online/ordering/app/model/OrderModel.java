package com.online.ordering.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderModel {
    private Long id;
    private String product;
    private String status;
    private String destination;
    private Long requestId;
    private String userName;
}
