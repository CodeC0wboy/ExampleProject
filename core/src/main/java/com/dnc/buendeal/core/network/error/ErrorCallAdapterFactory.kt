package com.dnc.buendeal.network.error

import com.dnc.buendeal.core.network.error.ErrorCallAdapter
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

// https://www.ackee.cz/blog/en/retrofit-exceptions-with-retrofit/
class ErrorCallAdapterFactory(private val gson: Gson) : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, Call<*>>? {
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType || returnType.actualTypeArguments.size != 1) {
            return null
        }

        val delegate = retrofit.nextCallAdapter(this, returnType, annotations)
        @Suppress("UNCHECKED_CAST")
        return ErrorCallAdapter(
            delegateAdapter = delegate as CallAdapter<Any, Call<*>>,
            gson = gson
        )
    }
}
