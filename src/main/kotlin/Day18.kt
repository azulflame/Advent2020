package me.toddbensmiller.advent

import java.util.*


/*
 * Created by Todd on 12/12/2020.
 */
fun day18(): Pair<Number, Number>
{
	return Pair(
		day18part1(ListHolder.day18),
		day18part2(ListHolder.day18)
	)
}

fun day18part1(input: List<String>): Number
{
	val parsed = input.map { it.replace(")", " )").replace("(", "( ").split(' ') }
	var sum = 0L
	for (line in parsed)
	{
		sum += resolveEquation(line, false)
	}
	return sum
}

fun day18part2(input: List<String>): Number
{
	val parsed = input.map { it.replace(")", " )").replace("(", "( ").split(' ') }
	var sum = 0L
	for (line in parsed)
	{
		sum += resolveEquation(line, true)
	}
	return sum
}


fun resolveEquation(tokens: List<String>, isPartTwo: Boolean): Long
{
	// Stack for numbers: 'values'
	val values: Stack<Long> = Stack()

	// Stack for Operators: 'ops'
	val ops: Stack<Char> = Stack()


	for (i in tokens.indices)
	{
		// Current token is a number,
		// push it to stack for numbers
		if (tokens[i].last() in ('0'..'9'))
		{
			values.push(tokens[i].toLong())
		}

		// Current token is an opening brace,
		// push it to 'ops'
		else if (tokens[i].first() == '(')
			ops.push(tokens[i].first())

		// Closing brace encountered,
		// solve entire brace
		else if (tokens[i].first() == ')')
		{
			while (ops.peek() != '(')
				values.push(
					applyOp(
						values.pop(),
						values.pop(),
						ops.pop()
					)
				)
			ops.pop()
		}

		// Current token is an operator.
		else if (tokens[i] == "+" ||
			tokens[i] == "-" ||
			tokens[i] == "*" ||
			tokens[i] == "/"
		)
		{
			// While top of 'ops' has same
			// or greater precedence to current
			// token, which is an operator.
			// Apply operator on top of 'ops'
			// to top two elements in values stack
			while (!ops.empty() && hasPrecedence(tokens[i].first(), ops.peek(), isPartTwo))
				values.push(
					applyOp(
						values.pop(),
						values.pop(),
						ops.pop()
					)
				)

			// Push current token to 'ops'.
			ops.push(tokens[i].first())
		}
	}

	// Entire expression has been
	// parsed at this point, apply remaining
	// ops to remaining values
	while (!ops.empty())
		values.push(
			applyOp(
				values.pop(),
				values.pop(),
				ops.pop()
			)
		)

	// Top of 'values' contains
	// result, return it
	return values.pop()
}



fun applyOp(a: Long, b: Long, op: Char): Long = when(op) {
	'*' -> a*b
	'-' -> a-b
	'+' -> a+b
	'/' -> a/b
	'(' -> 0
	')' -> 0
	else -> throw UnsupportedOperationException()
}

fun hasPrecedence(a: Char, b: Char, isPartTwo: Boolean): Boolean
{
	if (isPartTwo)
	{
		if (b == '(' || b == ')')
		{
			return false
		}
		if ((a == '+' || a == '-') && (b == '*' || b == '/'))
		{
			return false
		}
		return true
	}
	return !(b == '(' || b == ')')
}