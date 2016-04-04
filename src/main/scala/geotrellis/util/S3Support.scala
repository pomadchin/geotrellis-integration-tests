package geotrellis.util

import geotrellis.config.json.backend.JBackend
import geotrellis.config.S3Config
import geotrellis.spark.io.s3.S3Client
import org.apache.commons.io.IOUtils

trait S3Support extends S3Config { self: SparkSupport =>
  val loadParams: Map[String, String]
  val ingestParams: Map[String, String]
  val loadCredensials: Option[JBackend]
  val ingestCredensials: Option[JBackend]
  lazy val (s3LoadBucket, s3LoadPrefix)     = loadParams("bucket") -> loadParams("prefix")
  lazy val (s3IngestBucket, s3IngestPrefix) = ingestParams("bucket") -> ingestParams("prefix")
  @transient lazy val s3Client = S3Client.default
  @transient lazy val loadKeys = s3Client.listKeys(s3LoadBucket, s3LoadPrefix)

  def saveS3Keys(func: (String, Array[Byte]) => Unit) =
    loadKeys foreach { key =>
      func(key, IOUtils.toByteArray(s3Client.getObject(s3LoadBucket, key).getObjectContent))
    }
}
