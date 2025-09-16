package com.hkl.tobuy.ui

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import com.hkl.tobuy.arch.ToBuyViewModel
import com.hkl.tobuy.dataabase.AppDataBase

abstract class BaseFragment : Fragment() {

    protected val mainActivity: MainActivity
        get() = (activity as MainActivity)

    protected  val navController : NavController
        get() = (activity as MainActivity).navController
    protected val appDataBase: AppDataBase
        get() = (AppDataBase.getDataBase(requireActivity()))
    protected fun View.showKeyboard() {
        mainActivity.showKeyboard(this)
    }

    protected fun View.hideKeyboard() {
        mainActivity.hideKeyboard(this)
    }
    protected val sharedViewModel: ToBuyViewModel by activityViewModels()
}