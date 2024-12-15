package minesweeper.chimera.view;

import minesweeper.chimera.model.enumeration.Difficulty;

public interface StartCommand {
    public void run(int dum, Difficulty diff);
}
