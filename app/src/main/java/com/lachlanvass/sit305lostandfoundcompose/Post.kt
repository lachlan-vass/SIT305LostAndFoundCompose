package com.lachlanvass.sit305lostandfoundcompose

import android.content.Context
import androidx.room.*

@Entity
data class Post(

    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "location") val location: String?,
    @ColumnInfo(name = "type") val type: String?
)

@Dao
interface PostDao {

    @Query("SELECT * FROM Post")
    fun getAll(): List<Post>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(post: Post)

    @Delete
    fun delete(post: Post)

    @Query("DELETE FROM Post WHERE uid = :uid")
    fun deleteById(uid: Int)
}

@Database(entities = [Post::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao() : PostDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,AppDatabase::class.java, "post_database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}
