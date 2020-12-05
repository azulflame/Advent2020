package me.toddbensmiller.advent

import java.lang.Math.max
import java.lang.Math.pow
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
	var biggest = 0
	for(line in list)
	{
		biggest = kotlin.math.max(getSeatId(line), biggest)
	}

	return biggest.toLong()
}
fun day5part2(list: List<String>): Long
{
	val idList: HashSet<Int> = HashSet()
	for(line in list)
	{
		idList.add(getSeatId(line))
	}
	return idList.first { x -> !idList.contains(x+1) && idList.contains(x+2) }.toLong()
}

fun getSeatId(s: String): Int
{
	var row= 0
	for(x in 0..6)
	{
		if(s[x] == 'B')
		{
			row += pow(2.0,6.0-x).toInt()
		}
	}
	var col = 0
	for(x in 7..9)
	{
		if(s[x] == 'R')
		{
			col += pow(2.0,9.0-x).toInt()
		}
	}
	return row*8+col
}