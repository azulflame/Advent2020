package me.toddbensmiller.advent

import kotlin.system.measureNanoTime

/*
 * Created by Todd on 12/3/2020.
 */
fun day3(): Pair<Long, Long>
{
    return Pair(day3part1(ListHolder.day3),day3part2(ListHolder.day3))
}

fun day3part1(list: List<String>): Long {
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
    return trees.toLong()
}

fun day3part2(list: List<String>): Long {
    val slopes: Array<Pair<Int, Int>> by lazy { arrayOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))}
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