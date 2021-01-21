package co.kr.datapia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Unstagram extends BaseTimeEntity{
    @Id
    @GeneratedValue
    private Long boardId;

    @NotEmpty
    private String creatorId;


    @NotEmpty
    @Setter
    private String content;

    @NotEmpty
    @Setter
    private String image;

}
