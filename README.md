<img src="https://github.com/from-source/kiwi/blob/master/img/850x350_kiwi_SMALL.png">

# Kiwi: Fluent assertions for Kotlin projects

[![Build Status](https://travis-ci.com/from-source/kiwi.svg?branch=master)](https://travis-ci.com/from-source/kiwi)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/from-source/kiwi/blob/master/LICENSE.md)


*Kiwi* is multiplatform projects written in pure Kotlin. Except testing it does not use external dependencies

### Kiwi, Hello World!

Below snippet demonstrates usage of Kiwi assertions for standard type like String
```kotlin
    @Test
    fun `should say hallo to kiwi()` {
        "Kiwi, Hello World!".should startWith "Kiwi" contain "Hello" endWith "!"
    }
```

### Collections

Kiwi supports `List`, `Set` and `Map`
```kotlin
    @Test
    fun `should combine operator in infix chain`() {
        val animals = listOf("kiwi", "hedgehog", "flamingo", "humpback")

        animals.should haveSize 4 contain "humpback" have1st "kiwi"
    }
```

### Numbers

as well as `Byte`, `Short`, `Int`, `Long`, `Float`, `Double` and JVM based numbers like `BigDecimal`, `BigNumber`  
```kotlin
    @Test
    fun `should check if numer is prime`() {
        val number = 7

        number.should
                .bePositive()
                .beGreaterThan(1)
                .match { number -> (2 until number).all { number % it != 0} }
    }
```

### Dates

`LocalDate` and `LocalDateTime`
```kotlin
    @Test
    fun `should check if date meets some conditions`() {
        val date = LocalDate.of(2020, 11, 14)

        date.should
               .beInLeapYear()
               .beInQ4()
               .beBefore(date.plusDays(1))
    }
```

### Custom type

... even custom types are supported

```kotlin
    data class Animal(val name: String, val weight: Int, val mammal: Boolean) {
       fun heavy(): Boolean = weight > 10
    }

    val kiwi     = Animal(name = "kiwi", weight = 1, mammal = true)
    val hedgehog = Animal(name = "hedgehog", weight = 2, mammal = true)
    val flamingo = Animal(name = "flamingo", weight = 5, mammal = false)
    val humpback = Animal(name = "humpback", weight = 5000, mammal = true)


    @Test
    fun `should apply collection operators for list of custom object`() {

        val animals = listOf(kiwi, hedgehog, flamingo, humpback)

        animals.should
                .haveSize(4)
                .contain(flamingo)
                .beSorted { it.weight }
                .filtered { animal -> animal.mammal }
                .matchAny { animal -> animal.heavy() }

    }
```

### JsonPath

If you need json path Kiwi will work too. See `Roadmap & Future` below for more details 

```kotlin
    @Test
    fun `should select json paths for json string`() {
        val json = """{
            "kiwi": "kotlin assertion library",
            "github": {
                "developers": ["john", "ben"]
            }
        }"""

        json.should
                .haveJsonPath("$.kiwi")
                .haveJsonPath("$.kiwi", """"kotlin assertion library"""")
                .haveJsonPath("$..developers", """["john", "ben"]""")
    }
```

### Mix of the operators 
Last but not least you can mix all together 
Different types of operators e.g. `Collection`, `String`, `Numbers` can be used fluently

```kotlin
    @Test
    fun `should mix different types of operators`() {

        val animals = listOf(kiwi, hedgehog, flamingo, humpback)

        animals.should
                .contain(hedgehog)                              // Collection operator
                .last().name                                    // extract
                .should                             
                .match(Regex("[a-z]+"))                         // String operator
    }
```

## Build
Run command below
```bash
$ git clone git@github.com/from-source/kiwi.git
$ cd kiwi/
$ ./gradlew clean build
```

## Roadmap & Future 

### JsonPath evaluation - in progress

Take a look at the [JsonPathAcceptanceTest](https://github.com/from-source/kiwi/blob/master/kiwi-json-path/src/commonTest/kotlin/io.from.source.kiwi.jsonpath/JsonPathAcceptanceTest.kt) to see which json path selectors are available

```kotlin
    @Test
    fun `should select first book in the store`() {
        val json =  """{ 
            "store": {
                "details": {
                    "name": "Books & Books"
                },
                "books": [
                { 
                    "category": "science",
                    "author": "Douglas Hofstadter",
                    "title": "I Am a Strange Loop",
                    "price": 8.95
                },
                { 
                    "category": "science fiction",
                    "author": "Stanislaw Lem",
                    "title": "Solaris",
                    "isbn": "978-0156027601",
                    "price": 10.99,
                    "details": "Published in 1961"
                }]
            }
        }"""

        json.should
                .haveJsonPath("$.store.details")                                // check if path exists
                .haveJsonPath("$..books[1].category", "science fiction")        // check value of path
                .haveJsonPath("$..books[0]", """{                              
                    "category": "science",
                    "author": "Douglas Hofstadter",
                    "title": "I Am a Strange Loop",
                    "price": 8.95
                }""")
                

    }
```

### Support for more Kotlin types



### Sponsorship
Thank you JetBrains to sponsor the project

<a href="https://www.jetbrains.com/?from=kiwi">
    <img height="100" width="100" src="https://github.com/from-source/kiwi/blob/master/img/jetbrains-variant-2.png">
</a>
