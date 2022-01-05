package ir.maktab.service;

import ir.maktab.dao.ExpertDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.ImageSizeException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.entity.Client;
import ir.maktab.model.entity.Expert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class ExpertService {
    private final ExpertDao expertDao;

    public void add(Expert expert){
        Optional<Expert> found = findByEmail(expert.getEmail());
        if(found.isPresent())
            throw new DuplicateException("Duplicate expert!");
        if(expert.getImageData().length > 3072)
            throw new ImageSizeException("Maximum image size should be 300 KB");
        expertDao.save(expert);
    }

    private Optional<Expert> findByEmail(String email) {
        Optional<Expert> found = expertDao.findByEmail(email);
        return found;
    }

    public void update(Expert expert){
        Optional<Expert> found = findByEmail(expert.getEmail());
        if(found.isEmpty())
            throw new NotFoundException("expert not found!");
        expertDao.update(expert);
    }

    public List<Expert> getAllExperts(){
        List<Expert> experts = expertDao.findAllExperts();
        if(experts.isEmpty())
            throw new NotFoundException("there is no expert!");
        return experts;
    }

    public void remove(Expert expert){
        Optional<Expert> found = findByEmail(expert.getEmail());
        if(found.isEmpty())
            throw new NotFoundException("expert not found!");
        expertDao.delete(expert);
    }
}
