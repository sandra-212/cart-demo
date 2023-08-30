package com.example.demo.entity

import jakarta.persistence.*
import com.example.demo.dto.Item
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.time.LocalDateTime



@Entity
@Table(name = "cart")
data class Cart (

    @Id
    @GeneratedValue(generator = "cart_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "cart_sequence", sequenceName = "task_sequence", allocationSize = 1)
    val id: Long = 0,


    @Column(name = "user_id",nullable = false)
    val userId: String = "",

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "items", nullable = false)
    var items: MutableList<Item> = mutableListOf<Item>(),

    @Column(name = "created_on", nullable = false)
    val createdOn: LocalDateTime = LocalDateTime.now(),

    ) {
    fun getProducts(): MutableList<Item> {
        return items
    }
}