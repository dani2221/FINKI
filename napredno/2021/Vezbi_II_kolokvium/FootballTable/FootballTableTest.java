package FootballTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Partial exam II 2016/2017
 */
public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}

// Your code here
class FootballTable {
    private Map<String, Team> teams;

    public FootballTable() {
        this.teams = new HashMap<>();
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        Team home = this.teams.computeIfAbsent(homeTeam, key -> new Team(homeTeam));
        Team away = this.teams.computeIfAbsent(awayTeam, key -> new Team(awayTeam));

        // setting goals
        home.setGoalsScored(home.getGoalsScored() + homeGoals);
        home.setGoalsConceded(home.getGoalsConceded() + awayGoals);
        away.setGoalsScored(away.getGoalsScored() + awayGoals);
        away.setGoalsConceded(away.getGoalsConceded() + homeGoals);

        // setting wins, losses, draws
        if (homeGoals > awayGoals) {  // homeTeam wins
            home.setWins(home.getWins() + 1);
            away.setLosses(away.getLosses() + 1);
        } else if (awayGoals > homeGoals) {  // awayTeam wins
            away.setWins(away.getWins() + 1);
            home.setLosses(home.getLosses() + 1);
        } else {
            home.setDraws(home.getDraws() + 1);
            away.setDraws(away.getDraws() + 1);
        }

        // setting games played
        home.setNumGames(home.getNumGames() + 1);
        away.setNumGames(away.getNumGames() + 1);
    }

    public void printTable() {
        List<Team> teams = this.teams.values()
                .stream()
                .sorted(Comparator
                        .comparing(Team::getPoints)
                        .thenComparing(Team::goalDifference)
                        .reversed()
                        .thenComparing(Team::getTeamName))
                .collect(Collectors.toList());
        IntStream.range(0, teams.size())
                .forEach(i -> System.out.printf("%2d. %s\n", i + 1, teams.get(i)));
    }
}
class Team {
    private String teamName;
    private int numGames;
    private int goalsScored;
    private int goalsConceded;
    private int wins;
    private int draws;
    private int losses;

    public Team(String teamName) {
        this.teamName = teamName;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
        this.numGames = 0;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getNumGames() {
        return numGames;
    }

    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getPoints() {
        return wins * 3 + draws;
    }

    public int goalDifference() {
        return goalsScored - goalsConceded;
    }

    @Override
    public String toString() {
        return String.format("%-15s%5d%5d%5d%5d%5d", teamName, numGames, wins, draws, losses, getPoints());
    }
}
