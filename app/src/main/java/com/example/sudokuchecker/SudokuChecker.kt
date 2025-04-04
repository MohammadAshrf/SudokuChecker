package com.example.sudokuchecker

import kotlin.math.sqrt

class SudokuChecker {
    fun isValidSudoku(board: List<List<Char>>): Boolean {
        val size = board.size
        if (board.any { it.size != size }) return false

        val allowed = generateAllowedValues(size)

        if (!areRowsValid(board, allowed)) return false
        if (!areColumnsValid(board, allowed)) return false
        if (!areBoxesValid(board, allowed)) return false

        return true
    }

    private fun generateAllowedValues(size: Int): Set<Char> {
        val symbols = mutableSetOf<Char>()
        for (i in 1..size) {
            val symbol = if (i <= 9) '0' + i else 'A' + (i - 10)
            symbols.add(symbol)
        }
        return symbols
    }

    private fun areRowsValid(board: List<List<Char>>, allowed: Set<Char>): Boolean {
        for (row in board) {
            val seen = mutableSetOf<Char>()
            for (c in row) {
                if (c == '-') continue
                if (c !in allowed || c in seen) return false
                seen.add(c)
            }
        }
        return true
    }

    private fun areColumnsValid(board: List<List<Char>>, allowed: Set<Char>): Boolean {
        val size = board.size
        for (col in 0 until size) {
            val seen = mutableSetOf<Char>()
            for (row in 0 until size) {
                val c = board[row][col]
                if (c == '-') continue
                if (c !in allowed || c in seen) return false
                seen.add(c)
            }
        }
        return true
    }

    private fun areBoxesValid(board: List<List<Char>>, allowed: Set<Char>): Boolean {
        val size = board.size
        val boxSize = sqrt(size.toDouble()).toInt()
        for (boxRow in 0 until size step boxSize) {
            for (boxCol in 0 until size step boxSize) {
                val seen = mutableSetOf<Char>()
                for (i in 0 until boxSize) {
                    for (j in 0 until boxSize) {
                        val c = board[boxRow + i][boxCol + j]
                        if (c == '-') continue
                        if (c !in allowed || c in seen) return false
                        seen.add(c)
                    }
                }
            }
        }
        return true
    }
}