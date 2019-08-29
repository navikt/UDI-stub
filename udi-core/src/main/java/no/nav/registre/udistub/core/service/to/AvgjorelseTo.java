package no.nav.registre.udistub.core.service.to;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.udi.common.v2.Kodeverk;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class AvgjorelseTo {

    private String omgjortAvgjoerelsesId;
    private Kodeverk utfallstypeKode;
    private Kodeverk grunntypeKode;
    private Kodeverk tillatelseKode;
    private Boolean erPositiv;
    private Kodeverk utfallVarighetKode;
    private Integer utfallVarighet;
    private PeriodeTo utfallPeriode;
    private Kodeverk tillatelseVarighetKode;
    private Integer tillatelseVarighet;
    private PeriodeTo tillatelsePeriode;
    private LocalDate effektueringsDato;
    private LocalDate avgjoerelsesDato;
    private LocalDate iverksettelseDato;
    private LocalDate utreisefristDato;
    private String saksnummer;
    private String etat;
    private Boolean harFlyktningstatus;
    private Boolean uavklartFlyktningstatus;
    @JsonBackReference
    private PersonTo person;
}
