package io.tiles.socket.dto;

/**
 * Created by Samvel Abrahamyan 12/12/16.
 */
public class RegistrationRequestDto {

    public final String name;
    public final long roomId;

    public RegistrationRequestDto(String name, long roomId) {
        this.name = name;
        this.roomId = roomId;
    }
}
