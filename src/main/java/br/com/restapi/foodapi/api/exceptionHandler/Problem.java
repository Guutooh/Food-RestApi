package br.com.restapi.foodapi.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private LocalDateTime timestamp;

    private String userMessage;

    //retorno constratints violadas.
    private List<Field> filds;

    @Getter
    @Builder
    public static class Field{
        private String name;
        private String userMessage;
    }



}
