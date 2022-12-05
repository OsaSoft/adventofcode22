class Assignment {
    IntRange range

    Assignment(String range) {
        def bounds = range.split("-").collect { it as int }
        this.range = new IntRange(bounds[0], bounds[1])
    }

    @Override
    String toString() {
        return "Assignment{" +
                "range=" + range +
                '}';
    }
}

def file = new File(args[0])
String line

int totalOverlaps = 0
file.withReader { reader ->
    while ((line = reader.readLine()) != null) {
        if (line == "") continue

        def assignments = line.split(",").collect { new Assignment(it) }
        println assignments

        if ((assignments[0].range - assignments[1].range).isEmpty()
                || (assignments[1].range - assignments[0].range).isEmpty()) {
            totalOverlaps++
        }
    }
}

println totalOverlaps
