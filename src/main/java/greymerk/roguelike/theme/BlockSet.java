package greymerk.roguelike.theme;

import com.google.gson.JsonObject;

import greymerk.roguelike.worldgen.BlockProvider;
import greymerk.roguelike.worldgen.IBlockFactory;
import greymerk.roguelike.worldgen.IStair;
import greymerk.roguelike.worldgen.MetaBlock;
import greymerk.roguelike.worldgen.MetaStair;
import greymerk.roguelike.worldgen.blocks.BlockType;
import greymerk.roguelike.worldgen.blocks.door.Door;
import greymerk.roguelike.worldgen.blocks.door.DoorType;
import greymerk.roguelike.worldgen.blocks.door.IDoor;

public class BlockSet implements IBlockSet {

	private IBlockFactory floor;
	private IBlockFactory walls;
	private IStair stair;
	private IBlockFactory pillar;
	private IDoor door;
	private IBlockFactory lightblock;
	private IBlockFactory liquid;
	
	public BlockSet(IBlockFactory floor, IBlockFactory walls, IStair stair, IBlockFactory pillar,
					IDoor door, IBlockFactory lightblock, IBlockFactory liquid){
		
		this.floor = floor;
		this.walls = walls;
		this.stair = stair;
		this.pillar = pillar;
		this.door = door;
		this.lightblock = lightblock;
		this.liquid = liquid;
		
	}

	public BlockSet(IBlockFactory floor, IBlockFactory walls, IStair stair, IBlockFactory pillar,
			IDoor door){
		this(floor, walls, stair, pillar, door,
			new MetaBlock(BlockType.get(BlockType.GLOWSTONE)),
			new MetaBlock(BlockType.get(BlockType.WATER_FLOWING))
		);
	}
	
	public BlockSet(IBlockFactory floor, IBlockFactory walls, IStair stair, IBlockFactory pillar){
		this(floor, walls, stair, pillar,
			new Door(DoorType.get(DoorType.OAK))
		);
	}


	
	public BlockSet(IBlockFactory walls, IStair stair, IBlockFactory pillar){
		this(walls, walls, stair, pillar);
	}
	
	public BlockSet(JsonObject json, IBlockSet base){
		
		if(json.has("walls")){
			this.walls = BlockProvider.create(json.get("walls").getAsJsonObject());	
		} else {
			this.walls = base.getWall();
		}
		

		if(json.has("floor")){
			this.floor = BlockProvider.create(json.get("floor").getAsJsonObject());
		} else {
			this.floor = base.getFloor();
		}
		
		if(json.has("stair")){
			JsonObject stair = json.get("stair").getAsJsonObject();
			this.stair = stair.has("data")
					? new MetaStair(new MetaBlock(stair.get("data").getAsJsonObject()))
					: new MetaStair(new MetaBlock(stair));
		} else {
			this.stair = base.getStair();
		}

		if(json.has("pillar")){
			this.pillar = BlockProvider.create(json.get("pillar").getAsJsonObject());
		} else {
			this.pillar = base.getPillar();
		}
		
		if(json.has("door")){
			this.door = new Door(json.get("door").getAsJsonObject());
		} else {
			this.door = base.getDoor();
		}
		
		if(json.has("lightblock")){
			this.lightblock = BlockProvider.create(json.get("lightblock").getAsJsonObject());
		} else {
			this.lightblock = base.getLightBlock();
		}
		
		if(json.has("liquid")){
			this.liquid = BlockProvider.create(json.get("liquid").getAsJsonObject());
		} else {
			this.liquid = base.getLiquid();
		}
		
	}
	
	@Override
	public IBlockFactory getWall() {
		return walls;
	}

	@Override
	public IStair getStair() {
		return stair;
	}

	@Override
	public IBlockFactory getPillar() {
		return pillar;
	}

	@Override
	public IBlockFactory getFloor() {
		return this.floor;
	}

	@Override
	public IDoor getDoor() {
		return this.door;
	}

	@Override
	public IBlockFactory getLightBlock() {
		return this.lightblock;
	}

	@Override
	public IBlockFactory getLiquid() {
		return this.liquid;
	}
}
