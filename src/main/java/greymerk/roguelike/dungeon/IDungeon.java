package greymerk.roguelike.dungeon;

import java.util.List;
import java.util.Random;

import greymerk.roguelike.dungeon.settings.ISettings;
import greymerk.roguelike.treasure.ITreasureChest;
import greymerk.roguelike.worldgen.Coord;
import net.minecraft.world.World;

public interface IDungeon {

	public void generate(ISettings setting, Coord pos);
	
	public void spawnInChunk(World world, Random rand, int chunkX, int y, int chunkZ, boolean isCubicGen);
	
	public Coord getPosition();
	
	public List<IDungeonLevel> getLevels();
	
	public List<ITreasureChest> getChests();
	
}
