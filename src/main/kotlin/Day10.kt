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
	val inMap = d10Sort(input.map { it.toInt() }.plus(0))
	val reduced: HashMap<Int, Int> = HashMap()
	for (x in 0 until inMap.size - 1)
	{
		val diff = inMap[x + 1] - inMap[x]
		reduced[diff] = (reduced[diff] ?: 0) + 1
	}
	return reduced[3]!! * reduced[1]!!
}

fun day10part2(input: List<String>): Number
{
	return check10Cache(input.map { it.toInt() }.plus(input.map { it.toInt() }.maxOf { it } + 3), 0)
}

fun check10Cache(input: List<Int>, start: Int): Long
{
	if (D10.hm.contains(start))
		return D10.hm[start]!!
	var count = 0L
	if (start == input.last())
	{
		count = 1
	}
	for (x in 1..3)
	{
		val toTest = start + x
		if (input.contains(toTest))
		{
			val t = check10Cache(input, toTest)
			D10.hm[toTest] = t
			count += t
		}
	}
	return count
}

object D10
{
	val hm: HashMap<Int, Long> = HashMap()
}

fun d10Sort(input: List<Int>): List<Int>
{
	val validNums = Array(input.size * 3) { false }
	val mList: MutableList<Int> = mutableListOf()
	for (x in input)
	{
		validNums[x] = true
	}
	for (x in validNums.indices)
	{
		if (validNums[x])
		{
			mList.add(x)
		}
	}
	return mList.plus(mList.last() + 3)
}