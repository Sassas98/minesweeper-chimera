package minesweeper.chimera.model;

import minesweeper.chimera.model.enumeration.GameInputType;

public record GameInput(int x, int y, GameInputType type) {}
