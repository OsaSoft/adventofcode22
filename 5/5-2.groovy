def file = new File(args[0])
String line

def stacks = [].withDefault { new LinkedList<String>() }
file.withReader { reader ->
    while ((line = reader.readLine()) != "") {
        if (line.startsWith(" 1")) continue

        // get rid of brackets []
        line = line.replaceAll(~/\[(\w)]/, "\$1")
        // replace empty spots with a dash
        line = line.replaceAll(" {4}", "-")
        // get rid of extra padding whitespaces
        line = line.replaceAll(~/\s+/, "")

        line.eachWithIndex { it, i ->
            if (it != "-") stacks[i].add(it)
        }
    }

    while ((line = reader.readLine()) != null) {
        println stacks

        def matcher = line =~ /move (\d+) from (\d+) to (\d+)/
        int count = matcher[0][1] as int
        int from = matcher[0][2] as int - 1
        int to = matcher[0][3] as int - 1
        println "$count $from->$to"

        def crates = stacks[from].take(count)
        count.times { stacks[from].removeFirst() }
        while (!crates.isEmpty()) stacks[to].push(crates.removeLast())
    }
}

println stacks*.pop().join("")
