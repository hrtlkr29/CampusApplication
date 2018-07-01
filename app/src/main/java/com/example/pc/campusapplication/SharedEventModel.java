package com.example.pc.campusapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class SharedEventModel extends ViewModel {
    private final MutableLiveData<Event> selectedEvent = new MutableLiveData<Event>();

    public void select(Event event){
        selectedEvent.setValue(event);
    }

    public LiveData<Event> getSeleceted(){
        return selectedEvent;
    }
}
