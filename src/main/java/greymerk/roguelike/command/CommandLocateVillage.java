package greymerk.roguelike.command;

import greymerk.roguelike.dungeon.Dungeon;
import greymerk.roguelike.worldgen.WorldEditor;
import io.github.opencubicchunks.cubicchunks.api.world.ICubicWorld;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CommandLocateVillage implements ICommand {

    @Override
    public String getName() {
        return "list_villages";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return getName();
    }

    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            World world = player.getEntityWorld();
            if(!(world instanceof ICubicWorld)) {
                sender.sendMessage(new TextComponentString("Not Cubic World to find village!"));
            }
            WorldEditor editor = new WorldEditor(world);

            //boolean foundVillage = false;

            int cX = player.chunkCoordX;
            int cZ = player.chunkCoordZ;
            for (int x = -100; x < 100; ++x)
                for(int z = -100; z < 100; ++z) {
                    int cx = cX + x, cz = cZ + z;
                    if(Dungeon.isVillageChunk(editor, cx, cz)) {
                        BlockPos pos = new BlockPos(cx * 16, 0, cz * 16);
                        // ClientCommandHandler.instance.executeCommand(sender, "/tp "+pos.getX()+" 90 "+pos.getZ());

                        //if(!player.attemptTeleport(pos.getX(), 90, pos.getZ()))
                        //    sender.sendMessage(new TextComponentString("Failed to teleport to "+pos));

                        sender.sendMessage(new TextComponentString("Found village at "+pos.getX()+", (?), "+pos.getZ()+"!"));
                        // foundVillage = true;
                        // break;
                    }
                    //if(foundVillage) break;
                }

            //if(!foundVillage)
            //sender.sendMessage(new TextComponentString("Not found any village near!"));
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return Collections.emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return getName().compareTo(o.getName());
    }
}
