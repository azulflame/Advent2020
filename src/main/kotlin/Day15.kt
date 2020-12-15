package me.toddbensmiller.advent

/*
 * Created by Todd on 12/12/2020.
 */
fun day15(): Pair<Number, Number>
{
	return Pair(
		day15part1(ListHolder.day15),
		day15part2(ListHolder.day15)
	)
}

fun day15part1(input: List<String>): Number
{
	val parsed = input.first().split(',').map { it.toInt() }
	return computeDay15(parsed, 2020)
}

fun day15part2(input: List<String>): Number
{
	val parsed = input.first().split(',').map { it.toInt() }
	return computeDay15(parsed, 30000000)
}

fun computeDay15(data: List<Int>, target: Int): Number
{
	val map: HashMap<Int, Int> = HashMap()
	for (x in data.indices)
	{
		map[data[x]] = x + 1
	}
	var currentNum = data.last()
	var index = data.size
	while (index < target)
	{
		if (map.containsKey(currentNum))
		{
			val lastNum = currentNum
			currentNum = index - map[currentNum]!!
			map[lastNum] = index
		} else
		{
			map[currentNum] = index
			currentNum = 0
		}
		index++
	}
	return currentNum
}