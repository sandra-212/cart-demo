package com.example.demo.service

import com.example.demo.dto.CartDto
import com.example.demo.dto.toCartDto
import com.example.demo.dto.toEntity
import com.example.demo.entity.Cart
import com.example.demo.dto.Item
import com.example.demo.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class CartService (private val repository: CartRepository) {
    fun addToCart(item: Item, userId: String):CartDto {
        val items = mutableListOf<Item>()
        items.add(item)
        val cart = Cart(userId=userId, items = items)
        val update= repository.save(cart)
        return toCartDto(update)
    }

    fun updateCart(item: Item, qty: Int, userId: String):CartDto {
        val cart:Cart  = repository.findByCustomerID(userId)
        val items = cart.getProducts()
        val cartItem = items. find { it.id == item.id}
        if (cartItem == null) {
          items.add(Item(item.id, item.price, qty))
        } else {
            val updatedQuantity = cartItem.quantity + qty
            val updatedPrice= cartItem.price * updatedQuantity
            cartItem?.apply {price = updatedPrice; quantity = updatedQuantity  }

        }

        val cartEntity:Cart = toEntity(Cart(cart.id,cart.userId, items))
        val update= repository.save(cartEntity)
        return toCartDto(update)
    }


     fun getCart (userId:String): CartDto {
        val cart:Cart = repository.findByCustomerID(userId)
        return toCartDto(cart)
    }

    fun removeProductFromCart(id: Long, userId: String): CartDto {
        val cart:Cart  = repository.findByCustomerID(userId)
        val items = cart.getProducts()
        val cartItem = items.find { it.id == id} ?: throw NoSuchElementException("Item Not Found!")
        items.remove(cartItem)
        val cartEntity:Cart = toEntity(Cart(cart.id,cart.userId, items))
        val update= repository.save(cartEntity)
        return toCartDto(update)
    }
}