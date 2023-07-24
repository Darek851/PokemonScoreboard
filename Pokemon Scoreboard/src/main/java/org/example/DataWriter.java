package org.example;
import java.io.*;
import java.util.LinkedList;
import java.util.TreeMap;

public class DataWriter {
    private BufferedWriter replayWriter;
    private String filePath;
    private static final String HEADER = "player,pokemon,turns_active,times_brought_in,kos,pct_dmg_dealt," +
            "pct_dmg_taken,pct_dmg_healed,hits_taken,tera,tera_type,team_tera,team_tera_first,lead,faint,team_win";

    public DataWriter(String filePath) {
        try {
            this.filePath = filePath;
            this.replayWriter = new BufferedWriter(new FileWriter(filePath, false));
            replayWriter.append(HEADER);
            replayWriter.newLine();
            replayWriter.flush();
        } catch (IOException | NumberFormatException e) {
            System.out.println("File Not Found");
            }
        }

    public void save(ReplayReader reader) {
        try {
            replayWriter = new BufferedWriter(new FileWriter(filePath, true));
            reader.setTeams();
            reader.readGame();
            LinkedList<String[]> team1Data = reader.getTeam1().getData();
            for (String[] row : team1Data) {
                for (int i = 0; i < 15; i++) {
                    replayWriter.append(row[i]);
                    replayWriter.append(",");
                }
                replayWriter.append(row[15]);
                replayWriter.newLine();
            }
            LinkedList<String[]> team2Data = reader.getTeam2().getData();
            for (String[] row : team2Data) {
                for (int i = 0; i < 15; i++) {
                    replayWriter.append(row[i]);
                    replayWriter.append(",");
                }
                replayWriter.append(row[15]);
                replayWriter.newLine();
            }
            replayWriter.flush();
        } catch (IOException e) {
            return;
        }
    }

}
