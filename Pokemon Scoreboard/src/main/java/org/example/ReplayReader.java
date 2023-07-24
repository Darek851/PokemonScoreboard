package org.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class ReplayReader {
    private Team team1;
    private Team team2;
    private BufferedReader br;

    private int timestamp;

    public ReplayReader(String replay) {
        try {
            // Make a URL to the web page
            URL url = new URL(replay);
            // Get the input stream through URL Connection
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            URL log = null;
            try (BufferedReader rbr = new BufferedReader(new InputStreamReader(is))) {
                String line = null;
                // read each line and write to System.out
                while ((line = rbr.readLine()) != null & log == null) {
                    if (line.contains("You can get this log directly at")) {
                        log = new URL(line.substring(32));
                    }
                    if ((line.contains("uploaddate"))) {
                        String[] splitLine = line.split("\"");
                        timestamp = Integer.parseInt(splitLine[3]);
                    }
                }
            }
            if (log != null) {
                con = log.openConnection();
                is = con.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
            }
        } catch (IOException e) {
            br = null;
        }
        team1 = new Team();
        team2 = new Team();
    }

    public int getTime() {
        return timestamp;
        }

    public void setTeams() throws IOException {
        String line = null;
        while ((line = br.readLine()) != null & !line.equals("|start")) {
            String[] splitLine = line.split("\\|");
            if (splitLine.length >= 4) {
                if (splitLine[1].equals("player")) {
                    if (splitLine[2].equals("p1"))
                        team1.setPlayer(splitLine[3]);
                    else if (splitLine[2].equals("p2"))
                        team2.setPlayer(splitLine[3]);
                } else if (splitLine[1].equals("poke")) {
                    if (splitLine[2].equals("p1"))
                        team1.addPokemon(splitLine[3].split(",")[0].split("-\\*")[0]);
                    else if (splitLine[2].equals("p2"))
                        team2.addPokemon(splitLine[3].split(",")[0].split("-\\*")[0]);
                }
            }
        }
        line = br.readLine();
        String[] splitLine = line.split("\\|");
        team1.setCurrent(splitLine[3].split(",")[0]);
        team1.getCurrent().bringIn();
        team1.getCurrent().setLead();
        line = br.readLine();
        splitLine = line.split("\\|");
        team2.setCurrent(splitLine[3].split(",")[0]);
        team2.getCurrent().bringIn();
        team2.getCurrent().setLead();
    }

    public void readGame() throws IOException {
        String line = null;
        while ((line = br.readLine()) != null) {
            String[] splitLine = line.split("\\|");
            if (splitLine.length >= 2) {
                switch (splitLine[1]) {
                    case "switch", "drag":
                        String mon = splitLine[3].split(",")[0];
                        if (splitLine[2].contains("p1")) {
                            team1.setCurrent(mon);
                            team1.getCurrent().bringIn();
                            int switchHP = Integer.parseInt(splitLine[4].split("/")[0]);
                            team1.getCurrent().setHP(switchHP);
                        } else if (splitLine[2].contains("p2")) {
                            team2.setCurrent(mon);
                            team2.getCurrent().bringIn();
                            int switchHP = Integer.parseInt(splitLine[4].split("/")[0]);
                            team2.getCurrent().setHP(switchHP);
                        }
                        break;
                    case "move":
                        if (splitLine[2].contains("p1")) {
                            team1.getCurrent().useMove();
                        } else if (splitLine[2].contains("p2")) {
                            team2.getCurrent().useMove();
                        }
                        break;
                    case "-damage":
                        int newHP = Integer.parseInt(splitLine[3].split("/")[0].split(" ")[0]);
                        int dmg = 0;
                        if (splitLine[2].contains("p1")) {
                            dmg = team1.getCurrent().takeDmg(newHP);
                            if (splitLine.length == 4) { // this indicates direct damage from an attack (or Substitute)
                                team1.getCurrent().takeHit();
                                team2.getCurrent().dealDmg(dmg);
                            }
                            if (newHP == 0) {
                                team2.getCurrent().getKO();
                            }
                        } else if (splitLine[2].contains("p2")) {
                            dmg = team2.getCurrent().takeDmg(newHP);
                            if (splitLine.length == 4) { // this indicates direct damage from an attack
                                team2.getCurrent().takeHit();
                                team1.getCurrent().dealDmg(dmg);
                            }
                            if (newHP == 0) {
                                team1.getCurrent().getKO();
                            }
                        }
                        break;
                    case "faint":
                        if (splitLine[2].contains("p1")) {
                            team1.getCurrent().faint();
                        } else if (splitLine[2].contains("p2")) {
                            team2.getCurrent().faint();
                        }
                        break;
                    case "-heal":
                        newHP = Integer.parseInt(splitLine[3].split("/")[0]);
                        if (splitLine[2].contains("p1")) {
                            team1.getCurrent().heal(newHP);
                        } else if (splitLine[2].contains("p2")) {
                            team2.getCurrent().heal(newHP);
                        }
                        break;
                    case "-terastallize":
                        String teraType = splitLine[3];
                        if (splitLine[2].contains("p1")) {
                            team1.getCurrent().terastallize(teraType);
                            team1.terastallize();
                            if (!team2.usedTera()) {
                                team1.teraFirst();
                            }

                        } else if (splitLine[2].contains("p2")) {
                            team2.getCurrent().terastallize(teraType);
                            team2.terastallize();
                            if (!team1.usedTera()) {
                                team2.teraFirst();
                            }
                        }
                        break;
                    case "win":
                        if(splitLine[2].equals(team1.getPlayer())) {
                            team1.win();
                        } else {
                            team2.win();
                        }
                        break;
                    case "-start":
                        if(splitLine[3].equals("Substitute")) {
                            line = br.readLine();
                            splitLine = line.split("\\|");
                            if (splitLine[1].equals("-damage")) {
                                newHP = Integer.parseInt(splitLine[3].split("/")[0]);
                                if (splitLine[2].contains("p1")) {
                                    team1.getCurrent().takeDmg(newHP);
                                } else if (splitLine[2].contains("p2")) {
                                    team2.getCurrent().takeDmg(newHP);
                                }
                            }
                        }
                        break;
                    case "-immune":
                        if (splitLine[2].contains("p1")) {
                            team1.getCurrent().takeHit();
                        } else if (splitLine[2].contains("p2")) {
                            team2.getCurrent().takeHit();
                        }
                        break;
                    default:
                }
            }

        }
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void output() {
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
        }
    }
}
