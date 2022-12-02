enum RPS {
    ROCK(SCISSORS, 1), // used scissors before defined so will be null
    PAPER(ROCK, 2),
    SCISSORS(PAPER, 3);

    private RPS beats
    int shapeScore

    // workaround for issue above
    static {
        ROCK.beats = SCISSORS
    }

    RPS(RPS beats, int shapeScore) {
        this.beats = beats
        this.shapeScore = shapeScore
    }

    boolean beats(RPS other) {
        println "${this.beats} vs $other"
        return this.beats == other
    }

    static RPS parse(String input) {
        return switch (input) {
            case "A", "X": yield ROCK
            case "B", "Y": yield PAPER
            case "C", "Z": yield SCISSORS
            default: throw new IllegalStateException()
        }
    }

    int getScore(RPS other) {
        if (this == other) return 3

        return this.beats(other) ? 6 : 0
    }
}

def file = new File(args[0])
String line

def round = 1
def totalScore = 0
file.withReader { reader ->
    while ((line = reader.readLine()) != null) {
        if (line == "") continue
        println("Round $round:")
        def (opponent, player) = line.split(' ').collect { RPS.parse(it) }
        println("$line -> $opponent $player")
        def roundScore = player.getScore(opponent)
        def shapeScore = player.shapeScore

        println("Opponent plays $opponent, player should play $player. Player gets $shapeScore+$roundScore=${roundScore + shapeScore} points")
        totalScore += roundScore + shapeScore
        println("Current tally: $totalScore")
        round++
    }
}

println totalScore
