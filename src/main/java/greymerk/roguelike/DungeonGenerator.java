package greymerk.roguelike;

import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.dungeon.IDungeon;
import greymerk.roguelike.worldgen.IWorldEditor;
import greymerk.roguelike.worldgen.WorldEditor;
import io.github.opencubicchunks.cubicchunks.api.util.CubePos;
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
		IWorldEditor editor = new WorldEditor(world);
		IDungeon dungeon = new Dungeon(editor);
		dungeon.spawnInChunk(world, random, chunkX, 0, chunkZ);
	}

	// Use Deprecated CubePopulatorEvent because Terra121 uses it.
	@SubscribeEvent
	public void generate(CubePopulatorEvent event) {
		World world = event.getWorld();
		CubePos pos = event.getCube().getCoords();
		this.generate(world, world.rand, pos, event.getCube().getBiome(pos.getCenterBlockPos()), event);
	}

	@Override
	public void generate(World world, Random random, CubePos pos, Biome biome) {
		this.generate(world, random, pos, biome, new CubePopulatorEvent(world, null));
	}

	private void generate(World world, Random random, CubePos pos, Biome biome, CubePopulatorEvent event) {
		IWorldEditor editor = new WorldEditor(world);
		IDungeon dungeon = new Dungeon(editor);
		dungeon.spawnInChunk(world, random, pos.chunkPos().x, pos.getY(), pos.chunkPos().z);
	}
}

/*
import java.util.List;
import java.util.Random;

import com.sun.istack.internal.NotNull;
import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.dungeon.IDungeon;
import greymerk.roguelike.worldgen.IWorldEditor;
import greymerk.roguelike.worldgen.WorldEditor;
import io.github.opencubicchunks.cubicchunks.api.util.Box;
import io.github.opencubicchunks.cubicchunks.api.world.ICube;
import io.github.opencubicchunks.cubicchunks.api.worldgen.CubePrimer;
import io.github.opencubicchunks.cubicchunks.api.worldgen.ICubeGenerator;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DungeonGenerator implements ICubeGenerator {

//	@Override
//	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
//		IWorldEditor editor = new WorldEditor(world);
//		IDungeon dungeon = new Dungeon(editor);
//		dungeon.spawnInChunk(random, chunkX, chunkZ);
//	}

	@Nonnull
	private final IChunkGenerator vanilla;

	@Nonnull
	private final World world;

	@NotNull
	private final IWorldEditor editor;

	@NotNull
	private final IDungeon dungeon;

	public DungeonGenerator(IChunkGenerator vanilla, World world) {
		this.vanilla = vanilla;
		this.world = world;

		editor = new WorldEditor(world);
		dungeon = new Dungeon(editor);
	}

	@Override
	public CubePrimer generateCube(int cubeX, int cubeY, int cubeZ) {
		return null;
	}

	@Override
	public void generateColumn(Chunk column) {

	}

	@Override
	public void populate(ICube cube) {

	}

	@Override
	public Box getFullPopulationRequirements(ICube cube) {
		return null;
	}

	@Override
	public Box getPopulationPregenerationRequirements(ICube cube) {
		return null;
	}

	@Override
	public void recreateStructures(ICube cube) {

	}

	@Override
	public void recreateStructures(Chunk column) {

	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType type, BlockPos pos) {
		return null;
	}

	@Nullable
	@Override
	public BlockPos getClosestStructure(String name, BlockPos pos, boolean findUnexplored) {
		return null;
	}
}
*/