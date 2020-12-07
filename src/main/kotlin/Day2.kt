package me.toddbensmiller.advent

/*
 * Created by Todd on 12/1/2020.
 */

fun day2(): Pair<Int, Int>
{
	return Pair(day2part1(ListHolder.day2), day2part2(ListHolder.day2))
}

fun day2part1(list: List<String>): Int
{
	var count = 0
	list.forEach { x ->
		val split = x.replace(':', ' ').replace("  ", " ").replace('-', ' ').split(" ")
		val low = split[0].toInt()
		val high = split[1].toInt()
		val c = split[2][0]
		val pass = split[3]
		val charCount = pass.count { ch -> ch == c }
		if (charCount in low..high)
		{
			count++
		}
	}
	return count
}

fun day2part2(list: List<String>): Int
{
	var count = 0
	list.forEach { x ->
		val split = x.replace(':', ' ').replace('-', ' ').replace("  ", " ").split(" ")
		val low = split[0].toInt() - 1
		val high = split[1].toInt() - 1
		val c = split[2][0]
		val pass = split[3]
		if ((pass[low] == c).xor(pass[high] == c))
		{
			count++
		}
	}
	return count
}