package com.selflearning.journal.journal_backend.repository;

import com.selflearning.journal.journal_backend.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
