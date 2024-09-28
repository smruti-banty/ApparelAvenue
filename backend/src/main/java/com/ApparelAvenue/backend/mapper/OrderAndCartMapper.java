package com.ApparelAvenue.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ApparelAvenue.backend.dto.CustomerResponseDto;
import com.ApparelAvenue.backend.dto.OrderAndCartRequestDto;
import com.ApparelAvenue.backend.dto.OrderAndCartResponseDto;
import com.ApparelAvenue.backend.dto.ProductResponseDto;
import com.ApparelAvenue.backend.model.OrderAndCart;

public class OrderAndCartMapper {

    public static OrderAndCartResponseDto convertToOrderAndCartResponseDto(OrderAndCart orderAndCart) {
        OrderAndCartResponseDto orderAndCartResponseDto = new OrderAndCartResponseDto();

        BeanUtils.copyProperties(orderAndCart, orderAndCartResponseDto);

        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        BeanUtils.copyProperties(orderAndCart.getCustomer(), customerResponseDto);

        List<ProductResponseDto> list = orderAndCart.getProducts().stream()
                .map(product -> ProductMapper.convertToProductResponseDto(product)).toList();

        orderAndCartResponseDto.setCustomerResponseDto(customerResponseDto);
        orderAndCartResponseDto.setProductResponseDtos(list);
        return orderAndCartResponseDto;
    }

    public static List<OrderAndCartResponseDto> convertToListOfOrderAndCartResponseDto(List<OrderAndCart> orders) {
        return orders.stream()
                .map(orderAndCart -> OrderAndCartMapper.convertToOrderAndCartResponseDto(orderAndCart)).toList();
    }

    public static OrderAndCart convertToOrderAndCart(OrderAndCartRequestDto dto) {
        var orderAndCart = new OrderAndCart();
        BeanUtils.copyProperties(dto, orderAndCart);
        return orderAndCart;
    }
}
