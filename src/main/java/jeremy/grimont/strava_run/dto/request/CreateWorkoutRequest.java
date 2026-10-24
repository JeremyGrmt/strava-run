package jeremy.grimont.strava_run.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "discipline",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateRunningRequest.class, name = "RUNNING"),
        @JsonSubTypes.Type(value = CreateBikingRequest.class,    name = "BIKE")
})
public sealed interface CreateWorkoutRequest permits CreateRunningRequest, CreateBikingRequest {
    String title();
    String description();
    LocalDate date();
    Long totalTime();
    Integer calories();
    Long effortTypeId();
    String discipline();
}
