package me.toddbensmiller.advent

/*
 * Created by Todd on 12/7/2020.
 */
fun day8(): Pair<Number, Number>
{
	return Pair(day8part1(ListHolder.day8), day8part2(ListHolder.day8))
}

fun day8part1(list: List<String>): Number
{
	val inputMap = list.map { a ->
		a.split(" ")[0] to mapOf('-' to -1, '+' to 1)[a.split(" ")[1][0]]!! * a.split(' ')[1].drop(1)
			.toInt()
	}
	return getAcc(inputMap)
}

fun day8part2(list: List<String>): Number
{
	val inputMap = list.map { a ->
		a.split(" ")[0] to mapOf('-' to -1, '+' to 1)[a.split(" ")[1][0]]!! * a.split(' ')[1].drop(1)
			.toInt()
	}
	for (x in inputMap.indices)
	{
		if (doesHalt(flipIndex(inputMap, x)))
		{
			return getAcc(flipIndex(inputMap, x))
		}
	}
	return 0
}

fun getAcc(inputMap: List<Pair<String, Int>>): Int
{
	val hashMap: HashSet<Int> = HashSet()
	var index = 0
	var acc = 0
	while (!hashMap.contains(index) && index < inputMap.size)
	{
		when (inputMap[index].first)
		{
			"nop" ->
			{
				hashMap.add(index)
				index++
			}
			"acc" ->
			{
				hashMap.add(index)
				acc += inputMap[index].second
				index++
			}
			"jmp" ->
			{
				hashMap.add(index)
				index += inputMap[index].second
			}
		}
	}
	return acc
}

fun doesHalt(inputMap: List<Pair<String, Int>>): Boolean
{
	val hashMap: HashSet<Int> = HashSet()
	var index = 0
	while (!hashMap.contains(index) && index < inputMap.size)
	{
		when (inputMap[index].first)
		{
			"jmp" ->
			{
				hashMap.add(index)
				index += inputMap[index].second
			}
			else ->
			{
				hashMap.add(index)
				index++
			}
		}
	}
	return index >= inputMap.size
}

fun flipIndex(original: List<Pair<String, Int>>, index: Int): List<Pair<String, Int>>
{
	val before: List<Pair<String, Int>> = when
	{
		index > 0 -> original.subList(0, index)
		else -> listOf()
	}
	val after: List<Pair<String, Int>> = when
	{
		index < original.size -> original.subList(index + 1, original.size)
		else -> listOf()
	}
	val current = when (original[index].first)
	{
		"nop" -> Pair("jmp", original[index].second)
		"jmp" -> Pair("nop", original[index].second)
		else -> original[index]
	}
	return before.plus(current).plus(after)
}