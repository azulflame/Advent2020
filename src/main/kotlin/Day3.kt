package me.toddbensmiller.advent

import kotlin.system.measureNanoTime

/*
 * Created by Todd on 12/3/2020.
 */
fun day3(): Pair<Pair<String, Long>, Pair<String, Long>> {
    val p1a: Int
    val p2a: Long
    val p1t = measureNanoTime { p1a = day3part1(ListHolder.day3) }
    val p2t = measureNanoTime { p2a = day3part2(ListHolder.day3) }
    return Pair(Pair(p1a.toString(), p1t), Pair(p2a.toString(), p2t))
}

fun day3part1(list: List<String>): Int {
    val width = list[0].length
    var x = 0
    var y = 0
    var trees = 0
    while (x < list.size) {
        if (list[x][y] == '#') {
            trees++
        }
        x++
        y = (y + 3) % width
    }
    return trees
}

fun day3part2(list: List<String>): Long {
    val slopes: Array<Pair<Int, Int>> = arrayOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    var total = 1L
    val width = list[0].length
    slopes.forEach { slope ->
        var x = 0
        var y = 0
        var trees = 0
        while (x < list.size) {
            if (list[x][y] == '#') {
                trees++
            }
            x += slope.second
            y = (y + slope.first) % width
        }
        total *= trees
    }
    return total
}