package com.example.easycounter

class Counter (var n : Int){

    fun set(m : Int) {
        if (m<0)
            this.n = 0
        else
            this.n = m
    }

    fun add(m : Int) {
        if (n+m < 0)
            this.n = 0
        else
            this.n = n+m
    }

    fun print() : String {
        return this.n.toString()
    }
}