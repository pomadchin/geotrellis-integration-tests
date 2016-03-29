package geotrellis.test

import org.apache.spark.SparkContext
import com.typesafe.config.{Config => TConfig}

package object singleband {
  def tests(implicit configuration: TConfig, sc: SparkContext) =
    List(
      () => accumulo.HadoopIngestTest.apply,
      () => accumulo.S3IngestTest.apply/*,
      () => hadoop.HadoopIngestTest.apply,
      () => hadoop.S3IngestTest.apply,
      () => s3.HadoopIngestTest.apply,
      () => s3.S3IngestTest.apply,
      () => file.HadoopIngestTest.apply,
      () => file.S3IngestTest.apply*/
    )

  def testsTemporal(implicit configuration: TConfig, sc: SparkContext) =
    List(
      () => accumulo.TemporalS3IngestTest.apply,
      () => hadoop.TemporalS3IngestTest.apply,
      () => s3.TemporalS3IngestTest.apply,
      () => file.TemporalS3IngestTest.apply
    )
}
