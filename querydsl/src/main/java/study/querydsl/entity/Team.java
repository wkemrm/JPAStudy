package study.querydsl.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    public Long id;

    public String name;

    @OneToMany(mappedBy = "team")
    public List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
