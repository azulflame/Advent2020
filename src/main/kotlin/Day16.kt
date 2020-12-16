package me.toddbensmiller.advent

/*
 * Created by Todd on 12/12/2020.
 */
fun day16(): Pair<Number, Number>
{
	return Pair(
		day16part1(ListHolder.day16),
		day16part2(ListHolder.day16)
	)
}

fun day16part1(input: List<String>): Number
{
	val fieldChunk = input.subList(0, input.indexOf(""))
	val nearbyTicketChunk = input.subList(input.indexOf("nearby tickets:") + 1, input.size)
	val potentialRange: HashSet<Int> = HashSet()
	val invalidValues: MutableList<Int> = mutableListOf()
	val charList = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '|', '-')

	fieldChunk.forEach { line ->
		line.replace(" or ", "|").filter { it in charList }.split('|').map { x -> x.split('-') }.forEach { range ->
			potentialRange.addAll(range[0].toInt()..range[1].toInt())
		}
	}

	nearbyTicketChunk.map { a -> a.split(',').map { b -> b.toInt() } }.forEach { c ->
		c.forEach { d ->
			if (!potentialRange.contains(d))
			{
				invalidValues.add(d)
			}
		}
	}




	return invalidValues.sum()
}

fun day16part2(input: List<String>): Number
{
	val fieldChunk = input.subList(0, input.indexOf(""))
	val myTicket = input[input.indexOf("your ticket:") + 1].split(',').map { it.toLong() }
	val nearbyTicketChunk = input.subList(input.indexOf("nearby tickets:") + 1, input.size)
	val potentialRange: HashSet<Int> = HashSet()
	val charList = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '|', '-')
	val fieldList = List(20) { mutableListOf<Int>() }
	val ranges: HashMap<String, HashSet<Int>> = HashMap()
	val possibleRanges = HashMap<Int, MutableList<String>>()


	// populate the list of all values to later identify invalid tickets
	fieldChunk.forEach { line ->
		line.replace(" or ", "|").filter { it in charList }.split('|').map { x -> x.split('-') }.forEach { range ->
			potentialRange.addAll(range[0].toInt()..range[1].toInt())
		}
	}
	// build the individual, named ranges
	fieldChunk.forEach { line ->
		val name = line.split(':').first()
		val tempSet = HashSet<Int>()
		line.replace(" or ", "|").filter { it in charList }.split('|').map { x -> x.split('-') }.forEach { range ->
			tempSet.addAll(range[0].toInt()..range[1].toInt())
		}
		ranges[name] = tempSet
	}
	// add all tickets that aren't invalid
	nearbyTicketChunk.map { a -> a.split(',').map { b -> b.toInt() } }.forEach { c ->
		if (isValidTicket(potentialRange, c))
		{
			for (x in c.indices)
			{
				fieldList[x].add(c[x])
			}
		}
	}

	// generate all possible named ranges  of each range
	fieldList.indices.forEach { x ->
		ranges.keys.forEach { range ->
			if (ranges[range]!!.containsAll(fieldList[x]))
			{
				if (!possibleRanges.containsKey(x))
				{
					possibleRanges[x] = mutableListOf()
				}
				possibleRanges[x]!!.add(range)
			}
		}
	}
	// remove range names until 1 of each remains
	var changeMade = true
	while (changeMade)
	{
		changeMade = false
		for (key in possibleRanges.keys.filter { x -> possibleRanges[x]!!.size == 1 })
		{
			for (targetKey in possibleRanges.keys.filter { x -> possibleRanges[x]!!.contains(possibleRanges[key]!!.first())
					&& possibleRanges[x]!!.size > 1 })
			{
				changeMade = true
				possibleRanges[targetKey]!!.remove(possibleRanges[key]!!.first())
			}
		}
	}
	// return the named fields that start with "departure", multiplied together
	return myTicket.filterIndexed { i, _ -> possibleRanges[i]!!.first().startsWith("departure") }
		.reduce { a, b -> a * b }
}

fun isValidTicket(pr: HashSet<Int>, ticket: List<Int>): Boolean
{
	return ticket.filter { x -> !pr.contains(x) }.count() == 0
}