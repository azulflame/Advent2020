package me.toddbensmiller.advent

import kotlin.system.measureNanoTime

/*
 * Created by Todd on 12/5/2020.
 */


fun day6(): Pair<Pair<String, Long>, Pair<String, Long>>
{
	val p1a: Long
	val p2a: Long
	val p1t = measureNanoTime { p1a = day6part1(ListHolder.day6)}
	val p2t = measureNanoTime { p2a = day6part2(ListHolder.day6) }
	return Pair(Pair(p1a.toString(), p1t), Pair(p2a.toString(), p2t))
}

fun day6part1(list: List<String>): Long
{
	var total = 0
	val questionMap = HashSet<Char>()
	for(line in list)
	{
		if(line.isBlank())
		{
			total += questionMap.count()
			questionMap.clear()
		}
		for(ch in line)
		{
			questionMap.add(ch)
		}
	}
	total += questionMap.count()
	return total.toLong()
}

fun day6part2(list: List<String>): Long
{
	val allLowerChars by lazy { ('a'..'z').toHashSet()}
	var groupCommon = HashSet<Char>(allLowerChars)
	var total = 0
	for(line in list)
	{
		if(line.isBlank())
		{
			total += groupCommon.count()
			groupCommon = allLowerChars
		}
		else
		{
			for(ch in line)
			{
				groupCommon = groupCommon.intersect(line.toCharArray().toHashSet()).toHashSet()
			}
		}
	}
	total += groupCommon.count()
	return total.toLong()
}