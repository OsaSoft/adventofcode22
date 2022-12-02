def file = new File("input")
def line

def calories = [:].withDefault { 0 }
def elf = 0
file.withReader { reader ->
    while ((line = reader.readLine()) != null) {
        if (line == "") {
            elf++
            continue
        }
        calories[elf] += line as int
    }
}

println calories.values().sort().takeRight(3).sum()
