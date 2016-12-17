package io.tiles.socket;

import io.tiles.socket.dto.RegistrationRequestDto;

/**
 * Created by Samvel Abrahamyan 12/16/16.
 */
public class RegistartionRequestEventParser extends EventParser<RegistrationRequestDto> {


    public RegistartionRequestEventParser(String key) {
        super(key);
    }

    @Override
    public RegistrationRequestDto parse(String event) {
        return null;
    }

}
