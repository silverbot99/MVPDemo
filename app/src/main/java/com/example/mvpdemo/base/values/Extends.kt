package com.example.mvpdemo.base.values

class Extends {
    companion object{
        fun String?.getValueOrDefault(defaultValue: String): String {
            return if (this.isNullOrEmpty()) defaultValue
            else this
        }
        fun String?.getValueOrDefaultNull():String{
            return if (this.isNullOrEmpty()) return "null"
            else this
        }
    }
}