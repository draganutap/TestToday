package demoblaze.functional.base;

import lombok.Getter;
import lombok.Setter;

public class TestProperties {

    @Getter
    @Setter
    private Browser browser;

    @Getter
    @Setter
    private String demoblazeUrl;

    @Getter
    @Setter
    private String chromeDriver;

    @Getter
    @Setter
    private String firefoxDriver;

    @Getter
    @Setter
    private String edgeDriver;

    @Getter
    @Setter
    private Boolean headless;
}