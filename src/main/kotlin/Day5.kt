package me.toddbensmiller.advent

import kotlin.math.pow
import kotlin.system.measureNanoTime

/*
 * Created by Todd on 12/5/2020.
 */


fun day5(): Pair<Pair<String, Long>, Pair<String, Long>>
{
	val p1a: Long
	val p2a: Long
	val p1t = measureNanoTime { p1a = day5part1(ListHolder.day5) }
	val p2t = measureNanoTime { p2a = day5part2(ListHolder.day5) }
	return Pair(Pair(p1a.toString(), p1t), Pair(p2a.toString(), p2t))
}

fun day5part1(list: List<String>): Long
{
	val biggest = list.maxOf {x -> getSeatIdFast(x) }
	return biggest.toLong()
}

fun day5part2(list: List<String>): Long
{
	val idList = list.map { x -> getSeatIdFast(x)}
	return idList.first { x -> !idList.contains(x + 1) && idList.contains(x + 2) }.toLong()
}

fun getSeatIdFast(s: String): Int
{
	var row = 0
	for (x in 0..6)
	{
		if (s[x] == 'B')
		{
			row += 2.0.pow(6.0 - x).toInt()
		}
	}
	var col = 0
	for (x in 7..9)
	{
		if (s[x] == 'R')
		{
			col += 2.0.pow(9.0 - x).toInt()
		}
	}
	return row * 8 + col
}
fun getSeatIdClean(s: String) = Integer.parseInt(s.map { mapOf('B' to 1, 'R' to 1).getOrDefault(it, 0)}.joinToString(""), 2)