package me.toddbensmiller.advent

import java.lang.Long.parseLong

/*
 * Created by Todd on 12/12/2020.
 */
fun day14(): Pair<Number, Number>
{
	return Pair(
		day14part1(ListHolder.day14),
		day14part2(ListHolder.day14)
	)
}

fun day14part1(input: List<String>): Number
{
	val memory: HashMap<Long, Long> = HashMap()
	var mask = "" // assigned in 1st line
	for (line in input)
	{
		if (line.contains('[')) // int operation
		{
			val temp = line.filter { it in listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '=') }.split('=')
				.map { it.toLong() }
			val address = temp[0]
			val num = temp[1]
			memory[address] = maskNum(mask, num)
		} else
		{
			mask = line.filter { it in listOf('1', '0', 'X') }
		}
	}
	return memory.values.sum()
}

fun day14part2(input: List<String>): Number
{
	val memory: HashMap<Long, Long> = HashMap()
	var mask = "" // assigned in 1st line
	for (line in input)
	{
		if (line.contains('[')) // int operation
		{
			val temp = line.filter { it in listOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '=') }.split('=')
				.map { it.toLong() }
			val originalAddress = temp[0]
			val num = temp[1]
			val maskAddress =
				originalAddress.or(parseLong(mask.map { mapOf('X' to '0').getOrDefault(it, it) }.joinToString(""), 2))
			val maskedAddresses = floatingMask(mask, maskAddress)
			for (address in maskedAddresses)
			{
				memory[address] = num
			}
		} else
		{
			mask = line.filter { it in listOf('1', '0', 'X') }
		}
	}
	return memory.values.sum()
}

fun maskNum(mask: String, num: Long): Long
{
	val andMask = parseLong(mask.map { x -> mapOf('X' to '1').getOrDefault(x, x) }.joinToString(""), 2)
	val orMask = parseLong(mask.map { x -> mapOf('X' to '0').getOrDefault(x, x) }.joinToString(""), 2)
	return num.and(andMask).or(orMask)
}

fun floatingMask(mask: String, num: Long): Set<Long>
{
	if (mask.contains('X'))
	{
		val ind = mask.indexOf('X')
		val aMask = mask.replaceFirst('X', '0')
		val num0 = num.and(floatingMaskAnd(ind))
		val num1 = num.or(floatingMaskOr(ind))
		return floatingMask(aMask, num0).plus(floatingMask(aMask, num1))
	}
	return setOf(num)
}

fun floatingMaskOr(index: Int): Long
{
	val a = MutableList(36) { 0 }
	a[index] = 1
	return parseLong(a.joinToString(""), 2)
}

fun floatingMaskAnd(index: Int): Long
{
	val a = MutableList(36) { 1 }
	a[index] = 0
	return parseLong(a.joinToString(""), 2)
}