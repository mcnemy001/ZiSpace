package com.example.zispace.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.zispace.data.dao.DailyActivityDao
import com.example.zispace.data.dao.FriendDao
import com.example.zispace.data.dao.GalleryDao
import com.example.zispace.data.dao.InterestDao
import com.example.zispace.data.dao.MusicDao
import com.example.zispace.data.dao.ProfileDao
import com.example.zispace.data.dao.VideoDao
import com.example.zispace.data.entity.DailyActivityEntity
import com.example.zispace.data.entity.FriendEntity
import com.example.zispace.data.entity.GalleryEntity
import com.example.zispace.data.entity.InterestEntity
import com.example.zispace.data.entity.MusicEntity
import com.example.zispace.data.entity.ProfileEntity
import com.example.zispace.data.entity.VideoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        ProfileEntity::class, InterestEntity::class,
        DailyActivityEntity::class, FriendEntity::class,
        GalleryEntity::class, MusicEntity::class, VideoEntity::class
    ],
    version = 9,
    exportSchema = false
)
abstract class ZiSpaceDatabase : RoomDatabase() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    abstract fun profileDao(): ProfileDao
    abstract fun interestDao(): InterestDao
    abstract fun dailyActivityDao(): DailyActivityDao
    abstract fun friendDao(): FriendDao
    abstract fun galleryDao(): GalleryDao
    abstract fun musicDao(): MusicDao
    abstract fun videoDao(): VideoDao

    companion object {
        @Volatile
        private var INSTANCE: ZiSpaceDatabase? = null

        fun getDatabase(context: Context): ZiSpaceDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZiSpaceDatabase::class.java,
                    "zispace_database"
                )
                    .addCallback(DatabaseCallback(context))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database)
                }
            }
        }

        // PERBAIKAN: Logika diubah agar semua data diisi bersamaan saat database dibuat
        suspend fun populateDatabase(database: ZiSpaceDatabase) {
            val profileDao = database.profileDao()
            val interestDao = database.interestDao()
            val dailyActivityDao = database.dailyActivityDao()
            val friendDao = database.friendDao()
            val galleryDao = database.galleryDao()
            val musicDao = database.musicDao()
            val videoDao = database.videoDao()

            // Mengisi data Profil dan Minat
            val profile = ProfileEntity(
                id = 1,
                name = "Aziyusman Maulana",
                description = "Informatic Engineering\nStudent",
                profileImageUrl = "profile_placeholder",
                introduction = "I’m currently studying computer science at UNIKOM, Bandung, and now in my sixth semester. I enjoy building front-end websites, designing apps, and creating cool visual designs. I’m an easygoing who loves trying out new things, learning as I go, and finding ways to make my work useful and meaningful. I’m excited to keep growing my skills and take on projects that make a difference.\n",
                phone = "08552355264",
                email = "aziyusman18@gmail.com",
                instagram = "azysmn.m",
                location = "Bandung, West Java, Indonesia",
                mapsUrl = "geo:-6.8876,107.6160?q=-6.8876,107.6160(Aziyusman's Location)"
            )
            profileDao.insertProfile(profile)

            val interests = listOf(
                InterestEntity(profileOwnerId = 1, category = "hobby", name = "Indie and story games"),
                InterestEntity(profileOwnerId = 1, category = "hobby", name = "Football"),
                InterestEntity(profileOwnerId = 1, category = "hobby", name = "Photo Editing"),
                InterestEntity(profileOwnerId = 1, category = "food", name = "Ayam Geprek"),
                InterestEntity(profileOwnerId = 1, category = "food", name = "Nasi Goreng"),
                InterestEntity(profileOwnerId = 1, category = "food", name = "Mie Ayam"),
                InterestEntity(profileOwnerId = 1, category = "drink", name = "Plain Water"),
                InterestEntity(profileOwnerId = 1, category = "drink", name = "Mango Juice"),
                InterestEntity(profileOwnerId = 1, category = "drink", name = "Sweet Iced Tea"),
                InterestEntity(profileOwnerId = 1, category = "interest", name = "Frontend dev"),
                InterestEntity(profileOwnerId = 1, category = "interest", name = "Mobile app dev"),
                InterestEntity(profileOwnerId = 1, category = "interest", name = "UI Design"),
                InterestEntity(profileOwnerId = 1, category = "interest", name = "Graphic Design"),
                InterestEntity(profileOwnerId = 1, category = "aspiration", name = "Remote work"),
                InterestEntity(profileOwnerId = 1, category = "aspiration", name = "Building my own startup")
            )
            interestDao.insertAll(interests)

            // Mengisi data Aktivitas Harian
            val activities = listOf(
                DailyActivityEntity(icon = "ic_school", name = "Morning class at campus"),
                DailyActivityEntity(icon = "ic_work", name = "Working on a side project"),
                DailyActivityEntity(icon = "ic_walk", name = "Evening walk"),
                DailyActivityEntity(icon = "ic_design", name = "Editing photos or designing"),
                DailyActivityEntity(icon = "ic_gaming", name = "Late-night gaming session")
            )
            dailyActivityDao.insertAll(activities)

            // Mengisi data Teman
            val friends = listOf(
                FriendEntity(name = "Wildan", avatar = "avatar_wildan"),
                FriendEntity(name = "Atam", avatar = "avatar_atam"),
                FriendEntity(name = "Rifqi", avatar = "avatar_rifqi"),
                FriendEntity(name = "Imam", avatar = "avatar_imam")
            )
            friendDao.insertAll(friends)

            // Mengisi data Galeri
            val images = (1..17).map { GalleryEntity(imageName = "gallery_$it") }
            galleryDao.insertAll(images)

            // Isi data Musik Favorit
            val musicList = listOf(
                MusicEntity(title = "A Sky Full of Stars", artist = "Coldplay", albumArt = "art_coldplay", url = "https://open.spotify.com/track/0FDzzruyVECATHXKHFs9eJ"),
                MusicEntity(title = "About You", artist = "The 1975", albumArt = "art_1975", url = "https://open.spotify.com/track/1fDFHXcykq4iw8Gg7s5hG9"),
                MusicEntity(title = "Merdeka (Live)", artist = "Navicula", albumArt = "art_navicula", url = "https://open.spotify.com/track/1YZ9U5R1rTLCWGPHhMsAj2"),
                MusicEntity(title = "Diatas Awan", artist = "Nidji", albumArt = "art_nidji", url = "https://open.spotify.com/track/5pdffVGGraLgUsy7dM4IXw")
            )
            musicDao.insertAll(musicList)

            // Isi data Video Favorit
            val videoList = listOf(
                VideoEntity(title = "Youtube Rewind INDONESIA 2016 - Unity in Diversity", thumbnail = "thumb_rewind_2016", url = "https://youtu.be/1ZIGLm5cuDo"),
                VideoEntity(title = "“Wonderland Indonesia” by Alffy Rev ft. Novia Bachmid (Chapter 1)", thumbnail = "thumb_wonderland", url = "https://youtu.be/aKtb7Y3qOck")
            )
            videoDao.insertAll(videoList)
        }
    }
}
