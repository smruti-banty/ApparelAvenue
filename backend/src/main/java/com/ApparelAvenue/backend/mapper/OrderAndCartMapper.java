package com.ApparelAvenue.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.ApparelAvenue.backend.dto.OrderAndCartResponseDto;
import com.ApparelAvenue.backend.dto.ProductResponseDto;
import com.ApparelAvenue.backend.model.OrderAndCart;

public class OrderAndCartMapper {

    public static OrderAndCartResponseDto convertToOrderAndCartResponseDto(OrderAndCart orderAndCart) {
        OrderAndCartResponseDto orderAndCartResponseDto = new OrderAndCartResponseDto();
        BeanUtils.copyProperties(orderAndCart, orderAndCartResponseDto);
        List<ProductResponseDto> list = orderAndCart.getProducts().stream()
                .map(product -> ProductMapper.convertProductToProductResponseDto(product)).toList();
        orderAndCartResponseDto.setProductResponseDtos(list);
        return orderAndCartResponseDto;
    }
}
