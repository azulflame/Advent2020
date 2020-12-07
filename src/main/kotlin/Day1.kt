package me.toddbensmiller.advent

import kotlin.system.measureNanoTime

/*
 * Created by Todd on 12/1/2020.
 */

fun day1(): Pair<Int, Int> {
    return Pair( day1part1(ListHolder.day1) , day1part2(ListHolder.day1) )
}

fun day1part1(list: List<String>): Int {
	val newList = list.map { x -> Integer.parseInt(x) }.sorted()
    for (x in newList.indices) {
        for (y in x until newList.size) {
            if (newList[x] + newList[y] == 2020) {
                return newList[x] * newList[y]
            } else if (newList[x] + newList[y] > 2020) {
                break
            }
        }
    }
    return 0
}

fun day1part2(list: List<String>): Int {
	val newList = list.map { x -> Integer.parseInt(x) }.sorted()
	for (x in newList.indices) {
        for (y in x until newList.size) {
            if (newList[x] + newList[y] >= 2020) {
                break
            }
            for (z in y until newList.size) {
                if (newList[x] + newList[y] + newList[z] == 2020) {
                    return newList[x] * newList[y] * newList[z]
                } else if (newList[x] + newList[y] + newList[z] > 2020) {
                    break
                }
            }
        }
    }
    return 0
}