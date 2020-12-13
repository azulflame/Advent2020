package me.toddbensmiller.advent

/*
 * Created by Todd on 12/12/2020.
 */
fun day13(): Pair<Number, Number>
{
	return Pair(day13part1(ListHolder.day13),
	day13part2(ListHolder.day13))
}
fun day13part1(list: List<String>): Number
{
	val estimate = list[0].toInt()
	val nums = list[1].split(',').filter { x -> x != "x"}.map { x -> x.toInt()}
	val table = Array(nums.size) { Array(estimate + 1000+nums.maxOf {it}+1) { false } }
	for(x in nums.indices)
	{
		var inc = 0
		while((inc * nums[x]) < (estimate + 1000))
		{
			table[x][inc*nums[x]] = true
			inc++
		}
	}
	var index = estimate
	while(index < table[0].size)
	{
		for(x in nums.indices)
		{
			if(table[x][index])
			{
				return (index-estimate) * nums[x]
			}
		}
		index++
	}
	return 0
}

fun day13part2(list: List<String>): Number
{
	val busses: MutableList<Long> = mutableListOf()
	val modulos: MutableList<Long> = mutableListOf()
	var xcount = 1L

	// parse input
	for(x in list[1].split(','))
	{
		if(x != "x")
		{
			if(busses.size > 0)
			{
				modulos.add(xcount)
			}
			busses.add(x.toLong())
			xcount = 1
		}
		else
		{
			xcount++
		}
	}

	var n1 = busses[0]
	var n2 = busses[1]
	var lcm = lcmMod(n1,n2,modulos[0],n1)

	var mult = n1 * n2
	n1 = lcm
	for(x in 2 until busses.size)
	{
		n2 = busses[x]
		lcm = lcmMod(n1,n2,modulos[x-1], mult)
		mult *= n2
		n1 = lcm
	}
	return n1 - modulos.sum()
}


fun lcmMod(a: Long, b: Long, modulus: Long, additive: Long): Long
{
	var a1 = a
	var b1 = b
	while(b1-a1 != modulus)
	{
		if(a1 < b1)
		{
			a1 += additive
		}
		else
		{
			b1 = (a1/b) * b + b
		}
	}
	return b1
}