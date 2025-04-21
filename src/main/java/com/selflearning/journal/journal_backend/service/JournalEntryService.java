package com.selflearning.journal.journal_backend.service;

import com.selflearning.journal.journal_backend.entity.JournalEntry;
import com.selflearning.journal.journal_backend.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public JournalEntry getById(ObjectId myId){
        return journalEntryRepository.findById(myId).orElse(null);
    }

    public void deleteById(ObjectId myId) {
        journalEntryRepository.deleteById(myId);
    }
}
