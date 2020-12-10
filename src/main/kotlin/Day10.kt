package me.toddbensmiller.advent

/*
 * Created by Todd on 12/9/2020.
 */
fun day10(): Pair<Number, Number>
{
	return Pair(day10part1(ListHolder.day10), day10part2(ListHolder.day10))
}
fun day10part1(input: List<String>): Number
{
	val inMap = input.map { it.toInt()}.plus(0).sorted().plus(input.map { it.toInt()}.maxOf {it}+3)
	val reduced: HashMap<Int, Int> = HashMap()
	for(x in 0 until inMap.size-1)
	{
		val diff = inMap[x+1]-inMap[x]
		reduced[diff] = (reduced[diff] ?: 0)+1
	}
	return reduced[3]!! * reduced[1]!!
}
fun day10part2(input: List<String>): Number
{
	return check10Cache(input.map { it.toInt()}.plus(input.map { it.toInt()}.maxOf {it}+3).sorted(), 0)
}

fun check10Cache(input: List<Int>, wallVal: Int): Long
{
	if(D10.hm.contains(wallVal))
		return D10.hm[wallVal]!!
	var count = 0L
	if(wallVal == input.last())
	{
		count = 1
	}
	for(x in 1..3)
	{
		val toTest = wallVal + x
		if(input.contains(toTest))
		{
			val t = check10Cache(input, toTest)
			D10.hm[toTest] = t
			count += t
		}
	}
	return count
}

object D10 {
	val hm: HashMap<Int, Long> = HashMap()
}