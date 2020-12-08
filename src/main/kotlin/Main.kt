package me.toddbensmiller.advent


fun main(args: Array<String>)
{
	runAll().forEachIndexed { i, x ->
		println("Day ${i+1}")
		println("\tPart 1: ${x.first}")
		println("\tPart 2: ${x.second}")
	}
}


fun runAll(): List<Pair<Number, Number>>
{
	return listOf(
		day1(),
		day2(),
		day3(),
		day4(),
		day5(),
		day6(),
		day7(),
		day8()
	)
}