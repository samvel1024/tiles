package io.tiles.socket;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Samvel Abrahamyan 11/27/16.
 */

@SpringBootApplication
@ImportResource("socket-context.xml")
public class TilesApplication {

    public TilesApplication(){
        super();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(TilesApplication.class).run(args);
    }

}
