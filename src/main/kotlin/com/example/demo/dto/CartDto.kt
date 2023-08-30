package com.example.demo.dto

import com.example.demo.entity.Cart
import com.example.demo.dto.Item
import java.time.LocalDateTime

data class CartDto(
    val id: Long,
    val userId: String,
    val items: List<Item>,
    val createdOn: LocalDateTime,

    )


fun toCartDto(cart: Cart): CartDto {
    return CartDto(
        cart.id,
        cart.userId,
        cart.items,
        cart.createdOn,

    )
}


fun toEntity(cart: Cart)= Cart(
    cart.id,
    cart.userId,
    cart.items,
    cart.createdOn,

)