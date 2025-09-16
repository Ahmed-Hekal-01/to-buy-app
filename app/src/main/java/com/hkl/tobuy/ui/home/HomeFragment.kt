package com.hkl.tobuy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyTouchHelper
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
        // setup swipe-to-delete
        EpoxyTouchHelper.initSwiping(binding.homeRecyclerView)
            .right()
            .withTarget(HomeEpoxyController.ItemEntityModel::class.java)
            .andCallbacks(object : EpoxyTouchHelper.SwipeCallbacks<HomeEpoxyController.ItemEntityModel>(){
                override fun onSwipeCompleted(
                    model: HomeEpoxyController.ItemEntityModel?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    val itemThatWRemoved = model?.itemEntity ?: return
                    sharedViewModel.deleteItem(itemThatWRemoved)
                }

            })
    }

    override fun onResume() {
        super.onResume()
        mainActivity.hideKeyboard(requireView())
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBumpPriority(itemEntity: ItemEntity) {
        val currentPriority = itemEntity.priority
        var newPriority = currentPriority + 1
        if(newPriority > 3) {
            newPriority = 1
        }

        val updatedItemEntity = itemEntity.copy(priority = newPriority)
        sharedViewModel.updateItem(updatedItemEntity)
    }

    override fun onItemSelected(itemEntity: ItemEntity) {
        val navDirections = HomeFragmentDirections.actionHomeFragmentToAddItemEntityFragment(itemEntity.id)
        navController.navigate(navDirections)
    }
}