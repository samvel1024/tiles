package io.tiles.socket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */

@SpringBootApplication
@ImportResource("socket-context.xml")
@Component
public class TilesApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(TilesApplication.class).run(args);
    }

}
