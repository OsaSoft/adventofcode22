class Rucksack {
    String contents

    Rucksack(String contents) {
        this.contents = contents
    }

    def compartment1() {
        return contents.substring(0, middlePoint)
    }

    def compartment2() {
        return contents.substring(middlePoint)
    }

    private int middlePoint = contents.size() / 2
}

def file = new File(args[0])
String line

int score = 0
file.withReader { reader ->
    while ((line = reader.readLine()) != null) {
        if (line == "") continue

        def rucksack = new Rucksack(line)
        for (def character : rucksack.compartment1()) {
            if (rucksack.compartment2().contains(character)) {
                def c = character.toCharacter()
                def value = c.charValue() as int - (c.lowerCase ?  96 : (64 - 26))
                println "$c -> $value"
                score += value
                break
            }
        }
    }
}

println score
