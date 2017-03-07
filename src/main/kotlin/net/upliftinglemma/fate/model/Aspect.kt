package net.upliftinglemma.fate.model

import javax.persistence.Embeddable
import javax.validation.constraints.NotNull

@Embeddable
data class Aspect(
        @NotNull
        val label: String,

        @NotNull
        val name: String
)
