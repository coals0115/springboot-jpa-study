package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
    private String usename;
    private int age;

    @QueryProjection
    public MemberDto(String usename, int age) {
        this.usename = usename;
        this.age = age;
    }
}
