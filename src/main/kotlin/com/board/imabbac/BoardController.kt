package com.board.imabbac

import org.springframework.web.bind.annotation.*
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

    @GetMapping
    fun getAll(): List<BoardDto> { //컨트롤러 딴에서도 rest나 api 딴으로 올려줘야하기 때문에 return을 정의한다
        return boardService.getAll() //boardService를 호출하여 데이터를 가져옴
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable("id") id: Long): BoardDto { //개별값이라 List형식 안씀
        return boardService.getById(id) //@PathVariable을 통해 id값을 받아온 뒤 boardService를 호출하여 데이터를 가져옴
    }

}