package code.Young_Explicit.voting;

import redis.clients.jedis.*;
import org.bukkit.entity.*;
import java.util.*;

public class DatabaseManager
{
    public Jedis jc;
    
    public DatabaseManager() {
        super();
        (this.jc = new Jedis("localhost")).auth("trAdRApAw2sP834e4ReJAYemAhetrEcr");
        this.jc.connect();
    }
    
    public void setPlayerVote(final Player p, final Integer amount) {
        final UUID id = p.getUniqueId();
        this.jc.setex("{votecount}" + id.toString(), 39600, amount.toString());
    }
    
    public int getPlayerVotes(final Player p) {
        return this.jc.exists("{votecount}" + p.getUniqueId().toString()) ? Integer.valueOf(this.jc.get("{votecount}" + p.getUniqueId().toString())) : 0;
    }
    
    public boolean hasVoted(final Player p) {
        final UUID id = p.getUniqueId();
        return this.jc.exists("{votecount}" + id.toString());
    }
    
    public void setPlayerKeys(final Player p, final Integer amount) {
        final UUID id = p.getUniqueId();
        this.jc.set("{votekeys}" + id.toString(), amount.toString());
    }
    
    public int getPlayerKeys(final Player p) {
        return this.jc.exists("{votekeys}" + p.getUniqueId().toString()) ? Integer.valueOf(this.jc.get("{votekeys}" + p.getUniqueId().toString())) : 0;
    }
    
    public boolean doesHaveKey(final Player p) {
        final UUID id = p.getUniqueId();
        return this.jc.exists("{votekeys}" + id.toString());
    }
    
    public void clearVotes() {
        final Set<String> keys = this.jc.keys("{votecount}*");
        for (final String s : keys) {
            System.out.println(s);
            this.jc.del(s);
        }
    }
}
