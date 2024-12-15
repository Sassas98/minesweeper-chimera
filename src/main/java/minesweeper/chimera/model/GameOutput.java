package minesweeper.chimera.model;

import java.time.LocalDateTime;

import minesweeper.chimera.model.enumeration.GameOutputType;

public record GameOutput(GameOutputType type, LocalDateTime time) { }
