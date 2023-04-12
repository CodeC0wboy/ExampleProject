package com.dnc.buendeal.core.core.data.response

import com.dnc.buendeal.core.core.domain.model.User
import com.dnc.buendeal.core.core.domain.model.domain

class Settings(
    val theme: Theme,
    val user: User
)

fun SettingsResponse.domain() = Settings(
    theme = theme,
    user = user.domain()
)
