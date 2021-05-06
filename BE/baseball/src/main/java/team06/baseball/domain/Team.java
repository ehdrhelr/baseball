package team06.baseball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
public class Team {

    @JsonIgnore
    @Id
    private Long id;
    private String name;
    private String status;

    public String getTeam() {
        return this.name;
    }

    public String getStatus() {
        return this.status;
    }

}
