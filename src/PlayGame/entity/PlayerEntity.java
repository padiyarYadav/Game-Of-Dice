package PlayGame.entity;

public class PlayerEntity {
    public PlayerEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.blocked = false;
        Score = 0;
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


}
