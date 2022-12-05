class Rucksack {
    String contents

    Rucksack(String contents) {
        this.contents = contents
    }
}

def file = new File(args[0])
String line

int score = 0
file.withReader { reader ->
    def i = 0
    Rucksack[] elves = new Rucksack[3]
    def common = ""
    while ((line = reader.readLine()) != null) {
        if (line == "") continue
        elves[i++] = new Rucksack(line)

        if (i == 3) {
            elves[0].contents.each { if (elves[1].contents.contains(it)) common += it }
            String badge = common.find { elves[2].contents.contains(it) }
            def value = badge.toCharacter().charValue() as int - (badge.toCharacter().lowerCase ?  96 : (64 - 26))
            println "$badge -> $value"

            score += value
            i = 0
            elves = new Rucksack[3]
            common = ""
        }
    }
}

println score
