package net.upliftinglemma.fate.model

import net.upliftinglemma.fate.json.MapArrayProperty
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class FateCharacter(
        @Id
        @GeneratedValue
        @Column(columnDefinition = "uuid", updatable = false)
        val id: UUID?,

        @NotNull
        val name: String,

        @ElementCollection
        @OrderColumn
        val aspects: List<Aspect>,

        @ElementCollection
        @MapKeyColumn
        @MapArrayProperty
        val skills: Map<String, Int>
)
