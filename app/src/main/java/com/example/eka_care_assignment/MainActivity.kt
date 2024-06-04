package com.example.eka_care_assignment

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eka_care_assignment.adapter.UserListAdapter
import com.example.eka_care_assignment.model.User
import com.example.eka_care_assignment.viewmodel.UserViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var etDob: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        etDob = findViewById(R.id.etDob)

        val adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        userViewModel.allUsers.observe(this, androidx.lifecycle.Observer { users ->
            users?.let { adapter.setUsers(it) }
        })

        val btnSave = findViewById<Button>(R.id.btnSave)
        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etAddress = findViewById<EditText>(R.id.etAddress)

        etDob.setOnClickListener {
            showDatePickerDialog()
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val dob = etDob.text.toString()
            val address = etAddress.text.toString()

            val user = User(0, name, age, dob, address)
            userViewModel.insert(user)
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            etDob.setText("$dayOfMonth/${month + 1}/$year")
        }, year, month, day)

        datePickerDialog.show()
    }
}