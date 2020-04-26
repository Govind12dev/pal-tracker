package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    HashMap<Long, TimeEntry> list;
    public InMemoryTimeEntryRepository() {
        list =new HashMap<>();
    }

    long id=1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        if(timeEntry.getId()==0)
            timeEntry.setId(id);
        id++;
        list.put(timeEntry.getId(),timeEntry);
        return timeEntry;

    }

    @Override
    public TimeEntry find(long timeEntryId) {

        return list.get(timeEntryId);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        TimeEntry timeEntry1=   list.replace(id,timeEntry);
        return list.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(list.values());
    }
    @Override
    public void delete(long id) {
        list.remove(id);
    }
}
