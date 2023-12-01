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

    @Test
    public void testCreateOrder() {
        OrderModel order = new OrderModel();
        order.setId(1L);

        Mockito.when(orderRepository.save(order)).thenReturn(order);

        OrderModel result = orderService.createOrder(order);

        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;
        OrderModel existingOrder = new OrderModel();
        existingOrder.setId(orderId);

        OrderModel updatedOrder = new OrderModel();
        updatedOrder.setId(orderId);
        updatedOrder.setProduct("Updated");

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        Mockito.when(orderRepository.save(updatedOrder)).thenReturn(updatedOrder);

        OrderModel result = orderService.updateOrder(orderId, updatedOrder);

        Assertions.assertEquals("Updated", result.getProduct());
    }

    @Test
    public void testCancelOrderSuccess() {
        Long orderId = 1L;
        OrderModel existingOrder = new OrderModel();
        existingOrder.setId(orderId);

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        Mockito.doNothing().when(orderRepository).deleteById(orderId);

        boolean result = orderService.cancelOrder(orderId);

        Assertions.assertTrue(result);
    }

    @Test
    public void testCancelOrderNotFound() {
        Long orderId = 1L;

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        boolean result = orderService.cancelOrder(orderId);

        Assertions.assertFalse(result);
    }
}
