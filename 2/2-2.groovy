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

    RPS shapeToGet(String ending) {
        return switch (ending) {
            case "X": yield this.beats
            case "Y": yield this
            case "Z": yield values().find { it.beats(this) }
            default: throw new IllegalStateException()
        }
    }

    boolean beats(RPS other) {
        println "${this.beats} vs $other"
        return this.beats == other
    }

    static RPS parse(String input) {
        return switch (input) {
            case "A": yield ROCK
            case "B": yield PAPER
            case "C": yield SCISSORS
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
        def (opponent, ending) = line.split(' ')

        opponent = RPS.parse(opponent)
        def player = opponent.shapeToGet(ending)

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
