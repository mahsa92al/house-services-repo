package ir.maktab.service;

import ir.maktab.dao.ExpertDao;
import ir.maktab.exception.DuplicateException;
import ir.maktab.exception.ImageSizeException;
import ir.maktab.exception.NotFoundException;
import ir.maktab.model.dto.ExpertDto;
import ir.maktab.model.entity.Expert;
import ir.maktab.service.mapper.ExpertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mahsa Alikhani m-58
 */
@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService{
    private final ExpertDao expertDao;
    private final ExpertMapper expertMapper;

    @Override
    public void add(Expert expert){
        Optional<Expert> found = expertDao.findByEmail(expert.getEmail());
        if(found.isPresent())
            throw new DuplicateException("Duplicate expert!");
        if(expert.getImageData().length > 3072)
            throw new ImageSizeException("Maximum image size should be 300 KB");
        expertDao.save(expert);
    }

    @Override
    public void update(Expert expert){
        Optional<Expert> found = expertDao.findByEmail(expert.getEmail());
        if(found.isEmpty())
            throw new NotFoundException("expert not found!");
        expertDao.save(expert);
    }

    @Override
    public List<ExpertDto> getAllExperts(){
        List<Expert> experts = expertDao.findAll();
        if(experts.isEmpty())
            throw new NotFoundException("there is no expert!");
        return experts.stream().map(expertMapper::toExpertDto).collect(Collectors.toList());
    }

    @Override
    public void remove(Expert expert){
        Optional<Expert> found = expertDao.findByEmail(expert.getEmail());
        if(found.isEmpty())
            throw new NotFoundException("expert not found!");
        expertDao.delete(expert);
    }
}
