package com.board.imabbac

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.*
import java.time.LocalTime

@Entity
class BoardEntity (

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var title: String,

    var content: String,

    var name: String,

    ) {
    fun update(boardDto: BoardDto) {
        this.title = boardDto.title
        this. content = boardDto.content
        this.name = boardDto.name
    }
}
//