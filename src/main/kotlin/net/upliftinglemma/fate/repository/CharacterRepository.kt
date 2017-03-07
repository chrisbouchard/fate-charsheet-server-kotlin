package net.upliftinglemma.fate.repository

import net.upliftinglemma.fate.model.FateCharacter
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(
        path = "characters",
        collectionResourceRel = "characters",
        itemResourceRel = "character")
interface CharacterRepository : CrudRepository<FateCharacter, UUID>
