package com.hkl.tobuy.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hkl.tobuy.R
import com.hkl.tobuy.dataabase.entity.ItemEntity
import com.hkl.tobuy.databinding.FragmentHomeBinding
import com.hkl.tobuy.databinding.FragmetnAddItemEntityBinding
import com.hkl.tobuy.ui.BaseFragment
import java.util.UUID

class AddItemEntityFragment : BaseFragment() {
    private var _binding : FragmetnAddItemEntityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmetnAddItemEntityBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            saveItemEntityToTheDataBase()
        }
    }
    private fun saveItemEntityToTheDataBase() {
        val itemTitle = binding.titleEditText.text.toString().trim()
        if(itemTitle.isEmpty()) {
            binding.titleTextField.error = "* Required field"
            return
        }
        binding.titleTextField.error = null

        val itemDescription = binding.descriptionEditText.text.toString().trim()
        val itemPriority = when(binding.radioGroup.checkedRadioButtonId ) {
            R.id.radioButtonLow -> 1
            R.id.radioButtonMedium -> 2
            R.id.radioButtonHigh -> 3
            else -> 0
        }
        val itemEntity = ItemEntity(
            id = UUID.randomUUID().toString(),
            title = itemTitle,
            description = itemDescription,
            priority = itemPriority,
            categoryId = "", // todo update this when the app have categories
            createdAt = System.currentTimeMillis()
        )
        sharedViewModel.addItem(itemEntity)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}