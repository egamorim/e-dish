package com.egamorim.edish.application.service;

import com.egamorim.edish.application.domain.MenuItem;
import com.egamorim.edish.application.domain.Order;
import com.egamorim.edish.application.domain.OrderMenuItem;
import com.egamorim.edish.application.repository.MenuItemRepository;
import com.egamorim.edish.application.repository.OrderRepository;
import com.egamorim.edish.infrastructure.messaging.OrderPublisher;
import com.egamorim.edish.infrastructure.messaging.schemas.OrderItem;
import com.egamorim.edish.infrastructure.messaging.schemas.OrderRequested;
import com.egamorim.edish.presentation.dto.OrderDTO;
import com.egamorim.edish.presentation.dto.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderPublisher publisher;
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderDTO order(OrderDTO orderDTO) {

        Order order = Order.builder()
                .id(UUID.randomUUID())
                .merchantId(UUID.fromString(orderDTO.getMerchantId()))
                .startedAt(LocalDateTime.now())
                .content(this.orderMenuItemsFromDTO(orderDTO.getItems()))
                .build();

        Order savedOrder = orderRepository.save(order);

        OrderRequested msg = new OrderRequested();
        msg.setId(savedOrder.getId().toString());
        msg.setMerchantId(orderDTO.getMerchantId());

        List<OrderItem> items = new ArrayList<>();
        orderDTO.getItems().forEach(i -> {
            OrderItem item = new OrderItem();
            item.setId(i.getId().toString());
            item.setAmount(i.getAmount());
            items.add(item);
        });
        msg.setItems(items);

        publisher.publishOrderEvent(msg);
        return null;
    }

    private List<OrderMenuItem> orderMenuItemsFromDTO(List<OrderItemDTO> dtoItems) {
        return dtoItems.stream()
                .map(dto -> {
                    MenuItem menuItem = this.menuItemRepository
                            .findById(dto.getId())
                            .get();
                    return OrderMenuItem.builder()
                            .amount(dto.getAmount())
                            .item(menuItem)
                            .build();
                })
                .collect(Collectors.toList());
    }

    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }
}
