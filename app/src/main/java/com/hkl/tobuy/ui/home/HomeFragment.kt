package com.hkl.tobuy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hkl.tobuy.R
import com.hkl.tobuy.dataabase.entity.ItemEntity
import com.hkl.tobuy.databinding.FragmentHomeBinding
import com.hkl.tobuy.ui.BaseFragment

class HomeFragment : BaseFragment() , ItemEntityInterface {
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            navController.navigate( R.id.action_homeFragment_to_addItemEntityFragment)
        }

        val controller = HomeEpoxyController(this)
        binding.homeRecyclerView.setController(controller)
        sharedViewModel.itemListLiveData.observe(viewLifecycleOwner){ itemEntities ->
            controller.itemEntityList = itemEntities as ArrayList<ItemEntity>
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBumpPriority(itemEntity: ItemEntity) {
        // todo
    }

    override fun onItemSelected(itemEntity: ItemEntity) {
        //todo
    }
}