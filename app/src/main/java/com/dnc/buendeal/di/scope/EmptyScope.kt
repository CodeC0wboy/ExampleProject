package com.dnc.buendeal.di.scope

import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named

val EMPTY_SCOPE_NAME = named("EmptyScope")

fun emptyScope(scopeId: String) =
    (object : KoinComponent {}).getKoin().createScope(scopeId, EMPTY_SCOPE_NAME)
