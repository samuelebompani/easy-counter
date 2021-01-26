package com.example.easycounter

import java.lang.IllegalArgumentException

class Counter (var n : Int){

    fun set(m : Int) {
        if (m < 0 || m >= 10000000) //i don't want big numbers in the
            throw IllegalArgumentException()
        this.n = m
    }

    fun add(m : Int) {
        if (n+m < 0 || n+m >= 10000000)
            throw IllegalArgumentException()
        this.n = n+m
    }

    fun print() : String {
        return this.n.toString()
    }
}