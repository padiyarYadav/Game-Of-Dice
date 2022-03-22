package PlayGame.entity;

import PlayGame.utility.CommonConstants;

import java.util.*;

public class PlayerEntity {
    public PlayerEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.blocked = false;
        this.Score = 0;
        this.scoreHistory=new ArrayList<>(CommonConstants.PENALTY_NUMBER_OCCUR_VAL);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    private Integer id;
    private String name;
    private boolean blocked;
    private Integer Score;

    public List<Integer> getScoreHistory() {
        return scoreHistory;
    }

    public void setScoreHistory(List<Integer> scoreHistory) {
        this.scoreHistory = scoreHistory;
    }

    private List<Integer> scoreHistory;


}
