package com.example.demo.repository

import com.example.demo.entity.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
@Repository
interface CartRepository : JpaRepository<Cart, Long> {
    @Query(value = "select * from cart where cart.user_id = :customerId", nativeQuery = true)
    fun findByCustomerID(customerId:String): Cart

}