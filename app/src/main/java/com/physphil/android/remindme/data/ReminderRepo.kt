package com.physphil.android.remindme.data

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.physphil.android.remindme.room.ReminderDao
import com.physphil.android.remindme.room.entities.Reminder

/**
 * Repository to handle fetching and saving Reminder data
 *
 * Copyright (c) 2018 Phil Shadlyn
 */
class ReminderRepo(private val dao: ReminderDao) {

    /**
     * Return a Reminder with the given id, wrapped in a LiveData object to observe
     * @param id of the reminder to return
     * @return the Reminder wrapped in a LiveData, or a new empty Reminder if the supplied id is NULL
     */
    fun getReminderById(id: String?): LiveData<Reminder> {
        return if (id == null) {
            val data = MutableLiveData<Reminder>()
            data.value = Reminder()
            data
        }
        else {
            dao.getReminderById(id)
        }
    }

    fun insertReminder(reminder: Reminder) {
        Thread(Runnable {
            dao.insertReminder(reminder)
        }).start()
    }

    fun updateReminder(reminder: Reminder) {
        Thread(Runnable {
            dao.updateReminder(reminder)
        }).start()
    }

    fun updateRecurringReminder(id: String, newExternalId: Int, newTime: Long) {
        Thread(Runnable {
            dao.updateRecurringReminder(id, newExternalId, newTime)
        }).start()
    }
}