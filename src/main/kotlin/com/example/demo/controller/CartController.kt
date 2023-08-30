package com.example.demo.controller

import com.example.demo.dto.*
import com.example.demo.service.CartService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class CartController(private val service: CartService) {
    data class createCartData(val item: Item, val userId: String)
    data class cartData(val item: Item, val qty:Int)

    @GetMapping("cart/{userId}")
    fun getCartItems(@PathVariable("userId") userId: String
    ): ResponseEntity<CartDto> = ResponseEntity(service.getCart(userId), HttpStatus.OK)
    
    @PostMapping("add")
    fun addToCart(
        @RequestBody createCartData: createCartData
    ): ResponseEntity<CartDto> = ResponseEntity(service.addToCart(createCartData.item, createCartData.userId), HttpStatus.OK)

    @PutMapping("update/{userId}")
    fun updateCart(
        @PathVariable ("userId") userId: String,
        @RequestBody cartData: cartData
    ): ResponseEntity<CartDto> = ResponseEntity(service.updateCart(cartData.item, cartData.qty, userId), HttpStatus.OK)

    @DeleteMapping("delete/{userId}/{id}")
    fun  removeProduct(
        @PathVariable ("userId") userId: String,
        @PathVariable id: Long
    ): ResponseEntity<CartDto> =
        ResponseEntity(service.removeProductFromCart(id,userId), HttpStatus.OK)
}