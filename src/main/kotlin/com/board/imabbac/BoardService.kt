package com.board.imabbac

import org.springframework.stereotype.Service

// 엔티티는 직접적으로 컨트롤러로 넘겨서 프론트와 상호작용을 하면 안됨.
// repository에서 조회된 데이터는 엔티티로 반환이 되는데 그 엔티티를 Dto로 바꿔줘야함.

@Service
class BoardService (
    private val boardRepository: BoardRepository
) {
    fun create(
        boardDto: BoardDto
    ) {
        val boardEntity = BoardEntity(
            title = boardDto.title,
            content = boardDto.content,
            name = boardDto.name
        )
        boardRepository.save(boardEntity)
    }

    //전체 조회
    fun getAll(): List<BoardDto> {//리턴같은 경우 List형태의 BoardDto를 반환
        val boardEntities = boardRepository.findAll() //엔티티가 리스트 형식으로 담김.
        val boardDtoList = boardEntities.map { //map은 for문 같은 느낌. 엔티티들 다 돌려서 dto로 만듦
            board -> BoardDto (
                id = board.id,
                title = board.title,
                content = board.content,
                name = board.name
            )
        }
        return boardDtoList //컨트롤러에 넘겨줌
    }

    //개별 조회
    fun getById(id : Long): BoardDto{ //컨트롤러가 boardDto를 받아야하므로 반환값을 여기다도 써줌
        // 파라메터가 id로 받아오기에 findById도 id값으로 찾음
        val boardEntity = boardRepository.findById(id).get() //boardRepository에서 BoardEntity로 데이터를 가져옴.
        //엔티티를 Dto로 변환해줘야함.
        //생성자를 통해서 값을 하나씩 가져옴
        val boardDto = BoardDto (
            id = boardEntity.id,
            title = boardEntity.title,
            content = boardEntity.content,
            name = boardEntity.name
        )
        return boardDto
    }

    fun update(boardDto: BoardDto) { //id로 엔티티를 불러오고 엔티티 값을 직접 수정하는 로직, boardDto를 받아옴.
        val id: Long = boardDto.id!! //id가 null일 수도 있다고 선언했는데, !!를 붙히면 절대 null일 수 없다는 뜻.
        val boardEntity = boardRepository.findById(id).get() //얘를 통해서 엔티티를 받아옴
        //받은 boardEntity를 수정해야함
        boardEntity.update(boardDto) //boardEntity는 자동으로 업데이트가 됨.
        //save로직은 JPA에서 영속성 컨텍스트상으로 관리를 하기 때문에 update한 시점에 save가 자동적으로 됨.
        //지금은 가독성을 위해 아래의 save 코드 작성.
        boardRepository.save(boardEntity) //수정된 값을 저장함.
    }

    fun deleteById(id : Long) {
        boardRepository.deleteById(id)
    }
}