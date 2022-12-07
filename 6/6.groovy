def file = new File(args[0])

int readCharacters
file.withReader { reader ->
    def initBuffer = new char[4]
    reader.read(initBuffer, 0, 4)
    readCharacters = 4
    def buffer = initBuffer.toList()
    // normally should check for the packet here, but no input has it within first 4 chars

    println "Buffer init state: $buffer"

    def read = new char[1]
    while ((reader.read(read, 0, 1)) != -1) {
        def readChar = read[0]
        readCharacters++
        buffer.pop()
        buffer.add(readChar)

        println "Read $readChar. Updated buffer: $buffer"
        if (buffer.size() == buffer.unique(false).size()) break
    }
}

println(readCharacters)
