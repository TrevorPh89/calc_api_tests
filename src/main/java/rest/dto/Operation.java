package rest.dto;


import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter(onMethod = @__(@JsonValue))
public enum Operation {
    ADD("add"),
    SUB("sub"),
    MUL("mul"),
    DIV("div");
    private String value;

}
