package org.example.marketaja

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.viewmodel.CreationExtras
import kotlin.reflect.KClass

@Composable
inline fun<reified VM: ViewModel> viewModel(crossinline factory: () -> VM) : Lazy<VM> {
    val viewModel = ViewModelStore()

    val vm = remember {
       lazy {
           ViewModelProvider.create(
               viewModel,
               factory = object : ViewModelProvider.Factory {
                   override fun <T : ViewModel> create(
                       modelClass: KClass<T>,
                       extras: CreationExtras
                   ): T {
                       return factory.invoke() as T
                   }
               }
           )[VM::class]
       }
    }

    LifecycleStartEffect(Unit) {
        onStopOrDispose {
            if (vm.isInitialized()){
                viewModel.clear()
            }
        }
    }

    return vm
}