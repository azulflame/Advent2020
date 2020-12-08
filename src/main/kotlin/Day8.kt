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
		a.split(" ")[0] to mapOf('-' to -1, '+' to 1).getOrDefault(a.split(" ")[1][0], 0) * a.split(' ')[1].drop(1)
			.toInt()
	}
	return getAcc(inputMap)
}

fun day8part2(list: List<String>): Number
{
	val inputMap = list.map { a ->
		a.split(" ")[0] to mapOf('-' to -1, '+' to 1).getOrDefault(a.split(" ")[1][0], 0) * a.split(' ')[1].drop(1)
			.toInt()
	}
	// test for nops that should be jumps
	for (x in inputMap.indices)
	{
		if (inputMap[x].first == "nop")
		{
			val before: List<Pair<String, Int>> = if (x > 0)
			{
				inputMap.subList(0, x)
			} else
			{
				listOf()
			}
			val after: List<Pair<String, Int>> = if (x < inputMap.size)
			{
				inputMap.subList(x+1, inputMap.size)
			} else
			{
				listOf()
			}
			val testMap = before.plus(Pair("jmp", inputMap[x].second))
				.plus(after)
			if (doesHalt(testMap))
			{
				return getAcc(testMap)
			}
		}
	}
	// test for jumps that should be nops
	for (x in inputMap.indices)
	{
		if (inputMap[x].first == "jmp")
		{
			val before: List<Pair<String, Int>> = if (x > 0)
			{
				inputMap.subList(0, x)
			} else
			{
				listOf()
			}
			val after: List<Pair<String, Int>> = if (x < inputMap.size)
			{
				inputMap.subList(x+1, inputMap.size)
			} else
			{
				listOf()
			}
			val testMap = before.plus(Pair("nop", inputMap[x].second))
				.plus(after)
			if (doesHalt(testMap))
			{
				return getAcc(testMap)
			}
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
			"nop" ->
			{
				hashMap.add(index)
				index++
			}
			"acc" ->
			{
				hashMap.add(index)
				index++
			}
			"jmp" ->
			{
				hashMap.add(index)
				index += inputMap[index].second
			}
		}
	}
	return index >= inputMap.size
}