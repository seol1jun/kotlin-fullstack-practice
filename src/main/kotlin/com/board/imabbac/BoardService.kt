package com.board.imabbac

import org.springframework.stereotype.Service

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
}