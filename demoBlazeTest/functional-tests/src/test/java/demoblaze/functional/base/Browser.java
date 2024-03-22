package demoblaze.functional.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Browser {

    CHROME("chromeDriver"),
    FIREFOX("geckoDriver"),
    EDGE("edgeDriver");

    @Getter
    private final String driverLocationProperty;
}