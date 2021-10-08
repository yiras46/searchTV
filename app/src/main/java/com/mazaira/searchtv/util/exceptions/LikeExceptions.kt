package com.mazaira.searchtv.util.exceptions

class LikeExceptions(var typeError:TypeError):Exception()

enum class TypeError {
    GET,
    INSERT,
    UPDATE,
    DELETE,
    API
}