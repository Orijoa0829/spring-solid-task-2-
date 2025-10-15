package com.puzzlix.solid_task.domain.project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"issues"})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    // 무조건 양방향 설정을 할 필요는 없다.
//    // 나는 fk 주인이 아니야
//    @OneToMany(mappedBy = "project")
//    private List<Issue> issues = new ArrayList<>();
    // 이런 부분은 직접 쿼리를 구현하는것이 좋다.

}
