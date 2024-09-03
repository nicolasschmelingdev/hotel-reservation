package com.ntconsult.hotelreservation.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Schema(name = "ErrorDetails", description = "Payload representing an error in the API request")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "status", description = "HTTP Status", example = "400")
    @JsonProperty("status")
    private Integer status;

    @Schema(name = "timestamp", description = "Timestamp of the error", example = "2022-07-15T11:21:50.902245498Z")
    @JsonProperty("timestamp")
    private OffsetDateTime timestamp;

    @Schema(name = "type", description = "Type of the error", example = "NULL")
    @JsonProperty("type")
    private String type;

    @Schema(name = "title", description = "Title of the message", example = "Invalid data")
    @JsonProperty("title")
    private String title;

    @Schema(name = "detail", description = "Detail of the message", example = "One or more fields are invalid. Correct them and try again.")
    @JsonProperty("detail")
    private String detail;

    @Schema(name = "user_message", description = "User message", example = "One or more fields are invalid. Correct them and try again.")
    @JsonProperty("user_message")
    private String userMessage;

    @Schema(name = "objects", description = "Objects related to the error")
    @JsonProperty("objects")
    private List<Object> objects;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "ProblemObject", description = "Object associated with the error")
    public static class Object implements Serializable {

        private static final long serialVersionUID = 1L;

        @Schema(name = "name", description = "Name of the object", example = "description")
        @JsonProperty("name")
        private String name;

        @Schema(name = "user_message", description = "Message related to the object", example = "Description is required.")
        @JsonProperty("user_message")
        private String userMessage;
    }
}
