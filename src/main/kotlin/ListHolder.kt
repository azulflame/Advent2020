package me.toddbensmiller.advent

import java.io.File

/*
 * Created by Todd on 12/3/2020.
 */
object ListHolder {
	val test: List<String> by lazy { File("src/main/resources/test.txt").readLines() }
	val day1: List<String> by lazy { File("src/main/resources/day1.txt").readLines() }
	val day2: List<String> by lazy { File("src/main/resources/day2.txt").readLines() }
	val day3: List<String> by lazy { File("src/main/resources/day3.txt").readLines() }
	val day4: List<String> by lazy { File("src/main/resources/day4.txt").readLines() }
	val day5: List<String> by lazy { File("src/main/resources/day5.txt").readLines() }
	val day6: List<String> by lazy { File("src/main/resources/day6.txt").readLines() }
	val day7: List<String> by lazy { File("src/main/resources/day7.txt").readLines() }
	val day8: List<String> by lazy { File("src/main/resources/day8.txt").readLines() }
	val day9: List<String> by lazy { File("src/main/resources/day9.txt").readLines() }
	val day10: List<String> by lazy { File("src/main/resources/day10.txt").readLines() }
	val day11: List<String> by lazy { File("src/main/resources/day11.txt").readLines() }
	val day12: List<String> by lazy { File("src/main/resources/day12.txt").readLines() }
	val day13: List<String> by lazy { File("src/main/resources/day13.txt").readLines() }
	val day14: List<String> by lazy { File("src/main/resources/day14.txt").readLines() }
	val day15: List<String> by lazy { File("src/main/resources/day15.txt").readLines() }
	val day16: List<String> by lazy { File("src/main/resources/day16.txt").readLines() }
	val day17: List<String> by lazy { File("src/main/resources/day17.txt").readLines() }
	val day18: List<String> by lazy { File("src/main/resources/day18.txt").readLines() }
	val day19: List<String> by lazy { File("src/main/resources/day19.txt").readLines() }
	val day20: List<String> by lazy { File("src/main/resources/day20.txt").readLines() }
	val day21: List<String> by lazy { File("src/main/resources/day21.txt").readLines() }
	val day22: List<String> by lazy { File("src/main/resources/day22.txt").readLines() }
	val day23: List<String> by lazy { File("src/main/resources/day23.txt").readLines() }
	val day24: List<String> by lazy { File("src/main/resources/day24.txt").readLines() }
	val day25: List<String> by lazy { File("src/main/resources/day25.txt").readLines() }
}