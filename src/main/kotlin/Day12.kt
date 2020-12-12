package me.toddbensmiller.advent

import kotlin.math.abs

/*
 * Created by Todd on 12/11/2020.
 */
fun day12(): Pair<Number, Number>
{
	return Pair(
		day12part1(ListHolder.day12),
		day12part2(ListHolder.day12)
	)
}

enum class Direction
{
	NORTH,
	EAST,
	SOUTH,
	WEST
}

fun getDirection(current: Direction, turnDir: Char, amount: Int): Direction
{
	var currDegrees =
		when (current)
		{
			Direction.NORTH -> 0
			Direction.EAST -> 90
			Direction.SOUTH -> 180
			Direction.WEST -> 270
		}
	if (turnDir == 'L')
	{
		currDegrees -= amount
	} else
	{
		currDegrees += amount
	}
	while (currDegrees < 0)
	{
		currDegrees += 360
	}
	return when (currDegrees % 360)
	{
		0 -> Direction.NORTH
		90 -> Direction.EAST
		180 -> Direction.SOUTH
		270 -> Direction.WEST
		else -> throw Exception()
	}
}

fun day12part1(list: List<String>): Number
{
	val input = list.map { x -> Pair(x.first(), x.drop(1).toInt()) }
	var currentDirection = Direction.EAST
	var vertical = input.filter { x -> x.first == 'N' }.sumOf { y -> y.second } - input.filter { x -> x.first == 'S' }
		.sumOf { y -> y.second }
	var horizontal = input.filter { x -> x.first == 'E' }.sumOf { y -> y.second } - input.filter { x -> x.first == 'W' }
		.sumOf { y -> y.second }
	for (instruction in input.filter { it.first in listOf('R', 'L', 'F') })
	{
		when (instruction.first)
		{
			'R' -> currentDirection = getDirection(currentDirection, 'R', instruction.second)
			'L' -> currentDirection = getDirection(currentDirection, 'L', instruction.second)
			'F' -> when (currentDirection)
			{
				Direction.EAST -> horizontal += instruction.second
				Direction.WEST -> horizontal -= instruction.second
				Direction.SOUTH -> vertical -= instruction.second
				Direction.NORTH -> vertical += instruction.second
			}
		}
	}
	return abs(vertical) + abs(horizontal)
}

fun day12part2(list: List<String>): Number
{
	val input = list.map { x -> Pair(x.first(), x.drop(1).toInt()) }
	var waypointH = 10
	var waypointV = 1
	var vpos = 0L
	var hpos = 0L
	for (instruction in input)
	{
		when (instruction.first)
		{
			'N' -> waypointV += instruction.second
			'E' -> waypointH += instruction.second
			'S' -> waypointV -= instruction.second
			'W' -> waypointH -= instruction.second
			'L' ->
			{
				for (x in 0 until (instruction.second / 90))
				{
					val tx = -waypointV
					val ty = waypointH
					waypointH = tx
					waypointV = ty
				}
			}
			'R' -> for (x in 0 until (instruction.second / 90))
			{
				val tx = waypointV
				val ty = -waypointH
				waypointH = tx
				waypointV = ty
			}
			'F' ->
			{
				hpos += instruction.second * waypointH
				vpos += instruction.second * waypointV
			}
		}
	}
	return abs(hpos) + abs(vpos)
}
