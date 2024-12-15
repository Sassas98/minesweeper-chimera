package minesweeper.chimera.model.enumeration;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD,
    IMPOSSIBLE;

    public static Difficulty getDifficulty(String s){
        switch (s) {
            case "EASY":
                return EASY;
            case "MEDIUM":
                return MEDIUM;
            case "HARD":
                return HARD;
            case "IMPOSSIBLE":
                return IMPOSSIBLE;
            default:
                throw new IllegalArgumentException();
        }
    }

    public int getBombDivisor(){
        return this == IMPOSSIBLE ? 3
             : this == HARD ? 4
             : this == MEDIUM ? 5 : 6;
    }

}
