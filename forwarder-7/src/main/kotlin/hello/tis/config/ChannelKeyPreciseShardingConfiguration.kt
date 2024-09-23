package hello.tis.config

import kotlin.math.abs
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm

class ChannelKeyPreciseShardingConfiguration : StandardShardingAlgorithm<String> {
    override fun doSharding(
        availableTargetNames: Collection<String>,
        shardingValue: PreciseShardingValue<String>,
    ): String {
        val hash = shardingValue.value.hashCode()
        val index = abs(hash % availableTargetNames.size)
        return availableTargetNames.elementAt(index)
    }

    override fun doSharding(
        p0: MutableCollection<String>,
        p1: RangeShardingValue<String>,
    ): MutableCollection<String> {
        return (mutableListOf())
    }
}
