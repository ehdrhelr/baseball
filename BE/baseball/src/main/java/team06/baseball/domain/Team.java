package team06.baseball.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@ToString
@JsonPropertyOrder({"team", "status"})
public class Team {

    @JsonIgnore
    @Id
    private Long id;
    private String name;
    private String status;

    public Long getId() {
        return this.id;
    }

    public String getTeam() {
        return this.name;
    }

    public String getStatus() {
        return this.status;
    }

}
