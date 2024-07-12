package com.bookMangement.bookManagement.service;

import com.bookMangement.bookManagement.entity.Publisher;
import com.bookMangement.bookManagement.repository.BookRepository;
import com.bookMangement.bookManagement.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository pRepo;

    @Autowired
    private BookRepository bRepo;

    public Publisher savePublisher(Publisher publisher){
        Optional<Publisher> existingPublisher = pRepo.findById(publisher.getId());
        if (existingPublisher.isPresent()){
            Publisher savedPublisher = existingPublisher.get();
            if (!savedPublisher.getName().equals(publisher.getName())){
                Optional<Publisher> otherPublisher = pRepo.findByName(publisher.getName());
                if (otherPublisher.isPresent()){
                    throw  new IllegalArgumentException("Publisher '" + publisher.getName() + "' already exists");
                }
            }

            // Update the existing publisher with new data
            savedPublisher.setName(publisher.getName());
            savedPublisher.setAddress(publisher.getAddress());
            return pRepo.save(savedPublisher);
        }else{
            return pRepo.save(publisher);
        }
    }

    public  Publisher getPublisherById(int id){
        return pRepo.findById(id).orElse(null);
    }
    public List<Publisher> getAllPublishers(){
        return pRepo.findAll();
    }

    public void deletePublisherById(int id){
        bRepo.setPublisherToNull(id);
        pRepo.deleteById(id);
    }
}
