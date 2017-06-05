package net.upliftinglemma.fate.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.ContextualDeserializer
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.databind.type.TypeFactory
import java.io.IOException

class MapArrayDeserializer(mapType: JavaType = TypeFactory.unknownType()) :
        StdDeserializer<Map<*, *>>(mapType), ContextualDeserializer {

    val mapKeyType: JavaType = mapType.containedTypeOrUnknown(0)
    val mapValueType: JavaType = mapType.containedTypeOrUnknown(1)

    override fun createContextual(ctxt: DeserializationContext, property: BeanProperty): JsonDeserializer<*> =
            MapArrayDeserializer(property.type)

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Map<*, *> {
        if (!p.isExpectedStartArrayToken) {
            throw IOException("Invalid map array")
        }

        return generateSequence { p.nextToken() }
                .takeWhile { it != JsonToken.END_ARRAY }
                .associate { deserializeEntry(p, ctxt) }
    }

    private fun deserializeEntry(p: JsonParser, ctxt: DeserializationContext): Pair<*, *> {
        if (!p.isExpectedStartArrayToken) {
            throw IOException("Invalid entry array")
        }

        p.nextToken()

        val key: Any = ctxt.readValue(p, mapKeyType)
        p.nextToken()

        val value: Any = ctxt.readValue(p, mapValueType)
        p.nextToken()

        if (p.currentToken != JsonToken.END_ARRAY) {
            throw IOException("Entry arrays must contain exactly two elements")
        }

        return Pair(key, value)
    }

}
