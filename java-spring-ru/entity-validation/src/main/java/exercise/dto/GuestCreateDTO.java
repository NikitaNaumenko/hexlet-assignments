package exercise.dto;

// BEGIN
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
public class GuestCreateDTO {
    @NotNull
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @Size(min = 4, max = 4)
    private String clubCard;

    @Pattern(regexp="^\\+\\d{11,13}$")
    private String phoneNumber;

    @FutureOrPresent
    private LocalDate cardValidUntil;
}
// END
