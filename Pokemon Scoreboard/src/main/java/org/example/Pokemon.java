package org.example;

public class Pokemon {
    public String pokemon;
    private int activeTurns;
    private int timesBroughtIn;
    private int curHP;
    private int damagePctDealt;
    private int damagePctHealed;
    private int damagePctTaken;
    private int hitsTaken;
    private int KOs;
    private boolean tera;
    private boolean lead;
    private boolean faint;
    private String teraType;

    public Pokemon(String pokemon) {
        this.pokemon = pokemon;
        activeTurns = 0;
        timesBroughtIn = 0;
        curHP = 100;
        damagePctDealt = 0;
        damagePctHealed = 0;
        damagePctTaken = 0;
        hitsTaken = 0;
        KOs = 0;
        tera = false;
        lead = false;
        faint = false;
        teraType = "";
    }

    public void bringIn() {
        timesBroughtIn += 1;
    }

    public void useMove() {
        activeTurns += 1;
    }

    public int takeDmg(int newHP) {
       int dmg = curHP - newHP;
       damagePctTaken += dmg;
       curHP = newHP;
       return dmg;
    }

    public void heal(int newHP) {
        damagePctHealed += newHP - curHP;
        curHP = newHP;
    }

    public void setHP(int newHP) {
        if (newHP > curHP) {
            damagePctHealed += newHP - curHP;

        } else {
            damagePctTaken += curHP - newHP;
        }
        curHP = newHP;
    }

    public void dealDmg(int dmg) {
        damagePctDealt += dmg;
        //System.out.println(pokemon + " did " + dmg + " damage, total dealt is " + damagePctDealt);
    }

    public void takeHit() {
        hitsTaken += 1;
    }

    public void faint() {
        faint = true;
    }

    public void getKO() {
        KOs += 1;
    }

    public void terastallize(String teraType) {
        tera = true;
        this.teraType = teraType;
    }

    public int[] returnStats() { //for testing
        return new int[] {activeTurns, timesBroughtIn, (tera ? 1 : 0), damagePctDealt, damagePctHealed, damagePctTaken,
                hitsTaken, KOs, (lead ? 1 : 0), (faint ? 1 : 0)};
    }

    public void setLead() {
        lead = true;
    }

    public String[] getData() {
        return new String[] {"", pokemon, (Integer.toString(activeTurns)), Integer.toString(timesBroughtIn),
                Integer.toString(KOs), Integer.toString(damagePctDealt), Integer.toString(damagePctTaken),
                Integer.toString(damagePctHealed),Integer.toString(hitsTaken),
                Integer.toString((tera ? 1 : 0)), teraType, "", "",
                Integer.toString((lead ? 1 : 0)), Integer.toString((faint ? 1 : 0)), ""};
    }



}
