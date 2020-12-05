package me.toddbensmiller.advent

import kotlin.system.measureNanoTime

/*
 * Created by Todd on 12/3/2020.
 */
fun day4(): Pair<Pair<String, Long>, Pair<String, Long>>
{
	val p1a: Long
	val p2a: Long
	val p1t = measureNanoTime { p1a = day4part1(ListHolder.day4) }
	val p2t = measureNanoTime { p2a = day4part2(ListHolder.day4) }
	return Pair(Pair(p1a.toString(), p1t), Pair(p2a.toString(), p2t))
}

fun day4part1(list: List<String>): Long
{
	var validCount = 0L
	var byr = false
	var iyr = false
	var eyr = false
	var hgt = false
	var hcl = false
	var ecl = false
	var pid = false
	for (line in list)
	{
		if (line.isEmpty())
		{
			if (byr && iyr && eyr && hgt && hcl && ecl && pid)
			{
				validCount++
			}
			byr = false
			iyr = false
			eyr = false
			hgt = false
			hcl = false
			ecl = false
			pid = false
		} else
		{
			if (line.contains("byr:"))
			{
				byr = true
			}
			if (line.contains("iyr:"))
			{
				iyr = true
			}
			if (line.contains("eyr:"))
			{
				eyr = true
			}
			if (line.contains("hgt:"))
			{
				hgt = true
			}
			if (line.contains("hcl:"))
			{
				hcl = true
			}
			if (line.contains("ecl:"))
			{
				ecl = true
			}
			if (line.contains("pid:"))
			{
				pid = true
			}
		}
	}
	return validCount
}

fun day4part2(list: List<String>): Long
{
	var validCount = 0L
	val byrP = """byr:([0-9]{4})""".toRegex()
	val iyrP = """iyr:([0-9]{4})""".toRegex()
	val eyrP = """eyr:([0-9]{4})""".toRegex()
	val hgtP = """hgt:([0-9]+)(in|cm)""".toRegex()
	val hclP = """hcl:#([0-9a-f]{6})""".toRegex()
	val eclP = """ecl:(amb|blu|brn|gry|grn|hzl|oth)""".toRegex()
	val pidP = """pid:[0-9]{9}([^0-9]|$)""".toRegex()

	var byr = false
	var iyr = false
	var eyr = false
	var hgt = false
	var hcl = false
	var ecl = false
	var pid = false
	for (line in list)
	{
		if (line.isEmpty())
		{
			if (byr && iyr && eyr && hgt && hcl && ecl && pid)
			{
				validCount++
			}
			byr = false
			iyr = false
			eyr = false
			hgt = false
			hcl = false
			ecl = false
			pid = false
		} else
		{
			if (byrP.containsMatchIn(line))
			{
				val result = byrP.find(line)
				if (result != null && result.groupValues.isNotEmpty() && result.groupValues[1].toInt() in 1920..2002)
				{
					byr = true
				} else continue
			}
			if (iyrP.containsMatchIn(line))
			{
				val result = iyrP.find(line)
				if (result != null && result.groupValues.isNotEmpty() && result.groupValues[1].toInt() in 2010..2020)
				{
					iyr = true
				} else continue
			}
			if (eyrP.containsMatchIn(line))
			{
				val result = eyrP.find(line)
				if (result != null && result.groupValues.isNotEmpty() && result.groupValues[1].toInt() in 2020..2030)
				{
					eyr = true
				} else continue
			}
			if (hgtP.containsMatchIn(line))
			{
				val result = hgtP.find(line)
				if (result != null && result.groupValues.isNotEmpty() && (result.groupValues[1].toInt() in 150..193 && result.groupValues[2] == "cm"
							|| (result.groupValues[1].toInt() in 59..76 && result.groupValues[2] == "in"))
				)
				{
					hgt = true
				}
				else continue
			}
			if (hclP.containsMatchIn(line))
			{
				hcl = true
			}
			if (eclP.containsMatchIn(line))
			{
				ecl = true
			}
			if (pidP.containsMatchIn(line))
			{
				pid = true
			}
		}
	}
	if (byr && iyr && eyr && hgt && hcl && ecl && pid)
	{
		validCount++
	}
	return validCount
}