package jeremy.grimont.strava_run.entity.workout;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jeremy.grimont.strava_run.entity.EffortType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discipline")
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "discipline",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Running.class, name = "RUNNING"),
        @JsonSubTypes.Type(value = Bike.class, name = "BIKE")

})

public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String description;
    private LocalDate date;
    @Positive(message = "Duration can only be positive")
    private Integer durationInMinutes;
    @Column(insertable = false, updatable = false)
    @JsonIgnore
    private String discipline;
    @PositiveOrZero(message = "Calories cannot be negative")
    private Integer calories;

    @ManyToOne(fetch = FetchType.LAZY)
    private EffortType effortType;
}