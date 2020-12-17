package me.toddbensmiller.advent

/*
 * Created by Todd on 12/12/2020.
 */
fun day17(): Pair<Number, Number>
{
	return Pair(
		day17part1(ListHolder.test),
		day17part2(ListHolder.day17)
	)
}

enum class State
{ ACTIVE, INACTIVE, ACTIVE_TEMP, INACTIVE_TEMP }

fun day17part1(input: List<String>): Number
{
	val parsed = input.map { x ->
		x.toCharArray().map { y ->
			when (y)
			{
				'#' -> State.ACTIVE
				'.' -> State.INACTIVE
				else -> throw Exception()
			}
		}
	}
	val xoffset = 9
	val zoffset = 9
	val yoffset = 9
	val zmax = 2 * zoffset + 1
	val xmax = parsed.size + 2 * xoffset
	val ymax = parsed[0].size + 2 * yoffset
	val grid = Array(xmax) { Array(ymax) { Array(zmax) { State.INACTIVE } } }
	for (x in parsed.indices)
	{
		for (y in parsed[0].indices)
		{
			grid[x + xoffset][y + yoffset][zoffset] = parsed[x][y]
		}
	}
	for (iteration in 1..6)
	{
		for (x in 1 until grid.size - 1)
		{
			for (y in 1 until grid[0].size - 1)
			{
				for (z in 1 until grid[0][0].size - 1)
				{
					when (grid[x][y][z])
					{
						State.INACTIVE ->
						{
							val nc = neighborCount(grid, x, y, z)
							if (nc == 3)
							{
								grid[x][y][z] = State.ACTIVE_TEMP
							}
						}
						State.ACTIVE ->
						{
							val nc = neighborCount(grid, x, y, z)
							if (nc != 2 && nc != 3)
							{
								grid[x][y][z] = State.INACTIVE_TEMP
							}
						}
					}
				}
			}
		}
		for (x in grid.indices)
		{
			for (y in grid[0].indices)
			{
				for (z in grid[0][0].indices)
				{
					when (grid[x][y][z])
					{
						State.INACTIVE_TEMP -> grid[x][y][z] = State.INACTIVE
						State.ACTIVE_TEMP -> grid[x][y][z] = State.ACTIVE
					}
				}
			}
		}
	}
	return grid.sumBy { x -> x.sumOf { y -> y.count { z -> z == State.ACTIVE } } }
}

fun day17part2(input: List<String>): Number
{
	val parsed = input.map { x ->
		x.toCharArray().map { y ->
			when (y)
			{
				'#' -> State.ACTIVE
				'.' -> State.INACTIVE
				else -> throw Exception()
			}
		}
	}
	val xoffset = 9
	val zoffset = 9
	val yoffset = 9
	val woffset = 9
	val zmax = 2 * zoffset + 1
	val xmax = parsed.size + 2 * xoffset
	val ymax = parsed[0].size + 2 * yoffset
	val grid = Array(xmax) { Array(ymax) { Array(zmax) { Array(zmax) { State.INACTIVE } } } }
	for (x in parsed.indices)
	{
		for (y in parsed[0].indices)
		{
			grid[x + xoffset][y + yoffset][zoffset][woffset] = parsed[x][y]
		}
	}
	for (iteration in 1..6)
	{
		for (x in 1 until grid.size - 1)
		{
			for (y in 1 until grid[0].size - 1)
			{
				for (z in 1 until grid[0][0].size - 1)
				{
					for (w in 1 until grid[0][0][0].size - 1)
					{
						when (grid[x][y][z][w])
						{
							State.INACTIVE ->
							{
								val nc = neighborCount(grid, x, y, z, w)
								if (nc == 3)
								{
									grid[x][y][z][w] = State.ACTIVE_TEMP
								}
							}
							State.ACTIVE ->
							{
								val nc = neighborCount(grid, x, y, z, w)
								if (nc != 2 && nc != 3)
								{
									grid[x][y][z][w] = State.INACTIVE_TEMP
								}
							}
						}
					}
				}
			}
		}
		for (x in grid.indices)
		{
			for (y in grid[0].indices)
			{
				for (z in grid[0][0].indices)
				{
					for (w in grid[0][0][0].indices)
					{
						when (grid[x][y][z][w])
						{
							State.INACTIVE_TEMP -> grid[x][y][z][w] = State.INACTIVE
							State.ACTIVE_TEMP -> grid[x][y][z][w] = State.ACTIVE
						}
					}
				}
			}
		}
	}
	return grid.sumOf { x -> x.sumOf { y -> y.sumOf { z -> z.count { w -> w == State.ACTIVE } } } }
}

fun neighborCount(grid: Array<Array<Array<State>>>, x: Int, y: Int, z: Int): Int
{
	var count = 0
	for (xs in -1..1)
	{
		for (ys in -1..1)
		{
			for (zs in -1..1)
			{
				if ((ys != 0 || xs != 0 || zs != 0) && (grid[x + xs][y + ys][z + zs] == State.ACTIVE || grid[x + xs][y + ys][z + zs] == State.INACTIVE_TEMP))
				{
					count++
				}
			}
		}
	}
	return count
}

fun neighborCount(grid: Array<Array<Array<Array<State>>>>, x: Int, y: Int, z: Int, w: Int): Int
{
	var count = 0
	for (xs in -1..1)
	{
		for (ys in -1..1)
		{
			for (zs in -1..1)
			{
				for (ws in -1..1)
				{
					if ((ys != 0 || xs != 0 || zs != 0 || ws != 0) && (grid[x + xs][y + ys][z + zs][w + ws] == State.ACTIVE || grid[x + xs][y + ys][z + zs][w + ws] == State.INACTIVE_TEMP))
					{
						count++
					}
				}
			}
		}
	}
	return count
}