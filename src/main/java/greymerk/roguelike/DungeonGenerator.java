package greymerk.roguelike;

import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.dungeon.IDungeon;
import greymerk.roguelike.worldgen.IWorldEditor;
import greymerk.roguelike.worldgen.WorldEditor;
import io.github.opencubicchunks.cubicchunks.api.util.CubePos;
import io.github.opencubicchunks.cubicchunks.api.world.ICubicWorld;
import io.github.opencubicchunks.cubicchunks.api.worldgen.populator.CubePopulatorEvent;
import io.github.opencubicchunks.cubicchunks.api.worldgen.populator.ICubicPopulator;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class DungeonGenerator implements ICubicPopulator, IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		// if(world instanceof ICubicWorld) return; // Don't use this, because hybrid world uses this and cubic gen
		IWorldEditor editor = new WorldEditor(world);
		IDungeon dungeon = new Dungeon(editor);
		dungeon.spawnInChunk(world, random, chunkX, 0, chunkZ, false);
	}

	// Use Deprecated CubePopulatorEvent because Terra121 uses it.
	@SubscribeEvent
	public void generate(CubePopulatorEvent event) {
		if(!(event.getWorld() instanceof ICubicWorld)) return;
		World world = event.getWorld();
		CubePos pos = event.getCube().getCoords();
		this.generate(world, world.rand, pos, event.getCube().getBiome(pos.getCenterBlockPos()), event);
	}

	@Override
	public void generate(World world, Random random, CubePos pos, Biome biome) {
		// if(!(world instanceof ICubicWorld)) return;
		this.generate(world, random, pos, biome, new CubePopulatorEvent(world, null));
	}

	private void generate(World world, Random random, CubePos pos, Biome biome, CubePopulatorEvent event) {
		IWorldEditor editor = new WorldEditor(world);
		IDungeon dungeon = new Dungeon(editor);
		dungeon.spawnInChunk(world, random, pos.chunkPos().x, pos.getY(), pos.chunkPos().z, true);
	}
}