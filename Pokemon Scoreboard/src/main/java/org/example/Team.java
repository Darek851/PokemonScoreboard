package org.example;

import java.util.LinkedList;
import java.util.Set;
import java.util.TreeMap;

public class Team {
    private String player;
    private TreeMap<String, Pokemon> team;
    private Pokemon current;
    boolean win;
    boolean firstTera;
    boolean tera;

    public Team() {
        this.team = new TreeMap<String, Pokemon>();
        this.player = null;
        this.current = null;
        this.win = false;
        this.firstTera = false;
        this.tera = false;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public void addPokemon(String pokemon) {
        team.put(pokemon, new Pokemon(pokemon));
    }

    public void setCurrent(String pokemon) {
        current = team.get(pokemon);
    }

    public void terastallize() {
        tera = true;
    }

    public Pokemon getCurrent() {
        return current;
    }

    public Set<String> getMons() {
        return team.keySet();
    }

    public String getPlayer() {
        return player;
    }

    public boolean usedTera() {
        return tera;
    }

    public void teraFirst() {
        firstTera = true;
    }

    public void win() {
        win = true;
    }

    public TreeMap<String, int[]> getStats() { // for testing
        TreeMap<String, int[]> stats = new TreeMap<String, int[]>();
        for (String mon : team.keySet()) {
            stats.put(mon, team.get(mon).returnStats());
        }
        return stats;
    }

    public LinkedList<String[]> getData() {
        LinkedList<String[]> rows = new LinkedList<String[]>();
        for (String mon : team.keySet()) {
            String[] row = team.get(mon).getData();
            row[0] = player;
            row[11] = Integer.toString((tera ? 1 : 0));
            row[12] = Integer.toString((firstTera ? 1 : 0));
            row[15] = Integer.toString((win ? 1 : 0));
            rows.add(row);
        }
        return rows;
    }
}
