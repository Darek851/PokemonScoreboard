package org.example;
import java.io.IOException;
import java.util.TreeMap;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReplayReaderTest {
    ReplayReader reader = new ReplayReader("https://replay.pokemonshowdown.com/smogtours-gen9cap-703547");
    @Test
    public void setTeamsTest() {
        try {
            reader.setTeams();
            assertEquals("JJ09LIE", reader.getTeam1().getPlayer());
            assertEquals("joeshh", reader.getTeam2().getPlayer());
            Set<String> team1Mons = reader.getTeam1().getMons();
            Set<String> team2Mons = reader.getTeam2().getMons();
            assertTrue(team1Mons.contains("Baxcalibur"));
            assertTrue(team1Mons.contains("Kingambit"));
            assertTrue(team1Mons.contains("Slowking-Galar"));
            assertTrue(team1Mons.contains("Great Tusk"));
            assertTrue(team1Mons.contains("Venomicon"));
            assertTrue(team1Mons.contains("Dragapult"));
            assertTrue(team2Mons.contains("Ursaluna"));
            assertTrue(team2Mons.contains("Cinderace"));
            assertTrue(team2Mons.contains("Rotom-Wash"));
            assertTrue(team2Mons.contains("Venomicon"));
            assertTrue(team2Mons.contains("Jumbao"));
            assertTrue(team2Mons.contains("Kingambit"));
        } catch (IOException e) {fail();}
    }

    @Test
    public void timeStampTest() {
        assertEquals(1689496997, reader.getTime());
    }

    @Test
    public void leadsTest() {
        try {
            reader.setTeams();
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(0, team1Stats.get("Baxcalibur")[8]);
            assertEquals(0, team1Stats.get("Kingambit")[8]);
            assertEquals(0, team1Stats.get("Slowking-Galar")[8]);
            assertEquals(0, team1Stats.get("Great Tusk")[8]);
            assertEquals(0, team1Stats.get("Venomicon")[8]);
            assertEquals(1, team1Stats.get("Dragapult")[8]);
            assertEquals(0, team2Stats.get("Ursaluna")[8]);
            assertEquals(0, team2Stats.get("Cinderace")[8]);
            assertEquals(1, team2Stats.get("Rotom-Wash")[8]);
            assertEquals(0, team2Stats.get("Venomicon")[8]);
            assertEquals(0, team2Stats.get("Jumbao")[8]);
            assertEquals(0, team2Stats.get("Kingambit")[8]);
        } catch (IOException e) {fail();}
    }

    @Test
    public void switchTest() {
        try {
            reader.setTeams();
            reader.readGame();
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(1, team1Stats.get("Baxcalibur")[1]);
            assertEquals(3, team1Stats.get("Kingambit")[1]);
            assertEquals(6, team1Stats.get("Slowking-Galar")[1]);
            assertEquals(1, team1Stats.get("Great Tusk")[1]);
            assertEquals(3, team1Stats.get("Venomicon")[1]);
            assertEquals(8, team1Stats.get("Dragapult")[1]);
            assertEquals(4, team2Stats.get("Ursaluna")[1]);
            assertEquals(2, team2Stats.get("Cinderace")[1]);
            assertEquals(4, team2Stats.get("Rotom-Wash")[1]);
            assertEquals(6, team2Stats.get("Venomicon")[1]);
            assertEquals(2, team2Stats.get("Jumbao")[1]);
            assertEquals(6, team2Stats.get("Kingambit")[1]);
        } catch (IOException e) {fail();}
    }
    @Test
    public void dmgDealtTest() {
        try {
            reader.setTeams();
            reader.readGame();
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(127, team1Stats.get("Baxcalibur")[3]);
            assertEquals(85, team1Stats.get("Kingambit")[3]);
            assertEquals(0, team1Stats.get("Slowking-Galar")[3]);
            assertEquals(0, team1Stats.get("Great Tusk")[3]);
            assertEquals(26, team1Stats.get("Venomicon")[3]);
            assertEquals(424, team1Stats.get("Dragapult")[3]);
            assertEquals(106, team2Stats.get("Ursaluna")[3]);
            assertEquals(40, team2Stats.get("Cinderace")[3]);
            assertEquals(29, team2Stats.get("Rotom-Wash")[3]);
            assertEquals(164, team2Stats.get("Venomicon")[3]);
            assertEquals(0, team2Stats.get("Jumbao")[3]);
            assertEquals(72, team2Stats.get("Kingambit")[3]);
        } catch (IOException e) {fail();}
    }
    @Test
    public void KOsTest() {
        try {
            reader.setTeams();
            reader.readGame();
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(1, team1Stats.get("Baxcalibur")[7]);
            assertEquals(0, team1Stats.get("Kingambit")[7]);
            assertEquals(0, team1Stats.get("Slowking-Galar")[7]);
            assertEquals(0, team1Stats.get("Great Tusk")[7]);
            assertEquals(0, team1Stats.get("Venomicon")[7]);
            assertEquals(4, team1Stats.get("Dragapult")[7]);
            assertEquals(1, team2Stats.get("Ursaluna")[7]);
            assertEquals(0, team2Stats.get("Cinderace")[7]);
            assertEquals(0, team2Stats.get("Rotom-Wash")[7]);
            assertEquals(1, team2Stats.get("Venomicon")[7]);
            assertEquals(0, team2Stats.get("Jumbao")[7]);
            assertEquals(1, team2Stats.get("Kingambit")[7]);
        } catch (IOException e) {fail();}
    }

    @Test
    public void faintTest() {
        try {
            reader.setTeams();
            reader.readGame();
            int x = 9;
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(1, team1Stats.get("Baxcalibur")[x]);
            assertEquals(0, team1Stats.get("Kingambit")[x]);
            assertEquals(0, team1Stats.get("Slowking-Galar")[x]);
            assertEquals(1, team1Stats.get("Great Tusk")[x]);
            assertEquals(1, team1Stats.get("Venomicon")[x]);
            assertEquals(0, team1Stats.get("Dragapult")[x]);
            assertEquals(1, team2Stats.get("Ursaluna")[x]);
            assertEquals(1, team2Stats.get("Cinderace")[x]);
            assertEquals(1, team2Stats.get("Rotom-Wash")[x]);
            assertEquals(1, team2Stats.get("Venomicon")[x]);
            assertEquals(1, team2Stats.get("Jumbao")[x]);
            assertEquals(1, team2Stats.get("Kingambit")[x]);
        } catch (IOException e) {fail();}
    }

    @Test
    public void healTest() {
        try {
            reader.setTeams();
            reader.readGame();
            int x = 4;
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(0, team1Stats.get("Baxcalibur")[x]);
            assertEquals(30, team1Stats.get("Kingambit")[x]);
            assertEquals(52, team1Stats.get("Slowking-Galar")[x]);
            assertEquals(6, team1Stats.get("Great Tusk")[x]);
            assertEquals(0, team1Stats.get("Venomicon")[x]);
            assertEquals(0, team1Stats.get("Dragapult")[x]);
            assertEquals(0, team2Stats.get("Ursaluna")[x]);
            assertEquals(0, team2Stats.get("Cinderace")[x]);
            assertEquals(0, team2Stats.get("Rotom-Wash")[x]);
            assertEquals(179, team2Stats.get("Venomicon")[x]);
            assertEquals(0, team2Stats.get("Jumbao")[x]);
            assertEquals(61, team2Stats.get("Kingambit")[x]);
        } catch (IOException e) {fail();}
    }

    @Test
    public void dmgTakenTest() {
        try {
            reader.setTeams();
            reader.readGame();
            int x = 5;
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(100, team1Stats.get("Baxcalibur")[x]);
            assertEquals(53, team1Stats.get("Kingambit")[x]);
            assertEquals(137, team1Stats.get("Slowking-Galar")[x]);
            assertEquals(106, team1Stats.get("Great Tusk")[x]);
            assertEquals(100, team1Stats.get("Venomicon")[x]);
            assertEquals(76, team1Stats.get("Dragapult")[x]);
            assertEquals(100, team2Stats.get("Ursaluna")[x]);
            assertEquals(100, team2Stats.get("Cinderace")[x]);
            assertEquals(100, team2Stats.get("Rotom-Wash")[x]);
            assertEquals(279, team2Stats.get("Venomicon")[x]);
            assertEquals(12, team2Stats.get("Jumbao")[x]);
            assertEquals(161, team2Stats.get("Kingambit")[x]);
        } catch (IOException e) {fail();}
    }

    @Test
    public void teraTest() {
        try {
            reader.setTeams();
            reader.readGame();
            int x = 2;
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(1, team1Stats.get("Baxcalibur")[x]);
            assertEquals(0, team1Stats.get("Kingambit")[x]);
            assertEquals(0, team1Stats.get("Slowking-Galar")[x]);
            assertEquals(0, team1Stats.get("Great Tusk")[x]);
            assertEquals(0, team1Stats.get("Venomicon")[x]);
            assertEquals(0, team1Stats.get("Dragapult")[x]);
            assertEquals(0, team2Stats.get("Ursaluna")[x]);
            assertEquals(0, team2Stats.get("Cinderace")[x]);
            assertEquals(0, team2Stats.get("Rotom-Wash")[x]);
            assertEquals(1, team2Stats.get("Venomicon")[x]);
            assertEquals(0, team2Stats.get("Jumbao")[x]);
            assertEquals(0, team2Stats.get("Kingambit")[x]);
        } catch (IOException e) {fail();}
    }

    @Test
    public void activeTurnsTest() {
        try {
            reader.setTeams();
            reader.readGame();
            int x = 0;
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(6, team1Stats.get("Baxcalibur")[x]);
            assertEquals(2, team1Stats.get("Kingambit")[x]);
            assertEquals(3, team1Stats.get("Slowking-Galar")[x]);
            assertEquals(1, team1Stats.get("Great Tusk")[x]);
            assertEquals(4, team1Stats.get("Venomicon")[x]);
            assertEquals(15, team1Stats.get("Dragapult")[x]);
            assertEquals(3, team2Stats.get("Ursaluna")[x]);
            assertEquals(2, team2Stats.get("Cinderace")[x]);
            assertEquals(4, team2Stats.get("Rotom-Wash")[x]);
            assertEquals(14, team2Stats.get("Venomicon")[x]);
            assertEquals(1, team2Stats.get("Jumbao")[x]);
            assertEquals(5, team2Stats.get("Kingambit")[x]);
        } catch (IOException e) {fail();}
    }

    @Test
    public void hitsTakenTest() {
        try {
            reader.setTeams();
            reader.readGame();
            int x = 6;
            TreeMap<String, int[]> team1Stats = reader.getTeam1().getStats();
            TreeMap<String, int[]> team2Stats = reader.getTeam2().getStats();
            assertEquals(1, team1Stats.get("Baxcalibur")[x]);
            assertEquals(2, team1Stats.get("Kingambit")[x]);
            assertEquals(8, team1Stats.get("Slowking-Galar")[x]);
            assertEquals(2, team1Stats.get("Great Tusk")[x]);
            assertEquals(2, team1Stats.get("Venomicon")[x]);
            assertEquals(2, team1Stats.get("Dragapult")[x]);
            assertEquals(1, team2Stats.get("Ursaluna")[x]);
            assertEquals(2, team2Stats.get("Cinderace")[x]);
            assertEquals(4, team2Stats.get("Rotom-Wash")[x]);
            assertEquals(9, team2Stats.get("Venomicon")[x]);
            assertEquals(1, team2Stats.get("Jumbao")[x]);
            assertEquals(2, team2Stats.get("Kingambit")[x]);
        } catch (IOException e) {fail();}
    }


}
