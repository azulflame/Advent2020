package me.toddbensmiller.advent

import kotlin.system.measureNanoTime

/*
 * Created by Todd on 12/1/2020.
 */

fun day2(): Pair<Pair<String, Long>, Pair<String, Long>> {
    val p1a: Int
    val p2a: Int
    val p1t = measureNanoTime { p1a = day2part1(ListHolder.day2) }
    val p2t = measureNanoTime { p2a = day2part2(ListHolder.day2) }
    return Pair(Pair(p1a.toString(), p1t), Pair(p2a.toString(), p2t))
}

fun day2part1(list: List<String>): Int {
    var count = 0
    list.forEach { x ->
        val split = x.replace(':', ' ').replace("  ", " ").replace('-', ' ').split(" ")
        val low = split[0].toInt()
        val high = split[1].toInt()
        val c = split[2][0]
        val pass = split[3]
        val charCount = pass.count { ch -> ch == c }
        if (charCount in low..high) {
            count++
        }
    }
    return count
}

fun day2part2(list: List<String>): Int {
    var count = 0
    list.forEach { x ->
        val split = x.replace(':', ' ').replace('-', ' ').replace("  ", " ").split(" ")
        val low = split[0].toInt() - 1
        val high = split[1].toInt() - 1
        val c = split[2][0]
        val pass = split[3]
        if ((pass[low] == c).xor(pass[high] == c)) {
            count++
        }
    }
    return count
}