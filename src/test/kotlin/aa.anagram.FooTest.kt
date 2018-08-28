import io.kotlintest.specs.StringSpec

class WordsTest : StringSpec({
    "chars" {
        val chars = WordsTest::class.java.getResource("wordlist.txt")
                .readText(Charsets.ISO_8859_1)
                .lines()
                .flatMap { it.toSet() }
                .map { it.toLowerCase() }
                .distinct()
                .sorted()
        chars.forEach { println(it) }
        println(" => ${chars.size} distinct chars")
    }

    "anagrams" {
        val anagrams = WordsTest::class.java.getResource("wordlist.txt")
                .readText(Charsets.ISO_8859_1)
                .lines()
                .groupBy { hash(it) }
                .map { it.value }
        anagrams
                .filter { it.size > 1 }
                .forEach { println(it.joinToString()) }
    }
})

private val primes = mapOf(
        '\'' to 2,
        'a' to 3,
        'b' to 5,
        'c' to 7,
        'd' to 11,
        'e' to 13,
        'f' to 17,
        'g' to 19,
        'h' to 23,
        'i' to 29,
        'j' to 31,
        'k' to 37,
        'l' to 41,
        'm' to 43,
        'n' to 47,
        'o' to 53,
        'p' to 59,
        'q' to 61,
        'r' to 67,
        's' to 71,
        't' to 73,
        'u' to 79,
        'v' to 83,
        'w' to 89,
        'x' to 97,
        'y' to 101,
        'z' to 103,
        'à' to 107,
        'á' to 109,
        'â' to 113,
        'ä' to 127,
        'å' to 131,
        'ç' to 137,
        'è' to 139,
        'é' to 149,
        'ê' to 151,
        'ë' to 157,
        'í' to 163,
        'î' to 167,
        'ï' to 173,
        'ñ' to 179,
        'ó' to 181,
        'ô' to 191,
        'ö' to 193,
        'ø' to 197,
        'ù' to 199,
        'ú' to 211,
        'û' to 223,
        'ü' to 227
)

private fun hash(string: String): Int = when {
    string.isEmpty() -> 0
    else -> string
            .toLowerCase()
            .toList()
            .map { primes[it] ?: 0 }
            .reduce { a, b -> a * b }
}
