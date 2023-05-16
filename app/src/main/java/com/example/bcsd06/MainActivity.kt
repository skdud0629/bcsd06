package com.example.bcsd06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recycle=findViewById<RecyclerView>(R.id.recycle)
        val plusBtn: Button =findViewById(R.id.plusBtn)

        val editText = findViewById<View>(R.id.Edit) as EditText


        val itemList=ArrayList<BoardItem>()

        val boardAdapter = BoardAdapter(itemList)
        plusBtn.setOnClickListener{
            val str=editText.text.toString()
            if(str.isEmpty())
                Toast.makeText(this,"입력된 값이 없습니다.",Toast.LENGTH_SHORT).show()
            else{
                itemList.add(BoardItem(str))
            }


            boardAdapter.notifyDataSetChanged()
        }//  boardAdapter.notifyDataSetChanged()


        recycle.adapter=boardAdapter
        recycle.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)




    }
}