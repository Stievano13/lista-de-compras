package com.example.listadecompras

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel: ItemsViewModel by viewModels {
        ItemsViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        val addButton: Button = findViewById(R.id.button)
        val editText: EditText = findViewById(R.id.editText)

        addButton.setOnClickListener {
            if (editText.text.isEmpty()) {
                editText.error = "Penchant um valor"
                return@setOnClickListener
            }
            val newItem = ItemModel(
                id = 0.toString(),
                name = editText.text.toString(),
                onRemove = { item -> itemsAdapter.removeItem(item) }
            )
            itemsAdapter.addItem(newItem)
            editText.text.clear()
        }
    }
}

