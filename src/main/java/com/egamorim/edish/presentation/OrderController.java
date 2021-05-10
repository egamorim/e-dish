package com.egamorim.edish.presentation;

import com.egamorim.edish.application.repository.MenuItemRepository;
import com.egamorim.edish.application.service.OrderService;
import com.egamorim.edish.presentation.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final MenuItemRepository menuITemRepo;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity order(@RequestBody OrderDTO request) {

        System.out.println(request.toString());
        this.orderService.order(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
