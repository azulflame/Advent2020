package me.toddbensmiller.advent

import java.lang.Integer.max

/*
 * Created by Todd on 12/8/2020.
 */
fun day9(): Pair<Number, Number>
{
	val a = day9part1(ListHolder.day9)
	val b = day9part2(ListHolder.day9, a)
	return Pair(a, b)
}

fun day9part1(list: List<String>): Number
{
	val input = list.map { it.toLong() }
	val preamble = 25
	for (x in input.indices.minus(0 until preamble))
	{
		if (!isValid(input, x, preamble))
		{
			return input[x]
		}
	}
	return 0
}

fun isValid(tape: List<Long>, index: Int, preamble: Int): Boolean
{
	val tempList = tape.subList(max(0, index - preamble), index)
	val set: HashSet<Long> = HashSet()
	for (a in tempList)
	{
		if (set.contains(tape[index] - a))
		{
			return true
		}
		set.add(a)
	}
	return false
}

fun day9part2(list: List<String>, p1ans: Number): Number
{
	val nums = list.map { it.toLong() }
	for (start in nums.indices)
	{
		for (end in start + 1 until nums.size)
		{
			if (nums.subList(start, end).sumOf { it } == p1ans)
			{
				return nums.subList(start, end).minOf { it } + nums.subList(start, end).maxOf { it }
			}
		}
	}
	return 0
}
