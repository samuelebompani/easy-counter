package com.example.easycounter

class Counter (var n : Int){

    fun add() {
        this.n++
    }

    fun sub() {
        this.n--
    }

    fun reset() {
        this.n=0
    }

    fun print() : String {
        return this.n.toString()
    }
}