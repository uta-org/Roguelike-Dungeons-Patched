package greymerk.roguelike;

import greymerk.roguelike.command.CommandRoguelike;
import io.github.opencubicchunks.cubicchunks.api.worldgen.CubeGeneratorsRegistry;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid="roguelike", name="Roguelike Dungeons", version=Roguelike.version, acceptableRemoteVersions="*")

public class Roguelike {

	// The instance of your mod that Forge uses.
	@Instance("roguelike")
	public static Roguelike instance;
	// TODO: change version number
	public static final String version = "1.8.0";
	public static final String date = "Oct 20 2017";
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="greymerk.roguelike.ClientProxy", serverSide="greymerk.roguelike.CommonProxy")
	public static CommonProxy proxy;
	public static DungeonGenerator worldGen = new DungeonGenerator();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// TODO
		// GameRegistry.registerWorldGenerator(worldGen, 0);

		MinecraftForge.EVENT_BUS.register(worldGen);
		CubeGeneratorsRegistry.register(worldGen, 0);
	}
	
	@EventHandler
	public void modInit(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new EntityJoinWorld());
	}
	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event){

		MinecraftServer server = event.getServer();
		ICommandManager command = server.getCommandManager();
		ServerCommandManager serverCommand = ((ServerCommandManager) command);
		serverCommand.registerCommand(new CommandRoguelike());

	}
}
