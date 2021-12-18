package org.firstinspires.ftc.teamcode

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class NonNullWriteOnce<T : Any> : ReadWriteProperty<Any?, T> {
    
    private var property: T? = null

    override fun getValue(thisRef: Any?, kProp: KProperty<*>): T =
        property ?: throw IllegalStateException(
            "The property \"${kProp.name}\" from \"$thisRef\" "+
            "was accessed before it was initialized."
        )
    override fun setValue(thisRef: Any?, kProp: KProperty<*>, value: T) =
        if (property == null) property = value else throw IllegalStateException(
            "The property \"${kProp.name}\" from \"$thisRef\" "+
            "was written to after already being initialized once."
        )
}
