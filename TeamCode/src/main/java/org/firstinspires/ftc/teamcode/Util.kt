package org.firstinspires.ftc.teamcode

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// This is a delegate that acts like a property with the
// lateinit modifier, except it can only be written to once.
// Basically it's like a lateinit property with val instead of var.
class LateInitConstProperty<T : Any> : ReadWriteProperty<Any?, T> {
    
    private var value: T? = null

    override fun getValue(thisRef: Any?, kProp: KProperty<*>): T =
        value ?: throw IllegalStateException(
            "The property \"${kProp.name}\" from \"$thisRef\" "+
            "was accessed before it was initialized."
        )
    override fun setValue(thisRef: Any?, kProp: KProperty<*>, value: T) {
        if (this.value == null)
            this.value = value
        else throw IllegalStateException(
            "The property \"${kProp.name}\" from \"$thisRef\" "+
            "was written to after already being initialized once."
        )
    }
}
