package geotrellis.test.singleband.hadoop

import geotrellis.test.singleband.TemporalTestEnvironment

import geotrellis.spark.SpaceTimeKey
import geotrellis.spark.io._
import geotrellis.spark.io.hadoop._
import geotrellis.spark.io.index.ZCurveKeyIndexMethod
import geotrellis.spark.ingest._

import org.apache.hadoop.fs.Path

abstract class TemporalTests extends TemporalTestEnvironment {
  @transient lazy val writer = HadoopLayerWriter(new Path(hadoopIngestPath))
  @transient lazy val reader = HadoopLayerReader(new Path(hadoopIngestPath))
  @transient lazy val attributeStore = HadoopAttributeStore(hadoopIngestPath)
}