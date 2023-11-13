package com.online.ordering.app.service;

import com.online.ordering.app.model.OrderModel;
import com.online.ordering.app.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    public List<OrderModel> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<OrderModel> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public OrderModel createOrder(OrderModel order) {
        // Потом при надобности добавлю логику при создании если нужно
        return orderRepository.save(order);
    }

    public OrderModel updateOrder(Long orderId, OrderModel updatedOrder) {
        Optional<OrderModel> existingOrderOptional = orderRepository.findById(orderId);

        if (existingOrderOptional.isPresent()) {
            OrderModel existingOrder = existingOrderOptional.get();

            existingOrder = updatedOrder;

            return orderRepository.save(existingOrder);
        } else {
            // Обработка, если заказ не найден
            return null;
        }
    }

    public boolean cancelOrder(Long orderId) {
        Optional<OrderModel> existingOrderOptional = orderRepository.findById(orderId);

        if (existingOrderOptional.isPresent()) {
            orderRepository.deleteById(orderId);
            //Сделал удаление, если нужно просто статус менять чтобы он не отображался то сделаю так.
            return true; // Успешно отменено
        } else {
            return false; // Заказ не найден
        }
    }
}
