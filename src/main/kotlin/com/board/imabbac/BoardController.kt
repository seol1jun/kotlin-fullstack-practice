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
    //update를 사용할 수 있는 RestFull = PUT, FATCH
    //PUT = name, age, title이 있을 때 name만 쓰고 PUT하면 보내지 않은 나머지 값은 다 null로 바뀜.
    //넣은 name만 db에 저장. name만 바꾸고싶다해도 그대로 age랑 title을 써야됨.
    //PATCH = name, age, title이 있을 때 name만 써도 나머지 age, title 정보는 db에 보존.
    //즉 보통 거의 모든 수정은 PATCH 사용. PATCH는 요청에 포함된 부분만 변경해줌.
    @PatchMapping
    fun update(
        @RequestBody boardDto: BoardDto //update를 해야하기에 RequestBody를 통해서 boardDto를 받아옴
    ) {
        boardService.update(boardDto)//boardDto를 받아온 걸 service로 넘겨야함.
    }
    //crud 중 rud는 id값을 파라메터로 받아야 읽든지 수정하든지 삭제하든지 함.
    //patch는 body값을 통해서 확인할 수도 있기에 body값을 파라미터로 받음.
    //RestApi를 통해서 클라이언트와 통신하는 걸 포스트맨으로 지금까지는 구현
    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id : Long) {//PathVariable로 id를 가져옴
        boardService.deleteById(id)
    }
    //api 연동을 하기 위해선 api 문서가 있어야함.
    //api문서를 만들기 위해 swagger라는 라이브러리를 사용
    //스프링 컨트롤러에 미리 작성해둔 엔드포인트를 보고 스웨거가 api 형태로 제공

}