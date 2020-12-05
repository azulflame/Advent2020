package me.toddbensmiller.advent

import java.math.BigDecimal
import kotlin.math.max



fun main(args: Array<String>) {
	val hideAnswers: Boolean =  args.isNotEmpty() && args[0] == "--hide-answers"
	runAll(250, 1000, hideAnswers)
}

fun runOnce(): List<Pair<Pair<String, Long>, Pair<String, Long>>> {
    return listOf(
        day1(),
        day2(),
        day3(),
	    day4(),
	    day5()
    )
}

fun runAll(warmup: Int, runs: Int, hideAnswers: Boolean) {

    // first run, always happens. saves the actual answers.
    val answers = runOnce().map { x ->
        Pair(x.first.first, x.second.first)
    }
    // finish the rest of the warmups
    for (i in 0 until warmup - 1) {
        runOnce()
    }
    // run for averages
    val runList: List<List<Pair<Pair<String, Long>, Pair<String, Long>>>> = Array(runs) { runOnce() }.toList()
//     get the averages
    val averageList: MutableList<Pair<BigDecimal, BigDecimal>> = mutableListOf()
    for (i in (runList[0].indices)) {
        var sum1 = BigDecimal("0.000")
        var sum2 = BigDecimal("0.000")
        for (run in runList) {
            sum1 = sum1.add(BigDecimal(run[i].first.second.toString()))
            sum2 = sum2.add(BigDecimal(run[i].second.second.toString()))
        }
        averageList.add(Pair(sum1.div(BigDecimal(runs)), sum2.div(BigDecimal(runs))))
    }

    // whitespace padding counts
    val maxa =
        max(
            answers.maxOf { x -> x.first.length }.toInt(),
            answers.maxOf { y -> y.second.length }.toInt()
        )
    @Suppress("UNUSED_VARIABLE") val maxt = max(
        averageList.maxOf { x -> x.first.toString().length }.toInt(),
        averageList.maxOf { x -> x.second.toString().length }.toInt()
    )
    val maxtms = max(
        averageList.maxOf { x -> x.first.div(BigDecimal("1000000")).toString().length }.toInt(),
        averageList.maxOf { x -> x.second.div(BigDecimal("1000000")).toString().length }.toInt()
    )
    // print everything
    println("Warmup: $warmup\nRuns: $runs")
    for (i in averageList.indices) {
        println("Day ${i + 1}")
	    if(hideAnswers)
	    {
	    	printMsNoAnswers(averageList[i], maxtms)
	    }
	    else
	    {
		    printMsOnly(averageList[i], answers[i], maxa, maxtms)
	    }
    }
}

fun printMsNoAnswers(day: Pair<BigDecimal, BigDecimal>, maxtms: Int)
{
	println(
		"\tPart 1: ${
			BigDecimal("0.000").add(day.first.div(BigDecimal(1000000))).toString().padStart(maxtms, ' ')
		} ms"
	)
	println(
		"\tPart 2: ${
			BigDecimal("0.000").add(day.second.div(BigDecimal(1000000))).toString().padStart(maxtms, ' ')
		} ms"
	)
}

fun printMsOnly(day: Pair<BigDecimal, BigDecimal>, answers: Pair<String, String>, maxa: Int, maxtms: Int) {
    println(
        "\tPart 1: ${
            BigDecimal("0.000").add(day.first.div(BigDecimal(1000000))).toString().padStart(maxtms, ' ')
        } ms | ${answers.first.padStart(maxa)}"
    )
    println(
        "\tPart 2: ${
            BigDecimal("0.000").add(day.second.div(BigDecimal(1000000))).toString().padStart(maxtms, ' ')
        } ms | ${answers.second.padStart(maxa)}"
    )
}

@Suppress("unused")
fun printMsNs(day: Pair<BigDecimal, BigDecimal>, answers: Pair<String, String>, maxa: Int, maxtms: Int, maxt: Int) {
    println(
        "\tPart 1: ${
            day.first.div(BigDecimal(1000000)).toString().padStart(maxtms, ' ')
        } ms | ${
            day.first.toString().padStart(maxt)
        } ns | ${answers.first.padStart(maxa)}"
    )
    println(
        "\tPart 2: ${
            day.second.div(BigDecimal(1000000)).toString().padStart(maxtms, ' ')
        } ms | ${
            day.second.toString().padStart(maxt)
        } ns | ${answers.second.padStart(maxa)}"
    )
}