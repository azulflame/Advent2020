package me.toddbensmiller.advent

/*
 * Created by Todd on 12/6/2020.
 */
fun day7()
{
	println(day7part1(ListHolder.day7))
	println(day7part2(ListHolder.day7))
}

fun day7part1(list: List<String>): Int
{
	val canContainShinyGold: HashSet<String> = HashSet()
	canContainShinyGold.add("shiny gold")
	var changed = true
	val firstMap = list.map {
		it.split(Regex(" bags contain "))[0] to Regex("[0-9]+ ([a-z ]+) bag")
			.findAll(it.split(Regex(" bags contain "))[1]).map { a -> a.groupValues.drop(1).first() }.toList()
	}.toMap()
	while (changed)
	{
		val startSize = canContainShinyGold.size
		canContainShinyGold.addAll(firstMap.filter { x -> canContainShinyGold.intersect(x.value).isNotEmpty() }
			.map { it.key })
		changed = startSize < canContainShinyGold.size
	}

	return canContainShinyGold.size - 1
}

fun day7part2(list: List<String>): Int
{
	val firstMap = list.map {
		it.split(Regex(" bags contain "))[0] to Regex("([0-9]+ [a-z ]+) bag")
			.findAll(it.split(Regex(" bags contain "))[1]).map { a -> a.groupValues.drop(1).first() }.toList()
	}.toMap()


	return dfs(firstMap, "shiny gold")
}

fun dfs(graph: Map<String, List<String>>, target: String): Int
{
	if (graph[target].isNullOrEmpty())
		return 0
	return graph[target]!!.sumOf { x ->
		Regex("([0-9]+)").find(x)!!.value.toInt() * (1 + dfs(
			graph,
			Regex(" ([a-z ]+)").find(x)!!.groupValues[1]
		))
	}
}