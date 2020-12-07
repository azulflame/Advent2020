package me.toddbensmiller.advent

/*
 * Created by Todd on 12/5/2020.
 */


fun day6(): Pair<Long, Long>
{
	return Pair(day6part1(ListHolder.day6), day6part2(ListHolder.day6))
}

fun day6part1(list: List<String>): Long
{
	var total = 0
	val questionMap = HashSet<Char>()
	for (line in list)
	{
		if (line.isBlank())
		{
			total += questionMap.count()
			questionMap.clear()
		}
		for (ch in line)
		{
			questionMap.add(ch)
		}
	}
	total += questionMap.count()
	return total.toLong()
}

// for anyone noticing my performance change, I stopped caring about performance around here
fun day6part2(list: List<String>): Long
{
	val allLowerChars by lazy { ('a'..'z').toHashSet() }
	var groupCommon = HashSet<Char>(allLowerChars)
	var total = 0
	for (line in list)
	{
		if (line.isBlank())
		{
			total += groupCommon.count()
			groupCommon = allLowerChars
		} else
		{
			for (ch in line)
			{
				groupCommon = groupCommon.intersect(line.toCharArray().toHashSet()).toHashSet()
			}
		}
	}
	total += groupCommon.count()
	return total.toLong()
}