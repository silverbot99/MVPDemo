package com.example.mvpdemo.base.config

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class Constant {
    companion object{
        val BASE_URL = "https://covid-193.p.rapidapi.com/"
        fun stringWithPlusToInt(newCasesString: String): Int{ // THis function will get a string from newcase like "+1", "null" then to 1 or 0
            var result: Int? = 0
            if (newCasesString.startsWith("+")){
                result = newCasesString.substring(1, newCasesString.length).toIntOrNull()
            }
            return result ?: 0
        }
        fun String?.getValueOrDefault(defaultValue: String): String {
            return if (this.isNullOrEmpty()) defaultValue
            else this
        }
        fun String?.getValueOrDefaultNull():String{
            return if (this.isNullOrEmpty()) return "null"
            else this
        }
        fun Int?.getValueOrDefaultZero():Int{
            return this ?: return 0
        }

        fun String.formatNumber():String{
            val nf: NumberFormat = NumberFormat.getInstance(Locale.FRENCH)
            return nf.parse(this).toString()

        }
        fun doubleToStringNoDecimal(d: Double): String? {
            val formatter: DecimalFormat =
                NumberFormat.getInstance(Locale.US) as DecimalFormat
            formatter.applyPattern("#,###")
            return formatter.format(d)
        }

    }
}