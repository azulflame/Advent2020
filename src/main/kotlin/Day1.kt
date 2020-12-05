package me.toddbensmiller.advent

import kotlin.system.measureNanoTime

/*
 * Created by Todd on 12/1/2020.
 */

fun day1(): Pair<Pair<String, Long>, Pair<String, Long>> {
    val p1a: Int
    val p2a: Int
    val p1t = measureNanoTime { p1a = day1part1(ListHolder.day1.map { x -> Integer.parseInt(x) }.sorted()) }
    val p2t = measureNanoTime { p2a = day1part2(ListHolder.day1.map { x -> Integer.parseInt(x) }.sorted()) }
    return Pair(Pair(p1a.toString(), p1t), Pair(p2a.toString(), p2t))
}

fun day1part1(list: List<Int>): Int {
    for (x in list.indices) {
        for (y in x until list.size) {
            if (list[x] + list[y] == 2020) {
                return list[x] * list[y]
            } else if (list[x] + list[y] > 2020) {
                break
            }
        }
    }
    return 0
}

fun day1part2(list: List<Int>): Int {
    for (x in list.indices) {
        for (y in x until list.size) {
            if (list[x] + list[y] >= 2020) {
                break
            }
            for (z in y until list.size) {
                if (list[x] + list[y] + list[z] == 2020) {
                    return list[x] * list[y] * list[z]
                } else if (list[x] + list[y] + list[z] > 2020) {
                    break
                }
            }
        }
    }
    return 0
}