package mastermind


/**In this programming assignment, you'll need to implement  an evaluator
 * for the Mastermind game which compares two strings and
 *  tells how many letters and letters on the right position they share.*/

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)


fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPosition = secret.zip(guess).count { it.first == it.second }
    val letters = "ABCDEF".sumBy { ch ->
        kotlin.math.min(secret.count { it == ch }, guess.count { it == ch })
    }
    return Evaluation(rightPosition, letters - rightPosition)
}

fun String.masterCompare(otherString: String): Pair<Int, Int>? =
    when {
        length != otherString.length -> null
        else -> {
            // this part is really easy
            val commonAtSameIndex = zip(otherString).count {
                    (one, another) -> one == another
            }

            var commonOverall = 0
            val countedAlready = BooleanArray(length)

            /* this is yet another detail: there's no need for toList() allocation.
             * you can iterate over this (String) straight away. */
            for (c in this) {
                /* find the first occurrence of the c character in otherString
                 * that wasn't counted already */
                val index = countedAlready
                    .asSequence()
                    .withIndex()
                    .filterNot { it.value }
                    .indexOfFirst { otherString[it.index] == c }
                if (index >= 0) {
                    countedAlready[index] = true
                    commonOverall++
                }
            }
            commonOverall to commonAtSameIndex
        }
    }