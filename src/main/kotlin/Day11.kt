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

fun getVisibleSeatCoordinates(input: List<Array<Seats>>, x: Int, y: Int, immediateOnly: Boolean): List<Pair<Int,Int>>
{
	val visibleSeats: MutableList<Pair<Int,Int>> = mutableListOf()
	for(xdir in -1..1)
	{
		for(ydir in -1..1)
		{
			if(xdir != 0 || ydir != 0) // exclude the own cell
			{
				var tx = x+xdir
				var ty = y+ydir
				if(immediateOnly)
				{
					if(tx >= 0 && tx < input.size && ty >= 0 && ty < input[0].size && input[tx][ty] != Seats.FLOOR)
					{
						visibleSeats.add(Pair(tx,ty))
					}
				}
				else
				{
					while(tx >= 0 && tx < input.size && ty >= 0 && ty < input[0].size)
					{
						if(input[tx][ty] != Seats.FLOOR)
						{
							visibleSeats.add(Pair(tx,ty))
							break
						}
						tx += xdir
						ty += ydir
					}
				}
			}
		}
	}
	return visibleSeats.toList() // to unmutable list
}

fun day11part1(input: List<String>): Number
{
	var anyChanged = true
	val seatMap = mapOf('.' to Seats.FLOOR, '#' to Seats.OCCUPIED, 'L' to Seats.EMPTY)
	val inMap = input.map { a -> a.toCharArray().map { seatMap[it]!! }.toTypedArray() }
	val sightMap =
		inMap.mapIndexed { ai, a -> a.mapIndexed { bi, _ -> getVisibleSeatCoordinates(inMap, ai, bi, true) } }
	while (anyChanged)
	{
		anyChanged = false
		for (x in inMap.indices)
		{
			for (y in inMap[0].indices)
			{
				if (inMap[x][y] == Seats.OCCUPIED && sightMap[x][y].filter { a -> inMap[a.first][a.second] == Seats.OCCUPIED || inMap[a.first][a.second] == Seats.EMPTY_TEMP }
						.count() >= 4)
				{
					inMap[x][y] = Seats.EMPTY_TEMP
					anyChanged = true
				}
				if (inMap[x][y] == Seats.EMPTY && sightMap[x][y].filter { a -> inMap[a.first][a.second] == Seats.OCCUPIED || inMap[a.first][a.second] == Seats.EMPTY_TEMP }
						.count() == 0)
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

fun day11part2(input: List<String>): Number
{

	var anyChanged = true
	val seatMap = mapOf('.' to Seats.FLOOR, '#' to Seats.OCCUPIED, 'L' to Seats.EMPTY)
	val inMap = input.map { a -> a.toCharArray().map { seatMap[it]!! }.toTypedArray() }
	val sightMap =
		inMap.mapIndexed { ai, a -> a.mapIndexed { bi, _ -> getVisibleSeatCoordinates(inMap, ai, bi, false) } }
	while (anyChanged)
	{
		anyChanged = false
		for (x in inMap.indices)
		{
			for (y in inMap[0].indices)
			{
				if (inMap[x][y] == Seats.OCCUPIED && sightMap[x][y].filter { a -> inMap[a.first][a.second] == Seats.OCCUPIED || inMap[a.first][a.second] == Seats.EMPTY_TEMP }
						.count() >= 5)
				{
					inMap[x][y] = Seats.EMPTY_TEMP
					anyChanged = true
				}
				if (inMap[x][y] == Seats.EMPTY && sightMap[x][y].filter { a -> inMap[a.first][a.second] == Seats.OCCUPIED || inMap[a.first][a.second] == Seats.EMPTY_TEMP }
						.count() == 0)
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