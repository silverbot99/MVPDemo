package com.example.mvpdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpdemo.screen.main.model.AttributeModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    //    private val tvMessage = this.findViewById<TextView>(R.id.tvMessage)
//    private val tvStatus = this.findViewById<TextView>(R.id.tvStatus)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val attributeTest = AttributeModel(phone = "0982914562",name = "Ngan")
        tvStatus.text = checkOnAttributeNotNull(attributeTest)

    }
    private fun checkOnAttributeNotNull(attributeTest: AttributeModel): String {
        val gson = Gson()
        val json = gson.toJson(attributeTest)
        tvMessage.text = json.toString()
        val number = json.toString().count { it.toString().contains(",") }+1
//        if (json.toString() == "{}"|| json.toString().contains(",")){
//            return false
//        }
        return number.toString()
    }
}