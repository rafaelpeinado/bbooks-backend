package br.edu.ifsp.spo.bulls.users.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReadingTargetProgressTO {
    private Double progress;
    private Integer done;
    private Integer remaining;
}
