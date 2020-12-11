package me.toddbensmiller.advent

/*
 * Created by Todd on 12/10/2020.
 */
fun day11(): Pair<Number, Number>
{
	return Pair(
		day11part1(ListHolder.day11),
		day11part2(ListHolder.day11)
	)
}

enum class Seats
{
	FLOOR,
	EMPTY,
	EMPTY_TEMP,
	OCCUPIED,
	OCCUPIED_TEMP,
}

fun day11part1(input: List<String>): Number
{
	var anyChanged = true
	val seatMap = mapOf('.' to Seats.FLOOR, '#' to Seats.OCCUPIED, 'L' to Seats.EMPTY)
	val inMap = input.map { a -> a.toCharArray().map { seatMap[it]!! }.toTypedArray() }
	while (anyChanged)
	{
		anyChanged = false
		for (x in inMap.indices)
		{
			for (y in inMap[0].indices)
			{
				if (inMap[x][y] == Seats.OCCUPIED && getCellCount(inMap, x, y) >= 4)
				{
					inMap[x][y] = Seats.EMPTY_TEMP
					anyChanged = true
				}
				if (inMap[x][y] == Seats.EMPTY && getCellCount(inMap, x, y) == 0)
				{
					inMap[x][y] = Seats.OCCUPIED_TEMP
					anyChanged = true
				}
			}
		}
		for (x in inMap.indices)
		{
			for (y in inMap[0].indices)
			{
				if (inMap[x][y] == Seats.OCCUPIED_TEMP)
				{
					inMap[x][y] = Seats.OCCUPIED
				}
				if (inMap[x][y] == Seats.EMPTY_TEMP)
				{
					inMap[x][y] = Seats.EMPTY
				}
			}
		}
	}
	return inMap.sumBy { x -> x.count { y -> y == Seats.OCCUPIED } }
}

fun getCellCount(input: List<Array<Seats>>, x: Int, y: Int): Int
{
	val xunder = x > 0
	val xover = x < input.size - 1
	val yunder = y > 0
	val yover = y < input[0].size - 1

	var count = 0
	if (xunder && yunder && (input[x - 1][y - 1] == Seats.OCCUPIED || input[x - 1][y - 1] == Seats.EMPTY_TEMP))
	{
		count++
	}
	if (xunder && (input[x - 1][y] == Seats.OCCUPIED || input[x - 1][y] == Seats.EMPTY_TEMP))
	{
		count++
	}
	if (xunder && yover && (input[x - 1][y + 1] == Seats.OCCUPIED || input[x - 1][y + 1] == Seats.EMPTY_TEMP))
	{
		count++
	}
	if (yunder && (input[x][y - 1] == Seats.OCCUPIED || input[x][y - 1] == Seats.EMPTY_TEMP))
	{
		count++
	}
	if (yover && (input[x][y + 1] == Seats.OCCUPIED || input[x][y + 1] == Seats.EMPTY_TEMP))
	{
		count++
	}
	if (xover && yunder && (input[x + 1][y - 1] == Seats.OCCUPIED || input[x + 1][y - 1] == Seats.EMPTY_TEMP))
	{
		count++
	}
	if (xover && (input[x + 1][y] == Seats.OCCUPIED || input[x + 1][y] == Seats.EMPTY_TEMP))
	{
		count++
	}
	if (xover && yover && (input[x + 1][y + 1] == Seats.OCCUPIED || input[x + 1][y + 1] == Seats.EMPTY_TEMP))
	{
		count++
	}
	return count
}

fun getLongCellCount(input: List<Array<Seats>>, x: Int, y: Int): Int
{
	var count = 0
// case upper left
	var xmod = -1
	var ymod = -1
	while (x + xmod >= 0 && y + ymod >= 0)
	{
		when (input[x + xmod][y + ymod])
		{
			Seats.EMPTY_TEMP ->
			{
				count++; break
			}
			Seats.OCCUPIED ->
			{
				count++; break
			}
			Seats.OCCUPIED_TEMP -> break
			Seats.EMPTY -> break
			else ->
			{
				xmod--; ymod--
			}
		}
	}
	// straight up
	xmod = 0
	ymod = -1
	while (y + ymod >= 0)
	{
		when (input[x + xmod][y + ymod])
		{
			Seats.EMPTY_TEMP ->
			{
				count++; break
			}
			Seats.OCCUPIED ->
			{
				count++; break
			}
			Seats.OCCUPIED_TEMP -> break
			Seats.EMPTY -> break
			else ->
			{
				ymod--
			}
		}
	}
	// upper right
	xmod = 1
	ymod = -1
	while (x + xmod < input.size && y + ymod >= 0)
	{
		when (input[x + xmod][y + ymod])
		{
			Seats.EMPTY_TEMP ->
			{
				count++; break
			}
			Seats.OCCUPIED ->
			{
				count++; break
			}
			Seats.OCCUPIED_TEMP -> break
			Seats.EMPTY -> break
			else ->
			{
				xmod++; ymod--
			}
		}
	}
	// left
	xmod = -1
	ymod = 0
	while (x + xmod >= 0)
	{
		when (input[x + xmod][y + ymod])
		{
			Seats.EMPTY_TEMP ->
			{
				count++; break
			}
			Seats.OCCUPIED ->
			{
				count++; break
			}
			Seats.OCCUPIED_TEMP -> break
			Seats.EMPTY -> break
			else ->
			{
				xmod--
			}
		}
	}
	// right
	xmod = 1
	ymod = 0
	while (x + xmod < input.size)
	{
		when (input[x + xmod][y + ymod])
		{
			Seats.EMPTY_TEMP ->
			{
				count++; break
			}
			Seats.OCCUPIED ->
			{
				count++; break
			}
			Seats.OCCUPIED_TEMP -> break
			Seats.EMPTY -> break
			else ->
			{
				xmod++
			}
		}
	}
	// lower left
	xmod = -1
	ymod = 1
	while (x + xmod >= 0 && y + ymod < input[0].size)
	{
		when (input[x + xmod][y + ymod])
		{
			Seats.EMPTY_TEMP ->
			{
				count++; break
			}
			Seats.OCCUPIED ->
			{
				count++; break
			}
			Seats.OCCUPIED_TEMP -> break
			Seats.EMPTY -> break
			else ->
			{
				xmod--; ymod++
			}
		}
	}
	// straight down
	xmod = 0
	ymod = 1
	while (y + ymod < input[0].size)
	{
		when (input[x + xmod][y + ymod])
		{
			Seats.EMPTY_TEMP ->
			{
				count++; break
			}
			Seats.OCCUPIED ->
			{
				count++; break
			}
			Seats.OCCUPIED_TEMP -> break
			Seats.EMPTY -> break
			else ->
			{
				ymod++
			}
		}
	}
	// lower left
	xmod = 1
	ymod = 1
	while (x + xmod < input.size && y + ymod < input[0].size)
	{
		when (input[x + xmod][y + ymod])
		{
			Seats.EMPTY_TEMP ->
			{
				count++; break
			}
			Seats.OCCUPIED ->
			{
				count++; break
			}
			Seats.OCCUPIED_TEMP -> break
			Seats.EMPTY -> break
			else ->
			{
				xmod++; ymod++
			}
		}
	}
	return count
}

fun day11part2(input: List<String>): Number
{
	var anyChanged = true
	val seatMap = mapOf('.' to Seats.FLOOR, '#' to Seats.OCCUPIED, 'L' to Seats.EMPTY)
	val inMap = input.map { a -> a.toCharArray().map { seatMap[it]!! }.toTypedArray() }
	while (anyChanged)
	{
		anyChanged = false
		for (x in inMap.indices)
		{
			for (y in inMap[0].indices)
			{
				if (inMap[x][y] == Seats.OCCUPIED && getLongCellCount(inMap, x, y) >= 5)
				{
					inMap[x][y] = Seats.EMPTY_TEMP
					anyChanged = true
				}
				if (inMap[x][y] == Seats.EMPTY && getLongCellCount(inMap, x, y) == 0)
				{
					inMap[x][y] = Seats.OCCUPIED_TEMP
					anyChanged = true
				}
			}
		}
		for (x in inMap.indices)
		{
			for (y in inMap[0].indices)
			{
				if (inMap[x][y] == Seats.OCCUPIED_TEMP)
				{
					inMap[x][y] = Seats.OCCUPIED
				}
				if (inMap[x][y] == Seats.EMPTY_TEMP)
				{
					inMap[x][y] = Seats.EMPTY
				}
			}
		}
	}
	return inMap.sumBy { x -> x.count { y -> y == Seats.OCCUPIED } }
}