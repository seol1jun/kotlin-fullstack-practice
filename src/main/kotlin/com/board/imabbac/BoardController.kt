package com.board.imabbac

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController //RestApi 통신을 가능하게 해주는 어노테이션
@RequestMapping("/board") // 얘의 주소로 들어와야 아래가 실행됨.
class BoardController (
    private val boardService: BoardService
){

    @PostMapping //post 요청이 오면 create 실행
    fun create(
        @RequestBody boardDto: BoardDto
    ) {
        boardService.create(boardDto)
    }
}