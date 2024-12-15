package minesweeper.chimera.view;

import minesweeper.chimera.model.enumeration.Difficulty;

public record GameOptions(Difficulty diff, int n, boolean safe) {}
