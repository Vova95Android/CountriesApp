package com.example.appxml.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.appxml.di.appComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var bindingInternal: VB? = null
    protected val binding: VB
        get() = bindingInternal!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingInternal = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInternal = null
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB {
        val viewBindingClass = (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0] as Class<VB>
        val inflateMethod = viewBindingClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return inflateMethod.invoke(null, inflater, container, false) as VB
    }

    protected fun <T> Flow<T>.subscribe(block: (T) -> Unit) {
        lifecycleScope.launch {
            collectLatest {
                block.invoke(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeOnViewModel()
    }

    abstract fun subscribeOnViewModel()
}