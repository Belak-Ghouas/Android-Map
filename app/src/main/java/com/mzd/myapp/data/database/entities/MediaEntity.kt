package com.psa.containeroptim.data.database.entities

import androidx.room.*


@Entity(tableName = "media")
public class MediaEntity {


    @TypeConverters(MediaStatusConverter::class)
    @ColumnInfo(name = "media_status")
    var mediaStatus = MediaStatus.NONE


    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var mediaTag: String? = null
    var uri: String? = null

    @TypeConverters(MediaTypeConverter::class)
    @ColumnInfo(name = "media_type")
    var mediaType: MediaType? = null




    enum class MediaStatus {
        NONE, TO_BE_DOWNLOADED, TO_BE_UPLOADED, DOWNLOADING, UPLOADING, READY, ERROR_DOWNLOADING, ERROR_UPLOADING
    }
    enum class MediaType(var ext: String) {

        IMAGE(".jpg"), VIDEO(".mp4"), NONE("");

        companion object {
            fun fromExt(ext: String?): MediaType {

                if (ext == null || ext.length == 0)
                         return NONE

                val types = values()

                for (type in types) {
                    if (type.ext == ext) {
                        return type
                    }
                }
                return IMAGE
            }
        }

    }



    public class MediaTypeConverter {
        @TypeConverter
      public  fun toMediaType(mediaType: String?): MediaType {
            return try {
                MediaType.valueOf(
                    mediaType!!
                )
            } catch (e: Exception) {
                MediaType.IMAGE
            }
        }

        @TypeConverter
        fun toString(mediaType: MediaType): String {
            return mediaType.name
        }
    }

    public class MediaStatusConverter {
        @TypeConverter
        fun toMediaStatus(mediaStatus: String?): MediaStatus {
            return try {
                MediaStatus.valueOf(
                    mediaStatus!!
                )
            } catch (e: Exception) {
                MediaStatus.NONE
            }
        }

        @TypeConverter
        fun toString(mediaStatus: MediaStatus): String {
            return mediaStatus.name
        }
    }
}
