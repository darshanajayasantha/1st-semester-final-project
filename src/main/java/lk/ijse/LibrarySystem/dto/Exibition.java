package lk.ijse.LibrarySystem.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Exibition {
    private String ExibitionId;
    private String ExibitionDate;
    private String ExibitionTime;
    private String ExibitionDesc;
}