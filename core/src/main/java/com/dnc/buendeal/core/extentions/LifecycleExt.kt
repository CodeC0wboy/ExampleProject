package com.dnc.buendeal.core.extentions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

fun <T> Fragment.subscribe(liveData: (LiveData<T>)?, onNext: (t: T) -> Unit) {
    liveData?.observe(viewLifecycleOwner, {
        if (it != null) {
            onNext(it)
        }
    })
}

fun <T> Fragment.subscribeNullable(liveData: LiveData<T>?, onNext: (t: T?) -> Unit) {
    liveData ?: return
    liveData.observe(viewLifecycleOwner, Observer { onNext(it) })
}

fun <T> AppCompatActivity.subscribe(liveData: (LiveData<T>)?, onNext: (t: T) -> Unit) {
    liveData?.observe(this, {
        if (it != null) {
            onNext(it)
        }
    })
}

fun <T> AppCompatActivity.subscribeNullable(liveData: LiveData<T>, onNext: (t: T?) -> Unit) {
    liveData.observe(this, { onNext(it) })
}

fun <T> Fragment.subscribe(flow: (Flow<T?>)?, onNext: (t: T) -> Unit) {
    flow ?: return
    viewLifecycleOwner.lifecycle.coroutineScope.launch {
        flow.collect {
            if (it != null) {
                onNext(it)
            }
        }
    }
}

fun <T> Fragment.subscribeNullable(flow: (Flow<T?>)?, onNext: (t: T?) -> Unit) {
    flow ?: return
    viewLifecycleOwner.lifecycle.coroutineScope.launch {
        flow.collect { onNext(it) }
    }
}

fun <T> AppCompatActivity.subscribe(flow: (Flow<T?>)?, onNext: (t: T) -> Unit) {
    flow ?: return
    lifecycle.coroutineScope.launch {
        flow.collect {
            if (it != null) {
                onNext(it)
            }
        }
    }
}

fun <T> AppCompatActivity.subscribeNullable(flow: (Flow<T?>)?, onNext: (t: T?) -> Unit) {
    flow ?: return
    lifecycle.coroutineScope.launch {
        flow.collect { onNext(it) }
    }
}

fun <T> DialogFragment.subscribe(liveData: (LiveData<T>)?, onNext: (t: T) -> Unit) {
    liveData?.observe(viewLifecycleOwner, {
        if (it != null) {
            onNext(it)
        }
    })
}

fun <T : AppCompatActivity> AppCompatActivity.startClearActivity(kClass: KClass<T>) {
    finish()
    startActivity(
        Intent(this, kClass.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    )
}

fun <T> MutableLiveData<T>.callAndNullify(message: T) {
    value = message
    value = null
}

fun FragmentActivity.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    globalToast(this, text, duration)

fun FragmentActivity.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) =
    globalToast(this, getString(resId), duration)

fun Fragment.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    requireActivity().showToast(text, duration)

fun Fragment.showToast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) =
    requireActivity().showToast(resId, duration)

private fun globalToast(context: Context, text: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(context, text, duration).show()

fun Fragment.requestPermissions(
    permissions: Array<String>,
    requestCode: Int,
    onGranted: () -> Unit
) {
    fun checkPermissions(permissions: Array<out String>) = permissions.all {
        ContextCompat.checkSelfPermission(
            this.requireContext(),
            it
        ) == PackageManager.PERMISSION_GRANTED
    }

    if (checkPermissions(permissions)) {
        onGranted()
    } else {
        this.requestPermissions(permissions, requestCode)
    }
}

fun <T1, T2, R> MediatorLiveData<R>.biMerge(
    first: LiveData<T1>,
    second: LiveData<T2>,
    updateValue: (T1?, T2?) -> R
) {
    addSource(first) { first.value?.also { value = updateValue.invoke(first.value, second.value) } }
    addSource(second) {
        second.value?.also {
            value = updateValue.invoke(first.value, second.value)
        }
    }
}

fun <T1, T2, T3, R> MediatorLiveData<R>.triMerge(
    first: LiveData<T1>,
    second: LiveData<T2>,
    third: LiveData<T3>,
    updateValue: (T1?, T2?, T3?) -> R
) {
    addSource(first) {
        first.value?.also {
            value = updateValue(first.value, second.value, third.value)
        }
    }
    addSource(second) {
        second.value?.also {
            value = updateValue.invoke(first.value, second.value, third.value)
        }
    }
    addSource(third) {
        third.value?.also {
            value = updateValue.invoke(first.value, second.value, third.value)
        }
    }
}

fun MutableLiveData<Unit>.post() = postValue(Unit)

fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: (T) -> Unit) {
    val viewLifecycleOwner = (owner as? Fragment)?.viewLifecycleOwner ?: owner
    observe(
        viewLifecycleOwner,
        object : Observer<T> {
            override fun onChanged(t: T) {
                if (t != null) observer(t)
                removeObserver(this)
            }
        }
    )
}

inline fun <T, R> LiveData<out T>.map(
    crossinline transform: (T) -> R
): LiveData<R> = Transformations.map(this) { transform(it) }

inline fun <T> LiveData<T>.mutable(): MutableLiveData<T> =
    (this as? MutableLiveData) ?: throw IllegalArgumentException("LiveData is not mutable")

inline fun <T> StateFlow<T>.mutable(): MutableStateFlow<T> =
    (this as? MutableStateFlow) ?: throw IllegalArgumentException("StateFlow is not mutable")
