package com.selflearning.journal.journal_backend.service;

import com.selflearning.journal.journal_backend.entity.JournalEntry;
import com.selflearning.journal.journal_backend.entity.User;
import com.selflearning.journal.journal_backend.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        } catch (Exception e) {
            throw new RuntimeException("An error occur while saving the entry. ",e);
        }

    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public JournalEntry getById(ObjectId myId){
        return journalEntryRepository.findById(myId).orElse(null);
    }

    public void deleteById(ObjectId myId, String userName) {
        User user = userService.findByUserName(userName);
//        user.getJournalEntries().remove();
        user.getJournalEntries().removeIf(x->x.getId().equals(myId));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(myId);
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }
}
