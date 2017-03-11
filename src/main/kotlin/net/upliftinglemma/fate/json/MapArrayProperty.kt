package net.upliftinglemma.fate.json

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@JacksonAnnotationsInside
@JsonDeserialize(using = MapArrayDeserializer::class)
@JsonSerialize(using = MapArraySerializer::class)
@JsonProperty
annotation class MapArrayProperty
