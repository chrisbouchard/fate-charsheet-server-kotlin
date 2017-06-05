package net.upliftinglemma.fate.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

class MapArraySerializer : StdSerializer<Map<*, *>>(Map::class.java, true) {

    override fun serialize(value: Map<*, *>, gen: JsonGenerator, provider: SerializerProvider) {
        gen.writeStartArray()

        for ((key, value) in value) {
            gen.writeStartArray(2)
            provider.defaultSerializeValue(key, gen)
            provider.defaultSerializeValue(value, gen)
            gen.writeEndArray()
        }

        gen.writeEndArray()
    }

}
