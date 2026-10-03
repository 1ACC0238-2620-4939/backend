package com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PersonNamePersistenceEmbeddable {

    @Column(name = "given_names", nullable = false)
    private String givenNames;

    @Column(name = "paternal_surname", nullable = false)
    private String paternalSurname;

    @Column(name = "maternal_surname", nullable = false)
    private String maternalSurname;

    public PersonNamePersistenceEmbeddable(
            String givenNames,
            String paternalSurname,
            String maternalSurname
    ) {
        this.givenNames = givenNames;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
    }
}