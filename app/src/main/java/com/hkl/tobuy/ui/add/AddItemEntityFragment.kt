package com.hkl.tobuy.ui.add

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.hkl.tobuy.R
import com.hkl.tobuy.dataabase.entity.ItemEntity
import com.hkl.tobuy.databinding.FragmentHomeBinding
import com.hkl.tobuy.databinding.FragmetnAddItemEntityBinding
import com.hkl.tobuy.ui.BaseFragment
import java.util.UUID

class AddItemEntityFragment : BaseFragment() {
    private var _binding: FragmetnAddItemEntityBinding? = null
    private val binding get() = _binding!!
    private val safeArgs : AddItemEntityFragmentArgs by navArgs()
    private val selectedItem : ItemEntity? by lazy {
        sharedViewModel.itemListLiveData.value.find {
            it.id == safeArgs.selectedItemId
        }
    }
    private var inEditMode : Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmetnAddItemEntityBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveButton.setOnClickListener {
            saveItemEntityToTheDataBase()
        }
        sharedViewModel.transactionLiveData.observe(viewLifecycleOwner) { complete ->
            if (complete) {
                if(inEditMode) {
                    navController.navigateUp()
                    return@observe
                }
                Toast.makeText(requireActivity(), "Item Saved", Toast.LENGTH_SHORT).show()
                binding.titleEditText.text = null
                binding.titleEditText.requestFocus()
                binding.titleEditText.showKeyboard()
                binding.descriptionEditText.text = null
                binding.radioGroup.check(R.id.radioButtonLow)
            }
        }
        binding.titleTextField.showKeyboard()
        binding.titleEditText.requestFocus()

        // setup screen if in edit mode
        selectedItem?.let { item ->
            inEditMode = true
            binding.titleEditText.setText(item.title)
            binding.titleEditText.setSelection(item.title.length)
            binding.descriptionEditText.setText(item.description)
            when(item.priority) {
                1 -> binding.radioGroup.check(R.id.radioButtonLow)
                2 -> binding.radioGroup.check(R.id.radioButtonMedium)
                3 -> binding.radioGroup.check(R.id.radioButtonHigh)
            }
            binding.saveButton.text = "Update"
        }
    }

    private fun saveItemEntityToTheDataBase() {
        val itemTitle = binding.titleEditText.text.toString().trim()
        if (itemTitle.isEmpty()) {
            binding.titleTextField.error = "* Required field"
            return
        }
        binding.titleTextField.error = null

        val itemDescription = binding.descriptionEditText.text.toString().trim()
        val itemPriority = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButtonLow -> 1
            R.id.radioButtonMedium -> 2
            R.id.radioButtonHigh -> 3
            else -> 0
        }
        if(inEditMode) {
             val item = selectedItem!!.copy(
                 title = itemTitle,
                 description = itemDescription,
                 priority = itemPriority
             )
            sharedViewModel.updateItem(item)
            return
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

    override fun onPause() {
        super.onPause()
        sharedViewModel.transactionLiveData.postValue(false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}