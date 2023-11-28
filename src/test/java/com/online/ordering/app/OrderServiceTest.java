package com.online.ordering.app;

import com.online.ordering.app.model.OrderModel;
import com.online.ordering.app.repository.OrderRepository;
import com.online.ordering.app.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() {
        List<OrderModel> orders = new ArrayList<>();
        orders.add(new OrderModel());
        orders.add(new OrderModel());

        Mockito.when(orderRepository.findAll()).thenReturn(orders);

        List<OrderModel> result = orderService.getAllOrders();

        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void testGetOrderById() {
        OrderModel order = new OrderModel();
        order.setId(1L);

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<OrderModel> result = orderService.getOrderById(1L);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(1L, result.get().getId());
    }
}
