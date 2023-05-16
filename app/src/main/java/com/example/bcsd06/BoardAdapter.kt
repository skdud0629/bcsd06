package com.example.bcsd06

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView



data class BoardItem(var txt:String)

class BoardAdapter(val itemList: ArrayList<BoardItem>) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val Text = itemView.findViewById<TextView>(R.id.Text)

        init {
            val position = adapterPosition

            itemView.setOnClickListener{
                val builder = AlertDialog.Builder(itemView.context)
                builder.setTitle("이름 목록 삭제")
                    .setMessage("삭제하시겠습니까?")
                    .setPositiveButton("삭제", DialogInterface.OnClickListener {
                            dialog,_-> itemList.removeAt(position)
                        notifyItemRemoved(position)//리사이클러뷰에 변경 내용을 알리는 업데이트를 수행
                        notifyItemRangeChanged(position, itemList.size)
                    })
                    .setNegativeButton("ㄴ", DialogInterface.OnClickListener {
                            dialog,_-> dialog.dismiss()
                    })

                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()

                // Toast.makeText(itemView.context,itemList[position].txt, Toast.LENGTH_SHORT).show()
            }
            itemView.setOnLongClickListener(){
                val builder = AlertDialog.Builder(itemView.context)
                val editBuilder = AlertDialog.Builder(itemView.context)
                val editText = EditText(itemView.context)
                builder.setTitle("\n")
                    .setView(editText)
                    .setPositiveButton("확인", DialogInterface.OnClickListener { dialog, _ ->
                        val newText = editText.text.toString()
                        itemList[position].txt = newText
                        notifyItemChanged(position)
                    })
                    .setNegativeButton("취소", null)
                val EditDialog: AlertDialog = builder.create()
                EditDialog.show()
                true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.Text.text = itemList[position].txt
    }

    private lateinit var itemClickListener : AdapterView.OnItemClickListener

    override fun getItemCount(): Int {
        return itemList.count()
    }
}