package rationals

import java.math.BigInteger;


data class Rational(val numerator: BigInteger, val denominator: BigInteger) : Comparable<Rational> {
    val normalizedNumerator: BigInteger
    val normalizedDenominator: BigInteger

    init {
        val gcd = numerator.gcd(denominator);
        normalizedDenominator = denominator.div(gcd).abs()

        val result = numerator.times(denominator)
        normalizedNumerator = if (result.isPositive()) numerator.div(gcd).abs() else numerator.div(gcd).times(denominator.signum().toBigInteger())
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Rational -> this.normalizedNumerator.equals(other?.normalizedNumerator) && this.normalizedDenominator.equals(other?.normalizedDenominator)
            else -> false
        }
    }

    override fun compareTo(other: Rational): Int {
        val lcdNumerator = this.normalizedNumerator.times(other.normalizedDenominator)
        val lcdOtherNumerator = other.normalizedNumerator.times(this.normalizedDenominator)

        return lcdNumerator.compareTo(lcdOtherNumerator)
    }

    override fun toString(): String {
        if (normalizedDenominator == 1.toBigInteger()) {
            return "$normalizedNumerator"
        }

        return "$normalizedNumerator/$normalizedDenominator"
    }

    override fun hashCode(): Int {
        var result = normalizedNumerator.hashCode()
        result = 31 * result + normalizedDenominator.hashCode()
        return result
    }
}

fun BigInteger.isPositive(): Boolean {
    return this.signum() == 1
}

infix fun Int.divBy(denominator: Int): Rational {
    return Rational(this.toBigInteger(), denominator.toBigInteger())
}

infix fun Long.divBy(denominator: Long): Rational {
    return Rational(toBigInteger(), denominator.toBigInteger())
}

infix fun BigInteger.divBy(denominator: BigInteger): Rational {
    return Rational(this, denominator)
}

fun String.toRational(): Rational {

    if (!contains('/')) {
        return Rational(this.toBigInteger(), BigInteger.ONE)
    }

    val (numerator, denominator) = split("/")

    return Rational(numerator.toBigInteger(), denominator.toBigInteger())
}

operator fun Rational.unaryMinus(): Rational {
    return Rational(this.numerator.negate(), this.denominator)
}

operator fun Rational.plus(other: Rational): Rational {
    val newNumerator = this.numerator.times(other.denominator) + other.numerator.times(this.denominator)
    val newDenominator = this.denominator.times(other.denominator)

    return Rational(newNumerator, newDenominator)
}

operator fun Rational.minus(other: Rational): Rational {
    val newNumerator = this.numerator.times(other.denominator) - other.numerator.times(this.denominator)
    val newDenominator = this.denominator.times(other.denominator)

    return Rational(newNumerator, newDenominator)
}

operator fun Rational.times(other: Rational): Rational {
    val newNumerator = this.numerator.times(other.numerator)
    val newDenominator = this.denominator.times(other.denominator)

    return Rational(newNumerator, newDenominator)
}

operator fun Rational.div(other: Rational): Rational {
    val newNumerator = this.numerator.times(other.denominator)
    val newDenominator = this.denominator.times(other.numerator)

    return Rational(newNumerator, newDenominator)
}

class RationalRange<Rational: Comparable<Rational>>(
        override val start: Rational,
        override val endInclusive: Rational) : ClosedRange<Rational>

operator fun Rational.rangeTo(rangeEnd: Rational) = RationalRange(this, rangeEnd)



fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}







