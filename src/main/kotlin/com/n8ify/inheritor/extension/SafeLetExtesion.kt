package com.n8ify.inheritor.extension

/**
 * @Author : Wittawat.S
 *
 * */

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun <T1 : Any, T2 : Any, T3 : Any, R : Any> safeLet(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3) -> R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}

fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, R : Any> safeLet(p1: T1?, p2: T2?, p3: T3?, p4: T4?, block: (T1, T2, T3, T4) -> R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
}

fun <T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, R : Any> safeLet(p1: T1?, p2: T2?, p3: T3?, p4: T4?, p5: T5?, block: (T1, T2, T3, T4, T5) -> R?): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null && p5 != null) block(p1, p2, p3, p4, p5) else null
}

fun <A, B> T(tuple: Pair<A?, B?>): Pair<A, B>? =
        if (tuple.first == null || tuple.second == null) null
        else Pair(tuple.first!!, tuple.second!!)

fun <A, B, C> T(tuple: Triple<A?, B?, C?>): Triple<A, B, C>? =
        if (tuple.first == null || tuple.second == null || tuple.third == null) null
        else Triple(tuple.first!!, tuple.second!!, tuple.third!!)

fun <A, B> T(first: A?, second: B?): Pair<A, B>? =
        if (first == null || second == null) null
        else Pair(first, second)

fun <A, B, C> T(first: A?, second: B?, third: C?): Triple<A, B, C>? =
        if (first == null || second == null || third == null) null
        else Triple(first, second, third)
