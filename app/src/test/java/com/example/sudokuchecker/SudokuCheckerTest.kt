package com.example.sudokuchecker

import org.junit.Assert.*
import org.junit.Test

class SudokuCheckerTest {

    val checker = SudokuChecker()

    @Test
    fun `when board is completely valid then return true`() {
        val board = listOf(
            listOf('5', '3', '-', '-', '7', '-', '-', '-', '-'),
            listOf('6', '-', '-', '1', '9', '5', '-', '-', '-'),
            listOf('-', '9', '8', '-', '-', '-', '-', '6', '-'),
            listOf('8', '-', '-', '-', '6', '-', '-', '-', '3'),
            listOf('4', '-', '-', '8', '-', '3', '-', '-', '1'),
            listOf('7', '-', '-', '-', '2', '-', '-', '-', '6'),
            listOf('-', '6', '-', '-', '-', '-', '2', '8', '-'),
            listOf('-', '-', '-', '4', '1', '9', '-', '-', '5'),
            listOf('-', '-', '-', '-', '8', '-', '-', '7', '9')
        )
        assertTrue(checker.isValidSudoku(board))
    }

    @Test
    fun `when board is completely empty then return true`() {
        val board = List(9) { List(9) { '-' } }
        assertTrue(checker.isValidSudoku(board))
    }

    @Test
    fun `when board is partially filled and still valid then return true`() {
        val board = List(9) { MutableList(9) { '-' } }
        board[0][0] = '1'
        board[1][1] = '2'
        board[2][2] = '3'
        assertTrue(checker.isValidSudoku(board))
    }

    @Test
    fun `when board has duplicate in row then return false`() {
        val board = List(9) { MutableList(9) { '-' } }
        board[0][0] = '5'
        board[0][3] = '5'
        assertFalse(checker.isValidSudoku(board))
    }

    @Test
    fun `when board has duplicate in column then return false`() {
        val board = List(9) { MutableList(9) { '-' } }
        board[0][1] = '6'
        board[5][1] = '6'
        assertFalse(checker.isValidSudoku(board))
    }

    @Test
    fun `when board has duplicate in 3x3 box then return false`() {
        val board = List(9) { MutableList(9) { '-' } }
        board[0][0] = '9'
        board[1][1] = '9'
        assertFalse(checker.isValidSudoku(board))
    }

    @Test
    fun `when board contains invalid character then return false`() {
        val board = List(9) { MutableList(9) { '-' } }
        board[2][2] = '@'
        assertFalse(checker.isValidSudoku(board))
    }

    @Test
    fun `when board contains number outside allowed range then return false`() {
        val board = List(9) { MutableList(9) { '-' } }
        board[3][3] = '0'
        assertFalse(checker.isValidSudoku(board))
    }
}