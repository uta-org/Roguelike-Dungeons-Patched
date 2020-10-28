package greymerk.roguelike.dungeon.tasks;

import java.util.List;
import java.util.Random;

import greymerk.roguelike.DungeonDebug;
import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.dungeon.DungeonTunnel;
import greymerk.roguelike.dungeon.IDungeon;
import greymerk.roguelike.dungeon.IDungeonLevel;
import greymerk.roguelike.dungeon.settings.ISettings;
import greymerk.roguelike.worldgen.IWorldEditor;

public class DungeonTaskTunnels implements IDungeonTask{

	@Override
	public void execute(IWorldEditor editor, Random rand, IDungeon dungeon, ISettings settings) {
		List<IDungeonLevel> levels = dungeon.getLevels();

		// int steps = 0;
		if(DungeonDebug.debug) {
			System.out.println("Creating a tunnel with "+getSteps(levels)+" steps!");
		}

		// generate tunnels
		for(IDungeonLevel level : levels){
			for(DungeonTunnel t : level.getLayout().getTunnels()){
				t.construct(editor, rand, level.getSettings());
				// ++steps;
			}
		}

		// Dungeon.isGenerating = false; // Disable flag
	}

	private static int getSteps(List<IDungeonLevel> levels) {
		int steps = 0;
		for(IDungeonLevel level : levels){
			steps += level.getLayout().getTunnels().size();
		}
		return steps;
	}
}
