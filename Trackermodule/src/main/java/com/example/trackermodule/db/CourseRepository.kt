package com.example.patrollerapp.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.patrollerapp.homepage.pojo.UploadLatLong
import com.example.patrollerapp.util.Util
import com.example.trackermodule.db.Dao
import com.example.trackermodule.db.LatlongDatabase
import kotlinx.coroutines.*

class CourseRepository(application: Context?) {
    // below line is the create a variable
    // for dao and list for all courses.
    private val dao: Dao

    // below method is to read all the courses.
    val allCourses: LiveData<List<LatlongData>>

    // creating a constructor for our variables
    // and passing the variables to it.
    init {

        val database = LatlongDatabase.getInstance(application)
        dao = database.Dao()
        allCourses = dao.allCourses
    }

    // creating a method to insert the data to our database.
//    fun insert(model: LatlongData) {
//        model.id = System.currentTimeMillis()
//        InsertCourseAsyncTask(dao).execute(model)
    //         dao.insert(model);
//        insertRepo()
//    }
    @OptIn(DelicateCoroutinesApi::class)
    fun insert(model: LatlongData) = runBlocking {
        GlobalScope.launch(Dispatchers.Main) {
            model.id = System.currentTimeMillis()
            dao.insert(model);
        }
    }


    @OptIn(DelicateCoroutinesApi::class)
    fun delate() {
        GlobalScope.launch(Dispatchers.Main) { dao.nukeTable() }
    }

}