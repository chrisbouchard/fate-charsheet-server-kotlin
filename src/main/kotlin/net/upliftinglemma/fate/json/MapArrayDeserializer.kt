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
import com.google.common.collect.ImmutableMap
import com.google.common.collect.Maps
import java.io.IOException

class MapArrayDeserializer(mapType: JavaType = TypeFactory.unknownType()) :
        StdDeserializer<Map<*, *>>(mapType), ContextualDeserializer {

    private val mapKeyType: JavaType = mapType.containedTypeOrUnknown(0)
    private val mapValueType: JavaType = mapType.containedTypeOrUnknown(1)

    override fun createContextual(ctxt: DeserializationContext, property: BeanProperty): JsonDeserializer<*> =
            MapArrayDeserializer(property.type)

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Map<*, *> {
        val builder = ImmutableMap.builder<Any, Any>()

        if (!p.isExpectedStartArrayToken) {
            throw IOException("Invalid map array")
        }

        while (p.nextToken() != JsonToken.END_ARRAY) {
            val entry = deserializeEntry(p, ctxt)
            builder.put(entry)
        }

        return builder.build()
    }

    private fun deserializeEntry(p: JsonParser, ctxt: DeserializationContext): Map.Entry<*, *> {
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

        return Maps.immutableEntry(key, value)
    }

}
