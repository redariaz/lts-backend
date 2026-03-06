package com.example.login_service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("shift")
public class Shift {

    @Id
    private Long shiftId;
    private String shiftName;
    private String shiftTime;

}
