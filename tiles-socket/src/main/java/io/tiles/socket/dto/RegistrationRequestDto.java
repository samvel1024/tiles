package io.tiles.socket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Samvel Abrahamyan 12/12/16.
 */
public class RegistrationRequestDto {

    public final String name;
    public final long roomId;
    public final String color;

    public RegistrationRequestDto(
            @JsonProperty("name") String name,
            @JsonProperty("roomId") long roomId,
            @JsonProperty("color") String color) {
        this.name = name;
        this.roomId = roomId;
        this.color = color;
    }

    @Override
    public String toString() {
        return "RegistrationRequestDto{" +
                "name='" + name + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}
