package code.Young_Explicit.voting.events;

import org.kitteh.tag.*;
import ru.tehkode.permissions.bukkit.*;
import org.bukkit.entity.*;
import ru.tehkode.permissions.*;
import org.bukkit.event.*;

public class NametagListener implements Listener
{
    @EventHandler
    public void onNameTag(final AsyncPlayerReceiveNameTagEvent e) {
        final Player p = e.getNamedPlayer();
        final PermissionUser puser = PermissionsEx.getUser(p);
        if (puser.inGroup("Owner")) {
            e.setTag("§4" + p.getName());
        }
        else if (puser.inGroup("Dev")) {
            e.setTag("§b" + p.getName());
        }
        else if (puser.inGroup("Admin")) {
            e.setTag("§c" + p.getName());
        }
        else if (puser.inGroup("Mod")) {
            e.setTag("§5" + p.getName());
        }
        else if (puser.inGroup("Builder")) {
            e.setTag("§3" + p.getName());
        }
        else if (puser.inGroup("Streamer")) {
            e.setTag("§3" + p.getName());
        }
        else if (puser.inGroup("BestFriend")) {
            e.setTag("§d" + p.getName());
        }
        else if (puser.inGroup("Premium")) {
            e.setTag("§9" + p.getName());
        }
        else if (puser.inGroup("VIP")) {
            e.setTag("§2" + p.getName());
        }
        else if (puser.inGroup("Pro")) {
            e.setTag("§6" + p.getName());
        }
        else if (puser.inGroup("Legend")) {
            e.setTag("§a" + p.getName());
        }
        else if (puser.inGroup("Master")) {
            e.setTag("§3" + p.getName());
        }
        else if (puser.inGroup("Infamous")) {
            e.setTag("§d" + p.getName());
        }
        else if (puser.inGroup("Advanced")) {
            e.setTag("§b§l" + p.getName());
        }
        else if (puser.inGroup("Extreme")) {
            e.setTag("§6§l" + p.getName());
        }
        else {
            e.setTag("§f" + p.getName());
        }
    }
}
