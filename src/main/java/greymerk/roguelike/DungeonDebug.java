package greymerk.roguelike;

import greymerk.roguelike.config.RogueConfig;

public class DungeonDebug {
    // A constant value that enable debug (System.out.println) and creates one dungeon near the player when the criteria is met (y >= 400).
    public static boolean debug = RogueConfig.getBoolean(RogueConfig.DEBUG);
}
